/*
 * Copyright (C) 2017 HERE Global B.V. and its affiliate(s). All rights reserved.
 *
 * This software, including documentation, is protected by copyright controlled by
 * HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the prior written
 * consent of HERE Global B.V. This material also contains confidential information,
 * which may not be disclosed to others without prior written consent of HERE Global B.V.
 *
 */

package com.here.ivi.api.generator.common;

import com.here.ivi.api.TranspilerExecutionException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.text.StrBuilder;

/**
 * Generic implementation of command line tool to be run on each file generated by {@link
 * com.here.ivi.api.generator.common.GeneratorSuite}. Executed command must accept input data on
 * stdin and return processed data to stdout
 */
public class CommandLineTool implements FileTool {

  private final List<String> processCommand;
  private final String cwd;

  /**
   * @param command command to execute, excluding arguments
   * @param cwd existing path which will be current working directory of executed command
   * @param initialArgs list of arguments to pass to command
   */
  public CommandLineTool(String command, String cwd, List<String> initialArgs) {
    this.cwd = cwd;

    processCommand = new ArrayList<>();
    processCommand.add(command);
    processCommand.addAll(initialArgs);
  }

  @Override
  public GeneratedFile process(GeneratedFile file) {

    CharSequence processedContent = executeCommand(file.content);
    return new GeneratedFile(processedContent, file.targetFile);
  }

  /**
   * Create and start system process with a given command line
   *
   * @return system process
   * @apiNote protected overridable for mock Process injection in tests
   */
  protected Process startSystemProcess() {

    ProcessBuilder processBuilder = new ProcessBuilder(processCommand);
    if (!cwd.isEmpty()) {
      processBuilder.directory(new File(cwd));
    }

    try {
      return processBuilder.start();
    } catch (IOException e) {
      throw new TranspilerExecutionException(
          String.format("Starting a process for tool '%s' failed with error:", processCommand), e);
    }
  }

  /**
   * Method to actually execute command.
   *
   * @param input content to be processed
   * @return processed data
   */
  private CharSequence executeCommand(CharSequence input) {

    Process process = startSystemProcess();
    try {
      writeInput(process, input);
      String output = readOutput(process.getInputStream(), "stdout");
      String error = readOutput(process.getErrorStream(), "stderr");

      process.waitFor();

      throwIfError(process, error);

      return output;
    } catch (InterruptedException e) {
      throw new TranspilerExecutionException(
          String.format("Waiting for the process of tool '%s' failed with error:", processCommand),
          e);
    } finally {
      process.destroy();
    }
  }

  private void writeInput(Process process, CharSequence input) {

    if (input.length() == 0) {
      return;
    }

    OutputStream stdin = process.getOutputStream();
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
      writer.append(input);
    } catch (IOException e) {
      throw new TranspilerExecutionException(
          String.format("Writing to stdin of tool '%s' failed with error:", processCommand), e);
    }
  }

  private String readOutput(InputStream inputStream, String streamName) {

    try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(inputStream))) {
      StrBuilder outputBuilder = new StrBuilder();
      String line = outputReader.readLine();
      while (line != null) {
        outputBuilder.appendln(line);
        line = outputReader.readLine();
      }
      return outputBuilder.toString();
    } catch (IOException e) {
      throw new TranspilerExecutionException(
          String.format("Reading %s of tool '%s' failed with error:", streamName, processCommand),
          e);
    }
  }

  private void throwIfError(Process process, String error) {

    int processExitValue = process.exitValue();
    if (processExitValue != 0 || !error.isEmpty()) {
      StrBuilder errorMessage = new StrBuilder();
      errorMessage.appendln(
          String.format("Tool '%s' ended with code: %d.", processCommand, processExitValue));
      if (!error.isEmpty()) {
        errorMessage.appendln("Producing following error:");
        errorMessage.appendln(error);
      }
      throw new TranspilerExecutionException(errorMessage.toString());
    }
  }
}
