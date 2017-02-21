package com.here.ivi.api.model.cppmodel;

import com.here.ivi.api.model.Includes;

import java.util.*;
import java.util.stream.Collectors;

public class CppElements {

    public static final String CONST_QUALIFIER = "const";
    public static final String REF_QUALIFIER = "&";
    public static final String POINTER = "*";

    public enum Visibility {
        Default,
        Public,
        Protected,
        Private
    }

    public enum TypeInfo {
        Invalid,
        BuiltIn,
        InterfaceInstance,
        Complex
    }

    public static Set<Includes.Include> collectIncludes(CppNamespace root) {
        return root.streamRecursive()
                .filter(p -> p instanceof CppType)
                .map(CppType.class::cast)
                .map(t -> t.includes).flatMap(Set::stream).collect(Collectors.toSet());
    }

    public static Set<Includes.Include> collectIncludes(CppClass cppClass) {
        Set<Includes.Include> result = new HashSet();
        for (CppMethod method : cppClass.methods){
            for(CppParameter inParam : method.inParameters) {
                result.addAll(inParam.type.includes);
            }
            for(CppParameter outParam : method.outParameters) {
                result.addAll(outParam.type.includes);
            }
            //TODO add also return type for all methods, once the return type changes from current String to CppType
        }
        return result;
    }
}
