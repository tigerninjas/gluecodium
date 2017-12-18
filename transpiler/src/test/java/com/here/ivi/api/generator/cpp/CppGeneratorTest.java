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

package com.here.ivi.api.generator.cpp;

import static org.junit.Assert.*;

import com.here.ivi.api.generator.common.GeneratedFile;
import com.here.ivi.api.model.common.Include;
import com.here.ivi.api.model.cppmodel.CppClass;
import com.here.ivi.api.model.cppmodel.CppFile;
import com.here.ivi.api.model.cppmodel.CppMethod;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CppGeneratorTest {

  private static final String OUTPUT_FILE_NAME_HEADER = "some_fancy_header.output";
  private static final String OUTPUT_FILE_NAME_IMPL = "some_fancy_impl.output";

  private final CppGenerator cppGenerator = new CppGenerator();

  private final CppMethod cppMethod = new CppMethod.Builder("methodical").build();
  private final CppClass cppClass = new CppClass("Classy");
  private final CppFile cppModel = new CppFile(Collections.emptyList());

  @Before
  public void setUp() {
    cppClass.methods.add(cppMethod);
    cppModel.members.add(cppClass);
  }

  @Test
  public void generateCodeWithNullModel() {
    List<GeneratedFile> generatedFiles =
        cppGenerator.generateCode(null, OUTPUT_FILE_NAME_HEADER, OUTPUT_FILE_NAME_IMPL, "");

    assertTrue(generatedFiles.isEmpty());
  }

  @Test
  public void generateCodeWithEmptyModel() {
    cppModel.members.clear();

    List<GeneratedFile> generatedFiles =
        cppGenerator.generateCode(cppModel, OUTPUT_FILE_NAME_HEADER, OUTPUT_FILE_NAME_IMPL, "");

    assertTrue(generatedFiles.isEmpty());
  }

  @Test
  public void generateCodeWithNonEmptyModel() {
    List<GeneratedFile> generatedFiles =
        cppGenerator.generateCode(cppModel, OUTPUT_FILE_NAME_HEADER, OUTPUT_FILE_NAME_IMPL, "");

    assertFalse(generatedFiles.isEmpty());
  }

  @Test
  public void generateCodeFiltersOutSelfIncludes() {
    Include nonsenseInclude = Include.createInternalInclude("nonsense");
    Include selfInclude =
        Include.createInternalInclude(OUTPUT_FILE_NAME_HEADER + CppNameRules.HEADER_FILE_SUFFIX);
    cppModel.includes.add(nonsenseInclude);
    cppModel.includes.add(selfInclude);

    cppGenerator.generateCode(cppModel, OUTPUT_FILE_NAME_HEADER, OUTPUT_FILE_NAME_IMPL, "");

    assertTrue(cppModel.includes.contains(nonsenseInclude));
    assertFalse(cppModel.includes.contains(selfInclude));
  }

  @Test
  public void generateCodeGeneratesHeaderAndImplementationForInstantiableClass() {
    List<GeneratedFile> generatedFiles =
        cppGenerator.generateCode(cppModel, OUTPUT_FILE_NAME_HEADER, OUTPUT_FILE_NAME_IMPL, "");

    assertEquals(2, generatedFiles.size());
    assertTrue(
        generatedFiles.get(1).content.contains("#include \"" + OUTPUT_FILE_NAME_HEADER + ".h\""));
  }

  @Test
  public void generateCodeGeneratesHeaderAndImplementationForInstantiableClassWithStaticMethods() {
    cppMethod.specifiers.add(CppMethod.Specifier.STATIC);
    cppClass.methods.add(new CppMethod.Builder("non_static_methodical").build());

    List<GeneratedFile> generatedFiles =
        cppGenerator.generateCode(cppModel, OUTPUT_FILE_NAME_HEADER, OUTPUT_FILE_NAME_IMPL, "");

    assertEquals(2, generatedFiles.size());
    assertTrue(
        generatedFiles.get(1).content.contains("#include \"" + OUTPUT_FILE_NAME_HEADER + ".h\""));
  }
}