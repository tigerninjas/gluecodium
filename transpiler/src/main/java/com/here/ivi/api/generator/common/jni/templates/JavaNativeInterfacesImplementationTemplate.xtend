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

package com.here.ivi.api.generator.common.jni.templates

import com.here.ivi.api.generator.common.java.templates.JavaCopyrightHeaderTemplate
import com.here.ivi.api.model.javamodel.JavaClass

public class JavaNativeInterfacesImplementationTemplate {
  def static generate(JavaClass javaClass) '''
    «JavaCopyrightHeaderTemplate.generate()»

    /**
     * JNI implementation for class «javaClass.name»
     */

    #include "TODO"

    «FOR method : javaClass.methods»
    «method.returnType.name» «JavaNativeFunctionSignatureTemplate.generate(javaClass.name, method)»{
        //TODO
    }
    «ENDFOR»
  '''
}
