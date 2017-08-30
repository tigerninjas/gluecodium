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

package com.here.ivi.api.generator.baseapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.here.ivi.api.generator.common.AbstractFrancaCommentParser;
import com.here.ivi.api.generator.cpp.CppDefaultInitializer;
import com.here.ivi.api.generator.cpp.CppTypeMapper;
import com.here.ivi.api.generator.cpp.CppValueMapper;
import com.here.ivi.api.model.cppmodel.CppClass;
import com.here.ivi.api.model.cppmodel.CppComplexTypeRef;
import com.here.ivi.api.model.cppmodel.CppConstant;
import com.here.ivi.api.model.cppmodel.CppElement;
import com.here.ivi.api.model.cppmodel.CppEnum;
import com.here.ivi.api.model.cppmodel.CppEnumItem;
import com.here.ivi.api.model.cppmodel.CppField;
import com.here.ivi.api.model.cppmodel.CppMethod;
import com.here.ivi.api.model.cppmodel.CppParameter;
import com.here.ivi.api.model.cppmodel.CppPrimitiveTypeRef;
import com.here.ivi.api.model.cppmodel.CppStruct;
import com.here.ivi.api.model.cppmodel.CppTaggedUnion;
import com.here.ivi.api.model.cppmodel.CppTypeDefRef;
import com.here.ivi.api.model.cppmodel.CppTypeRef;
import com.here.ivi.api.model.cppmodel.CppUsing;
import com.here.ivi.api.model.cppmodel.CppValue;
import com.here.ivi.api.model.franca.DefinedBy;
import com.here.ivi.api.model.franca.FrancaElement;
import com.here.ivi.api.model.rules.InstanceRules;
import com.here.ivi.api.test.ArrayEList;
import com.here.ivi.api.test.MockContextStack;
import java.util.List;
import org.franca.core.franca.FArgument;
import org.franca.core.franca.FArrayType;
import org.franca.core.franca.FConstantDef;
import org.franca.core.franca.FEnumerationType;
import org.franca.core.franca.FEnumerator;
import org.franca.core.franca.FExpression;
import org.franca.core.franca.FField;
import org.franca.core.franca.FInitializerExpression;
import org.franca.core.franca.FInterface;
import org.franca.core.franca.FMapType;
import org.franca.core.franca.FMethod;
import org.franca.core.franca.FModelElement;
import org.franca.core.franca.FStructType;
import org.franca.core.franca.FTypeCollection;
import org.franca.core.franca.FTypeDef;
import org.franca.core.franca.FTypeRef;
import org.franca.core.franca.FTypedElement;
import org.franca.core.franca.FUnionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
  CppMethodMapper.class,
  CppCommentParser.class,
  InstanceRules.class,
  CppTypeMapper.class,
  DefinedBy.class,
  CppDefaultInitializer.class,
  CppValueMapper.class
})
@SuppressWarnings({"PMD.TooManyFields", "PMD.TooManyMethods", "PMD.CouplingBetweenObjects"})
public class CppModelBuilderTest {

  private static final String CLASS_NAME = "classy";
  private static final String RETURN_TYPE_COMMENT = "no comments";
  private static final String PARAMETER_NAME = "flowers";
  private static final String STRUCT_NAME = "structural";
  private static final String TYPE_DEF_NAME = "definitely";
  private static final String ARRAY_NAME = "relay";
  private static final String MAP_NAME = "tigers";
  private static final String FIELD_NAME = "flowers";
  private static final String CONSTANT_NAME = "permanent";
  private static final String ENUM_NAME = "innumerable";
  private static final String ENUM_ITEM_NAME = "enumerated";
  private static final String USING_NAME = "useful";
  private static final String UNION_NAME = "soviet";

  private final MockContextStack<CppElement> contextStack = new MockContextStack<>();

  @Mock private FrancaElement rootModel;

  @Mock private FInterface francaInterface;
  @Mock private FMethod francaMethod;
  @Mock private FArgument francaArgument;
  @Mock private FTypeCollection francaTypeCollection;
  @Mock private FConstantDef francaConstant;
  @Mock private FField francaField;
  @Mock private FStructType francaStructType;
  @Mock private FTypeDef francaTypeDef;
  @Mock private FTypeRef francaTypeRef;
  @Mock private FArrayType francaArrayType;
  @Mock private FMapType francaMapType;
  @Mock private FEnumerationType francaEnumerationType;
  @Mock private FTypeRef francaAnotherTypeRef;
  @Mock private FInitializerExpression francaInitializerExpression;
  @Mock private FEnumerator francaEnumerator;
  @Mock private FExpression francaExpression;
  @Mock private FUnionType francaUnionType;

  private CppModelBuilder modelBuilder;

  private final CppComplexTypeRef cppComplexTypeRef =
      new CppComplexTypeRef.Builder("::typically").build();

  private final CppMethod cppMethod = new CppMethod.Builder("classical").build();
  private final CppValue cppValue = new CppValue("valuable");
  private final CppEnum cppEnum = new CppEnum(ENUM_NAME);
  private final CppStruct cppStruct = new CppStruct(STRUCT_NAME);
  private final CppTypeRef cppTypeRef = new CppPrimitiveTypeRef(CppPrimitiveTypeRef.Type.INT64);
  private final CppUsing cppUsing =
      new CppUsing(USING_NAME, new CppTypeDefRef(USING_NAME, cppTypeRef));

  @Before
  public void setUp() {
    PowerMockito.mockStatic(
        CppMethodMapper.class,
        CppCommentParser.class,
        InstanceRules.class,
        CppTypeMapper.class,
        DefinedBy.class,
        CppDefaultInitializer.class,
        CppValueMapper.class);
    MockitoAnnotations.initMocks(this);

    modelBuilder = new CppModelBuilder(contextStack, rootModel);

    when(francaInterface.getName()).thenReturn(CLASS_NAME);
    when(francaArgument.getName()).thenReturn(PARAMETER_NAME);
    when(francaStructType.getName()).thenReturn(STRUCT_NAME);
    when(francaTypeDef.getName()).thenReturn(TYPE_DEF_NAME);
    when(francaArrayType.getName()).thenReturn(ARRAY_NAME);
    when(francaMapType.getName()).thenReturn(MAP_NAME);
    when(francaField.getName()).thenReturn(FIELD_NAME);
    when(francaConstant.getName()).thenReturn(CONSTANT_NAME);
    when(francaEnumerationType.getName()).thenReturn(ENUM_NAME);
    when(francaEnumerator.getName()).thenReturn(ENUM_ITEM_NAME);
    when(francaUnionType.getName()).thenReturn(UNION_NAME);

    when(francaMethod.getInArgs()).thenReturn(new ArrayEList<>());
    when(francaTypeDef.getActualType()).thenReturn(francaTypeRef);
    when(francaMapType.getKeyType()).thenReturn(francaTypeRef);
    when(francaMapType.getValueType()).thenReturn(francaAnotherTypeRef);
    when(francaField.getType()).thenReturn(francaTypeRef);
    when(francaConstant.getRhs()).thenReturn(francaInitializerExpression);

    when(CppMethodMapper.mapMethodReturnType(any(), any()))
        .thenReturn(new CppMethodMapper.ReturnTypeData(cppComplexTypeRef, RETURN_TYPE_COMMENT));
    when(CppCommentParser.parse(any(FModelElement.class)))
        .thenReturn(new AbstractFrancaCommentParser.Comments());
    when(CppCommentParser.parse(any(FMethod.class)))
        .thenReturn(new AbstractFrancaCommentParser.Comments());
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsName() {
    modelBuilder.finishBuilding(francaInterface);

    CppClass cppClass = modelBuilder.getFirstResult(CppClass.class);
    assertNotNull(cppClass);
    assertTrue(cppClass.name.toLowerCase().startsWith(CLASS_NAME));

    verify(francaInterface).getName();
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsMethods() {
    contextStack.injectResult(cppMethod);

    modelBuilder.finishBuilding(francaInterface);

    CppClass cppClass = modelBuilder.getFirstResult(CppClass.class);
    assertNotNull(cppClass);
    assertFalse(cppClass.methods.isEmpty());
    assertEquals(cppMethod, cppClass.methods.iterator().next());
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsStructs() {
    contextStack.injectResult(cppStruct);

    modelBuilder.finishBuilding(francaInterface);

    CppClass cppClass = modelBuilder.getFirstResult(CppClass.class);
    assertNotNull(cppClass);
    assertFalse(cppClass.structs.isEmpty());
    assertEquals(cppStruct, cppClass.structs.iterator().next());
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsEnums() {
    contextStack.injectResult(cppEnum);

    modelBuilder.finishBuilding(francaInterface);

    CppClass cppClass = modelBuilder.getFirstResult(CppClass.class);
    assertNotNull(cppClass);
    assertFalse(cppClass.enums.isEmpty());
    assertEquals(cppEnum, cppClass.enums.iterator().next());
  }

  @Test
  public void finishBuildingFrancaInterfaceReadsUsings() {
    contextStack.injectResult(cppUsing);

    modelBuilder.finishBuilding(francaInterface);

    CppClass cppClass = modelBuilder.getFirstResult(CppClass.class);
    assertNotNull(cppClass);
    assertEquals(1, cppClass.usings.size());
    assertEquals(cppUsing, cppClass.usings.iterator().next());
  }

  @Test
  public void finishBuildingFrancaMethodReadsReturnTypeData() {
    modelBuilder.finishBuilding(francaMethod);

    CppMethod cppMethod = modelBuilder.getFirstResult(CppMethod.class);
    assertNotNull(cppMethod);
    assertEquals(cppComplexTypeRef, cppMethod.returnType);
    assertTrue(cppMethod.comment.endsWith(RETURN_TYPE_COMMENT));
  }

  @Test
  public void finishBuildingFrancaMethodReadsStaticFlag() {
    when(rootModel.isStatic(francaMethod)).thenReturn(true);

    modelBuilder.finishBuilding(francaMethod);

    CppMethod cppMethod = modelBuilder.getFirstResult(CppMethod.class);
    assertNotNull(cppMethod);
    assertTrue(cppMethod.specifiers.contains(CppMethod.Specifier.STATIC));
  }

  @Test
  public void finishBuildingFrancaMethodReadsInputParameters() {
    CppParameter cppParameter = new CppParameter("flowers", null);
    contextStack.injectResult(cppParameter);

    modelBuilder.finishBuilding(francaMethod);

    CppMethod cppMethod = modelBuilder.getFirstResult(CppMethod.class);
    assertNotNull(cppMethod);

    List<CppParameter> cppParameters = cppMethod.parameters;
    assertFalse(cppParameters.isEmpty());
    assertEquals(cppParameter, cppParameters.get(0));
  }

  @Test
  public void finishBuildingInputArgumentReadsName() {
    modelBuilder.finishBuildingInputArgument(francaArgument);

    CppParameter cppParameter = modelBuilder.getFirstResult(CppParameter.class);
    assertNotNull(cppParameter);
    assertEquals(PARAMETER_NAME, cppParameter.name);
  }

  @Test
  public void finishBuildingInputArgumentMapsType() {
    when(CppMethodMapper.mapArgumentType(any(), any(), any())).thenReturn(cppComplexTypeRef);

    modelBuilder.finishBuildingInputArgument(francaArgument);

    CppParameter cppParameter = modelBuilder.getFirstResult(CppParameter.class);
    assertNotNull(cppParameter);
    assertEquals(cppComplexTypeRef, cppParameter.type);

    PowerMockito.verifyStatic();
    CppMethodMapper.mapArgumentType(same(francaArgument), same(null), same(rootModel));
  }

  @Test
  public void finishBuildingFrancaTypeCollectionReadsStructs() {
    contextStack.injectResult(cppStruct);

    modelBuilder.finishBuilding(francaTypeCollection);

    CppStruct result = modelBuilder.getFirstResult(CppStruct.class);
    assertEquals(cppStruct, result);
  }

  @Test
  public void finishBuildingFrancaTypeCollectionReadsUsings() {
    contextStack.injectResult(cppUsing);

    modelBuilder.finishBuilding(francaTypeCollection);

    CppUsing result = modelBuilder.getFirstResult(CppUsing.class);
    assertEquals(cppUsing, result);
  }

  @Test
  public void finishBuildingFrancaTypeCollectionReadsEnums() {
    contextStack.injectResult(cppEnum);

    modelBuilder.finishBuilding(francaTypeCollection);

    CppEnum result = modelBuilder.getFirstResult(CppEnum.class);
    assertEquals(cppEnum, result);
  }

  @Test
  public void finishBuildingFrancaTypeCollectionReadsConstants() {
    final CppConstant cppConstant = new CppConstant(CONSTANT_NAME, cppComplexTypeRef, cppValue);
    contextStack.injectResult(cppConstant);

    modelBuilder.finishBuilding(francaTypeCollection);

    CppConstant result = modelBuilder.getFirstResult(CppConstant.class);
    assertEquals(cppConstant, result);
  }

  @Test
  public void finishBuildingFrancaConstant() {
    when(CppTypeMapper.map(any(), any(FConstantDef.class))).thenReturn(cppComplexTypeRef);
    when(CppValueMapper.map(any(), any(FInitializerExpression.class))).thenReturn(cppValue);

    modelBuilder.finishBuilding(francaConstant);

    CppConstant cppConstant = modelBuilder.getFirstResult(CppConstant.class);
    assertNotNull(cppConstant);
    assertEquals(CONSTANT_NAME, cppConstant.name.toLowerCase());
    assertEquals(cppComplexTypeRef, cppConstant.type);
    assertEquals(cppValue, cppConstant.value);

    PowerMockito.verifyStatic();
    CppTypeMapper.map(same(rootModel), same(francaConstant));
    PowerMockito.verifyStatic();
    CppValueMapper.map(same(cppComplexTypeRef), same(francaInitializerExpression));
  }

  @Test
  public void finishBuildingFrancaFieldReadsName() {
    when(CppDefaultInitializer.map(any(FTypedElement.class))).thenReturn(cppValue);

    modelBuilder.finishBuilding(francaField);

    CppField cppField = modelBuilder.getFirstResult(CppField.class);
    assertNotNull(cppField);
    assertEquals(FIELD_NAME, cppField.name);
    assertEquals(cppValue, cppField.initializer);

    PowerMockito.verifyStatic();
    CppDefaultInitializer.map(same(francaField));
  }

  @Test
  public void finishBuildingFrancaFieldReadsType() {
    contextStack.injectResult(cppComplexTypeRef);

    modelBuilder.finishBuilding(francaField);

    CppField cppField = modelBuilder.getFirstResult(CppField.class);
    assertNotNull(cppField);
    assertEquals(cppComplexTypeRef, cppField.type);
  }

  @Test
  public void finishBuildingFrancaStructTypeReadsName() {
    modelBuilder.finishBuilding(francaStructType);

    CppStruct cppStruct = modelBuilder.getFirstResult(CppStruct.class);
    assertNotNull(cppStruct);
    assertEquals(STRUCT_NAME, cppStruct.name.toLowerCase());
  }

  @Test
  public void finishBuildingFrancaStructTypeReadsFields() {
    final CppField cppField = new CppField(cppComplexTypeRef, FIELD_NAME);
    contextStack.injectResult(cppField);

    modelBuilder.finishBuilding(francaStructType);

    CppStruct cppStruct = modelBuilder.getFirstResult(CppStruct.class);
    assertNotNull(cppStruct);
    assertFalse(cppStruct.fields.isEmpty());
    assertEquals(cppField, cppStruct.fields.get(0));
  }

  @Test
  public void finishBuildingFrancaTypeDefInstanceId() {
    when(InstanceRules.isInstanceId(any())).thenReturn(true);

    modelBuilder.finishBuilding(francaTypeDef);

    CppUsing cppUsing = modelBuilder.getFirstResult(CppUsing.class);
    assertNull(cppUsing);
  }

  @Test
  public void finishBuildingFrancaTypeDefNotInstanceId() {
    when(CppTypeMapper.map(any(), any(FTypeRef.class))).thenReturn(cppComplexTypeRef);

    modelBuilder.finishBuilding(francaTypeDef);

    CppUsing cppUsing = modelBuilder.getFirstResult(CppUsing.class);
    assertNotNull(cppUsing);
    assertEquals(TYPE_DEF_NAME, cppUsing.name.toLowerCase());
    assertEquals(cppComplexTypeRef, cppUsing.definition);

    PowerMockito.verifyStatic();
    CppTypeMapper.map(rootModel, francaTypeRef);
  }

  @Test
  public void finishBuildingFrancaArrayType() {
    when(CppTypeMapper.mapArray(any(), any())).thenReturn(cppComplexTypeRef);

    modelBuilder.finishBuilding(francaArrayType);

    CppUsing cppUsing = modelBuilder.getFirstResult(CppUsing.class);
    assertNotNull(cppUsing);
    assertEquals(ARRAY_NAME, cppUsing.name.toLowerCase());
    assertEquals(cppComplexTypeRef, cppUsing.definition);

    PowerMockito.verifyStatic();
    CppTypeMapper.mapArray(rootModel, francaArrayType);
  }

  @Test
  public void finishBuildingFrancaMapType() {
    when(CppTypeMapper.mapMapType(any(), any())).thenReturn(cppComplexTypeRef);
    when(DefinedBy.createFromFModelElement(any())).thenReturn(mock(DefinedBy.class));

    modelBuilder.finishBuilding(francaMapType);

    CppUsing cppUsing = modelBuilder.getFirstResult(CppUsing.class);
    assertNotNull(cppUsing);
    assertEquals(MAP_NAME, cppUsing.name.toLowerCase());
    assertEquals(cppComplexTypeRef, cppUsing.definition);

    PowerMockito.verifyStatic();
    CppTypeMapper.mapMapType(rootModel, francaMapType);
  }

  @Test
  public void finishBuildingFrancaTypeRef() {
    when(CppTypeMapper.map(any(), any(FTypeRef.class))).thenReturn(cppComplexTypeRef);

    modelBuilder.finishBuilding(francaTypeRef);

    CppComplexTypeRef result = modelBuilder.getFirstResult(CppComplexTypeRef.class);
    assertEquals(cppComplexTypeRef, result);

    PowerMockito.verifyStatic();
    CppTypeMapper.map(same(rootModel), same(francaTypeRef));
  }

  @Test
  public void finishBuildingFrancaEnumerationTypeReadsName() {
    modelBuilder.finishBuilding(francaEnumerationType);

    CppEnum cppEnum = modelBuilder.getFirstResult(CppEnum.class);
    assertNotNull(cppEnum);
    assertEquals(ENUM_NAME, cppEnum.name.toLowerCase());
  }

  @Test
  public void finishBuildingFrancaEnumerationTypeReadsEnumItems() {
    CppEnumItem cppEnumItem = new CppEnumItem("enumerated");
    contextStack.injectResult(cppEnumItem);

    modelBuilder.finishBuilding(francaEnumerationType);

    CppEnum cppEnum = modelBuilder.getFirstResult(CppEnum.class);
    assertNotNull(cppEnum);
    assertFalse(cppEnum.items.isEmpty());
    assertEquals(cppEnumItem, cppEnum.items.get(0));
  }

  @Test
  public void finishBuildingFrancaEnumerator() {
    modelBuilder.finishBuilding(francaEnumerator);

    CppEnumItem cppEnumItem = modelBuilder.getFirstResult(CppEnumItem.class);
    assertNotNull(cppEnumItem);
    assertEquals(ENUM_ITEM_NAME, cppEnumItem.name.toLowerCase());
  }

  @Test
  public void finishBuildingFrancaExpression() {
    when(CppValueMapper.map(any(FExpression.class))).thenReturn(cppValue);

    modelBuilder.finishBuilding(francaExpression);

    CppValue result = modelBuilder.getFirstResult(CppValue.class);
    assertEquals(cppValue, result);

    PowerMockito.verifyStatic();
    CppValueMapper.map(francaExpression);
  }

  @Test
  public void finishBuildingFrancaUnionTypeReadsName() {
    modelBuilder.finishBuilding(francaUnionType);

    CppTaggedUnion cppTaggedUnion = modelBuilder.getFirstResult(CppTaggedUnion.class);
    assertNotNull(cppTaggedUnion);
    assertEquals(UNION_NAME, cppTaggedUnion.name.toLowerCase());
  }

  @Test
  public void finishBuildingFrancaUnionTypeReadsFields() {
    final CppField cppField = new CppField(cppComplexTypeRef, FIELD_NAME);
    contextStack.injectResult(cppField);

    modelBuilder.finishBuilding(francaUnionType);

    CppStruct cppStruct = modelBuilder.getFirstResult(CppStruct.class);
    assertNotNull(cppStruct);
    assertFalse(cppStruct.fields.isEmpty());
    assertEquals(cppField, cppStruct.fields.get(0));
  }
}
