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

package com.here.ivi.api.model.java;

import static org.junit.Assert.assertEquals;

import com.here.ivi.api.model.java.JavaReferenceType.Type;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class JavaReferenceTypeTest {

  @Parameter public Type type;

  @Parameter(1)
  public String literalName;

  @Parameterized.Parameters
  public static Collection<?> getValues() {
    return java.util.Arrays.asList(
        new Object[][] {{Type.STRING, "string"}, {Type.THROWABLE, "Throwable"}});
  }

  @Test
  public void getLiteralName() {
    assertEquals(literalName, new JavaReferenceType(type).getLiteralName());
  }
}