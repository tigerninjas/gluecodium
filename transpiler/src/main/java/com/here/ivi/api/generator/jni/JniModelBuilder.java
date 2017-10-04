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

package com.here.ivi.api.generator.jni;

import com.here.ivi.api.common.CollectionsHelper;
import com.here.ivi.api.generator.baseapi.CppModelBuilder;
import com.here.ivi.api.generator.common.AbstractModelBuilder;
import com.here.ivi.api.generator.common.ModelBuilderContextStack;
import com.here.ivi.api.generator.cpp.CppNameRules;
import com.here.ivi.api.generator.java.JavaModelBuilder;
import com.here.ivi.api.model.cppmodel.CppClass;
import com.here.ivi.api.model.cppmodel.CppField;
import com.here.ivi.api.model.cppmodel.CppMethod;
import com.here.ivi.api.model.cppmodel.CppParameter;
import com.here.ivi.api.model.cppmodel.CppStruct;
import com.here.ivi.api.model.franca.FrancaElement;
import com.here.ivi.api.model.javamodel.*;
import com.here.ivi.api.model.jni.*;
import com.here.ivi.api.model.rules.InstanceRules;
import java.util.Collections;
import java.util.List;
import org.franca.core.franca.*;

/**
 * This class builds a correspondence-tree containing correspondences between java and cpp model
 * elements. Currently only corresponding classes and theirs corresponding methods are calculated.
 *
 * <p>Preconditions:
 *
 * <p>For correspondence calculation it is assumed to have one to one mapping, i.e. one franca
 * element is mapped to at least one target model element.
 *
 * <p>It is assumed that Java- and CppModelBuilder's finishBuilding methods are called in advance of
 * calling finishBuilding on JniModelBuilder (constructed java and cpp elements need to be
 * accessible via getResults(..) ).
 */
public class JniModelBuilder extends AbstractModelBuilder<JniElement> {

  private final FrancaElement rootModel;
  private final JavaModelBuilder javaBuilder;
  private final CppModelBuilder cppBuilder;

  public JniModelBuilder(
      final ModelBuilderContextStack<JniElement> contextStack,
      final FrancaElement rootModel,
      final JavaModelBuilder javaBuilder,
      final CppModelBuilder cppBuilder) {

    super(contextStack);
    this.rootModel = rootModel;
    this.javaBuilder = javaBuilder;
    this.cppBuilder = cppBuilder;
  }

  public JniModelBuilder(
      final FrancaElement rootModel,
      final JavaModelBuilder javaBuilder,
      final CppModelBuilder cppBuilder) {
    this(new ModelBuilderContextStack<>(), rootModel, javaBuilder, cppBuilder);
  }

  @Override
  public void finishBuilding(FInterface francaInterface) {

    CppClass cppClass = cppBuilder.getFirstResult(CppClass.class);
    JavaTopLevelElement javaTopLevelElement = javaBuilder.getFirstResult(JavaTopLevelElement.class);
    JavaClass javaClass = javaBuilder.getFirstResult(JavaClass.class);
    JniContainer jniContainer =
        JniContainer.createInterfaceContainer(
            javaTopLevelElement.javaPackage.packageNames,
            rootModel.getPackageNames(),
            javaClass.name,
            javaTopLevelElement.name,
            cppClass.name,
            cppClass.hasInstanceMethods());
    List<JniElement> previousResults = getCurrentContext().previousResults;
    CollectionsHelper.getStreamOfType(previousResults, JniStruct.class).forEach(jniContainer::add);
    CollectionsHelper.getStreamOfType(previousResults, JniMethod.class).forEach(jniContainer::add);
    storeResult(jniContainer);
    closeContext();
  }

  @Override
  public void finishBuilding(FMethod francaMethod) {

    JavaMethod javaMethod = javaBuilder.getFirstResult(JavaMethod.class);
    CppMethod cppMethod = cppBuilder.getFirstResult(CppMethod.class);

    JniType returnType = JniType.createType(javaMethod.returnType, cppMethod.returnType);
    boolean isStatic = cppMethod.specifiers.contains(CppMethod.Specifier.STATIC);
    JniMethod jniMethod = new JniMethod(javaMethod.getName(), cppMethod.name, returnType, isStatic);
    jniMethod.parameters.addAll(
        CollectionsHelper.getAllOfType(getCurrentContext().previousResults, JniParameter.class));

    storeResult(jniMethod);
    closeContext();
  }

  @Override
  public void finishBuildingInputArgument(FArgument francaArgument) {

    FTypeRef typeReference = francaArgument.getType();

    boolean isInstanceRef =
        typeReference.getDerived() instanceof FTypeDef
            && InstanceRules.isInstanceId((FTypeDef) typeReference.getDerived());

    JavaParameter javaParameter = javaBuilder.getFirstResult(JavaParameter.class);
    CppParameter cppParameter = cppBuilder.getFirstResult(CppParameter.class);
    JniType jniType = JniType.createType(javaParameter.type, cppParameter.type, isInstanceRef);

    storeResult(new JniParameter(javaParameter.getName(), jniType));
    closeContext();
  }

  @Override
  public void finishBuilding(FStructType francaStructType) {

    JavaClass javaClass = javaBuilder.getFirstResult(JavaClass.class);
    CppStruct cppStruct = cppBuilder.getFirstResult(CppStruct.class);
    List<JniField> jniFields =
        CollectionsHelper.getAllOfType(getCurrentContext().previousResults, JniField.class);

    storeResult(new JniStruct(javaClass, cppStruct, jniFields));
    closeContext();
  }

  @Override
  public void finishBuilding(FField francaField) {

    JavaField javaField = javaBuilder.getFirstResult(JavaField.class);
    CppField cppField = cppBuilder.getFirstResult(CppField.class);

    storeResult(new JniField(javaField, cppField));
    closeContext();
  }

  @Override
  public void finishBuilding(FTypeCollection francaTypeCollection) {

    JniStruct jniStruct =
        CollectionsHelper.getFirstOfType(getCurrentContext().previousResults, JniStruct.class);
    List<String> structsPackage =
        jniStruct != null ? jniStruct.javaClass.javaPackage.packageNames : Collections.emptyList();
    List<String> cppNameSpace = CppNameRules.getNestedNameSpecifier(francaTypeCollection);

    JniContainer jniContainer =
        JniContainer.createTypeCollectionContainer(structsPackage, cppNameSpace);
    CollectionsHelper.getStreamOfType(getCurrentContext().previousResults, JniStruct.class)
        .forEach(jniContainer::add);

    storeResult(jniContainer);
    closeContext();
  }

  @Override
  public void finishBuilding(FAttribute francaAttribute) {

    List<JavaMethod> javaMethods =
        CollectionsHelper.getAllOfType(javaBuilder.getResults(), JavaMethod.class);
    List<CppMethod> cppMethods =
        CollectionsHelper.getAllOfType(cppBuilder.getResults(), CppMethod.class);

    JavaMethod javaGetter = javaMethods.get(0);
    CppMethod cppGetter = cppMethods.get(0);
    JniType jniType = JniType.createType(javaGetter.returnType, cppGetter.returnType);
    storeResult(new JniMethod(javaGetter.name, cppGetter.name, jniType, false));

    if (!francaAttribute.isReadonly()) {
      JavaMethod javaSetter = javaMethods.get(1);
      CppMethod cppSetter = cppMethods.get(1);
      JniMethod jniSetter = new JniMethod(javaSetter.name, cppSetter.name, null, false);

      JavaParameter javaParameter = javaSetter.parameters.get(0);
      CppParameter cppParameter = cppSetter.parameters.get(0);
      JniType parameterType = JniType.createType(javaParameter.type, cppParameter.type);
      jniSetter.parameters.add(new JniParameter(javaParameter.name, parameterType));

      storeResult(jniSetter);
    }

    closeContext();
  }
}
