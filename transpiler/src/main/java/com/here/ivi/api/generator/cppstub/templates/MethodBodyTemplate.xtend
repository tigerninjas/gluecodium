package com.here.ivi.api.generator.cppstub.templates

import com.here.ivi.api.model.cppmodel.CppMethod
import org.eclipse.xtend2.lib.StringConcatenation

interface MethodBodyTemplate {
  def abstract StringConcatenation generate(CppMethod m)
}