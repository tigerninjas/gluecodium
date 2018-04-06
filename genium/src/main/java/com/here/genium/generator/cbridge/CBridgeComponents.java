/*
 * Copyright (c) 2016-2018 HERE Europe B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package com.here.genium.generator.cbridge;

import static com.here.genium.generator.cbridge.CBridgeNameRules.CBRIDGE_INTERNAL;
import static com.here.genium.generator.cbridge.CBridgeNameRules.INCLUDE_DIR;

import com.google.common.collect.Iterables;
import com.here.genium.model.cbridge.*;
import com.here.genium.model.common.Include;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public final class CBridgeComponents {

  public static final String PROXY_CACHE_FILENAME =
      Paths.get(CBRIDGE_INTERNAL, INCLUDE_DIR, "CachedProxyBase.h").toString();

  @SuppressWarnings("OperatorWrap")
  public static Collection<Include> collectImplementationIncludes(CInterface cInterface) {

    Collection<Include> includes = new LinkedList<>();

    for (CFunction function :
        Iterables.concat(cInterface.functions, cInterface.inheritedFunctions)) {
      includes.addAll(collectFunctionBodyIncludes(function));
    }
    for (CStruct struct : cInterface.structs) {
      includes.addAll(struct.mappedType.includes);
      for (CField field : struct.fields) {
        includes.addAll(field.type.includes);
      }
    }
    if (cInterface.selfType != null) {
      includes.addAll(cInterface.selfType.includes);
    }
    for (CMap map : cInterface.maps) {
      includes.add(map.include);
    }

    return includes;
  }

  public static Collection<Include> collectPrivateHeaderIncludes(CInterface cInterface) {
    Collection<Include> includes = new LinkedList<>();
    for (CStruct struct : cInterface.structs) {
      includes.addAll(struct.mappedType.includes);
    }
    if (cInterface.selfType != null) {
      includes.addAll(cInterface.selfType.includes);
    }
    return includes;
  }

  public static Collection<Include> collectHeaderIncludes(CInterface cInterface) {

    Collection<Include> includes = new LinkedList<>();

    for (CFunction function : cInterface.functions) {
      includes.addAll(collectFunctionSignatureIncludes(function));
    }
    for (CStruct struct : cInterface.structs) {
      for (CField field : struct.fields) {
        includes.addAll(field.type.functionReturnType.includes);
        for (CType type : field.type.cTypesNeededByConstructor) {
          includes.addAll(type.includes);
        }
      }
    }
    for (CEnum enumType : cInterface.enumerators) {
      includes.addAll(enumType.includes);
    }
    for (final CMap map : cInterface.maps) {
      includes.addAll(map.keyType.functionReturnType.includes);
      includes.addAll(map.valueType.functionReturnType.includes);
    }

    return includes;
  }

  public static Collection<Include> collectImplementationIncludes(final Collection<CArray> arrays) {
    return arrays.stream().flatMap(array -> array.includes().stream()).collect(Collectors.toList());
  }

  public static Collection<Include> collectHeaderIncludes(final Collection<CArray> arrays) {
    return arrays
        .stream()
        .flatMap(array -> array.returnTypeIncludes().stream())
        .collect(Collectors.toList());
  }

  private static Collection<Include> collectFunctionSignatureIncludes(CFunction function) {
    Collection<Include> includes = new LinkedList<>();
    for (CParameter parameter : function.parameters) {
      includes.addAll(parameter.getSignatureIncludes());
    }
    includes.addAll(function.returnType.functionReturnType.includes);
    if (function.error != null) {
      includes.addAll(function.error.functionReturnType.includes);
    }
    return includes;
  }

  private static Collection<Include> collectFunctionBodyIncludes(CFunction function) {
    Collection<Include> includes = new LinkedList<>();
    for (CParameter parameter : function.parameters) {
      includes.addAll(parameter.mappedType.includes);
    }
    includes.addAll(function.returnType.includes);
    includes.addAll(function.delegateCallIncludes);
    if (function.selfParameter != null) {
      includes.addAll(function.selfParameter.mappedType.includes);
    }
    return includes;
  }
}