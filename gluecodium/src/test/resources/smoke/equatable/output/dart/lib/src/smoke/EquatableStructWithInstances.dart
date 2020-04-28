import 'dart:collection';
import 'package:collection/collection.dart';
import 'package:library/src/smoke/EquatableClass.dart';
import 'package:library/src/smoke/EquatableInterface.dart';
import 'package:library/src/smoke/NonEquatableClass.dart';
import 'package:library/src/smoke/NonEquatableInterface.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
class EquatableStructWithInstances {
  EquatableClass equatableClass;
  NonEquatableClass nonEquatableClass;
  EquatableInterface equatableInterface;
  NonEquatableInterface nonEquatableInterface;
  EquatableClass equatableClassNullable;
  NonEquatableClass nonEquatableClassNullable;
  EquatableInterface equatableInterfaceNullable;
  NonEquatableInterface nonEquatableInterfaceNullable;
  EquatableStructWithInstances(this.equatableClass, this.nonEquatableClass, this.equatableInterface, this.nonEquatableInterface, this.equatableClassNullable, this.nonEquatableClassNullable, this.equatableInterfaceNullable, this.nonEquatableInterfaceNullable);
  @override
  bool operator ==(dynamic other) {
    if (identical(this, other)) return true;
    if (other is! EquatableStructWithInstances) return false;
    EquatableStructWithInstances _other = other;
    return equatableClass == _other.equatableClass &&
        nonEquatableClass == _other.nonEquatableClass &&
        equatableInterface == _other.equatableInterface &&
        nonEquatableInterface == _other.nonEquatableInterface &&
        equatableClassNullable == _other.equatableClassNullable &&
        nonEquatableClassNullable == _other.nonEquatableClassNullable &&
        equatableInterfaceNullable == _other.equatableInterfaceNullable &&
        nonEquatableInterfaceNullable == _other.nonEquatableInterfaceNullable;
  }
  @override
  int get hashCode {
    int result = 7;
    result = 31 * result + equatableClass.hashCode;
    result = 31 * result + nonEquatableClass.hashCode;
    result = 31 * result + equatableInterface.hashCode;
    result = 31 * result + nonEquatableInterface.hashCode;
    result = 31 * result + equatableClassNullable.hashCode;
    result = 31 * result + nonEquatableClassNullable.hashCode;
    result = 31 * result + equatableInterfaceNullable.hashCode;
    result = 31 * result + nonEquatableInterfaceNullable.hashCode;
    return result;
  }
}
// EquatableStructWithInstances "private" section, not exported.
final _smoke_EquatableStructWithInstances_create_handle = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>, Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_create_handle');
final _smoke_EquatableStructWithInstances_release_handle = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_release_handle');
final _smoke_EquatableStructWithInstances_get_field_equatableClass = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_equatableClass');
final _smoke_EquatableStructWithInstances_get_field_nonEquatableClass = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_nonEquatableClass');
final _smoke_EquatableStructWithInstances_get_field_equatableInterface = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_equatableInterface');
final _smoke_EquatableStructWithInstances_get_field_nonEquatableInterface = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_nonEquatableInterface');
final _smoke_EquatableStructWithInstances_get_field_equatableClassNullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_equatableClassNullable');
final _smoke_EquatableStructWithInstances_get_field_nonEquatableClassNullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_nonEquatableClassNullable');
final _smoke_EquatableStructWithInstances_get_field_equatableInterfaceNullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_equatableInterfaceNullable');
final _smoke_EquatableStructWithInstances_get_field_nonEquatableInterfaceNullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_field_nonEquatableInterfaceNullable');
Pointer<Void> smoke_EquatableStructWithInstances_toFfi(EquatableStructWithInstances value) {
  final _equatableClass_handle = smoke_EquatableClass_toFfi(value.equatableClass);
  final _nonEquatableClass_handle = smoke_NonEquatableClass_toFfi(value.nonEquatableClass);
  final _equatableInterface_handle = smoke_EquatableInterface_toFfi(value.equatableInterface);
  final _nonEquatableInterface_handle = smoke_NonEquatableInterface_toFfi(value.nonEquatableInterface);
  final _equatableClassNullable_handle = smoke_EquatableClass_toFfi_nullable(value.equatableClassNullable);
  final _nonEquatableClassNullable_handle = smoke_NonEquatableClass_toFfi_nullable(value.nonEquatableClassNullable);
  final _equatableInterfaceNullable_handle = smoke_EquatableInterface_toFfi_nullable(value.equatableInterfaceNullable);
  final _nonEquatableInterfaceNullable_handle = smoke_NonEquatableInterface_toFfi_nullable(value.nonEquatableInterfaceNullable);
  final _result = _smoke_EquatableStructWithInstances_create_handle(_equatableClass_handle, _nonEquatableClass_handle, _equatableInterface_handle, _nonEquatableInterface_handle, _equatableClassNullable_handle, _nonEquatableClassNullable_handle, _equatableInterfaceNullable_handle, _nonEquatableInterfaceNullable_handle);
  smoke_EquatableClass_releaseFfiHandle(_equatableClass_handle);
  smoke_NonEquatableClass_releaseFfiHandle(_nonEquatableClass_handle);
  smoke_EquatableInterface_releaseFfiHandle(_equatableInterface_handle);
  smoke_NonEquatableInterface_releaseFfiHandle(_nonEquatableInterface_handle);
  smoke_EquatableClass_releaseFfiHandle_nullable(_equatableClassNullable_handle);
  smoke_NonEquatableClass_releaseFfiHandle_nullable(_nonEquatableClassNullable_handle);
  smoke_EquatableInterface_releaseFfiHandle_nullable(_equatableInterfaceNullable_handle);
  smoke_NonEquatableInterface_releaseFfiHandle_nullable(_nonEquatableInterfaceNullable_handle);
  return _result;
}
EquatableStructWithInstances smoke_EquatableStructWithInstances_fromFfi(Pointer<Void> handle) {
  final _equatableClass_handle = _smoke_EquatableStructWithInstances_get_field_equatableClass(handle);
  final _nonEquatableClass_handle = _smoke_EquatableStructWithInstances_get_field_nonEquatableClass(handle);
  final _equatableInterface_handle = _smoke_EquatableStructWithInstances_get_field_equatableInterface(handle);
  final _nonEquatableInterface_handle = _smoke_EquatableStructWithInstances_get_field_nonEquatableInterface(handle);
  final _equatableClassNullable_handle = _smoke_EquatableStructWithInstances_get_field_equatableClassNullable(handle);
  final _nonEquatableClassNullable_handle = _smoke_EquatableStructWithInstances_get_field_nonEquatableClassNullable(handle);
  final _equatableInterfaceNullable_handle = _smoke_EquatableStructWithInstances_get_field_equatableInterfaceNullable(handle);
  final _nonEquatableInterfaceNullable_handle = _smoke_EquatableStructWithInstances_get_field_nonEquatableInterfaceNullable(handle);
  final _result = EquatableStructWithInstances(
    smoke_EquatableClass_fromFfi(_equatableClass_handle),
    smoke_NonEquatableClass_fromFfi(_nonEquatableClass_handle),
    smoke_EquatableInterface_fromFfi(_equatableInterface_handle),
    smoke_NonEquatableInterface_fromFfi(_nonEquatableInterface_handle),
    smoke_EquatableClass_fromFfi_nullable(_equatableClassNullable_handle),
    smoke_NonEquatableClass_fromFfi_nullable(_nonEquatableClassNullable_handle),
    smoke_EquatableInterface_fromFfi_nullable(_equatableInterfaceNullable_handle),
    smoke_NonEquatableInterface_fromFfi_nullable(_nonEquatableInterfaceNullable_handle)
  );
  smoke_EquatableClass_releaseFfiHandle(_equatableClass_handle);
  smoke_NonEquatableClass_releaseFfiHandle(_nonEquatableClass_handle);
  smoke_EquatableInterface_releaseFfiHandle(_equatableInterface_handle);
  smoke_NonEquatableInterface_releaseFfiHandle(_nonEquatableInterface_handle);
  smoke_EquatableClass_releaseFfiHandle_nullable(_equatableClassNullable_handle);
  smoke_NonEquatableClass_releaseFfiHandle_nullable(_nonEquatableClassNullable_handle);
  smoke_EquatableInterface_releaseFfiHandle_nullable(_equatableInterfaceNullable_handle);
  smoke_NonEquatableInterface_releaseFfiHandle_nullable(_nonEquatableInterfaceNullable_handle);
  return _result;
}
void smoke_EquatableStructWithInstances_releaseFfiHandle(Pointer<Void> handle) => _smoke_EquatableStructWithInstances_release_handle(handle);
// Nullable EquatableStructWithInstances
final _smoke_EquatableStructWithInstances_create_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_create_handle_nullable');
final _smoke_EquatableStructWithInstances_release_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_release_handle_nullable');
final _smoke_EquatableStructWithInstances_get_value_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_EquatableStructWithInstances_get_value_nullable');
Pointer<Void> smoke_EquatableStructWithInstances_toFfi_nullable(EquatableStructWithInstances value) {
  if (value == null) return Pointer<Void>.fromAddress(0);
  final _handle = smoke_EquatableStructWithInstances_toFfi(value);
  final result = _smoke_EquatableStructWithInstances_create_handle_nullable(_handle);
  smoke_EquatableStructWithInstances_releaseFfiHandle(_handle);
  return result;
}
EquatableStructWithInstances smoke_EquatableStructWithInstances_fromFfi_nullable(Pointer<Void> handle) {
  if (handle.address == 0) return null;
  final _handle = _smoke_EquatableStructWithInstances_get_value_nullable(handle);
  final result = smoke_EquatableStructWithInstances_fromFfi(_handle);
  smoke_EquatableStructWithInstances_releaseFfiHandle(_handle);
  return result;
}
void smoke_EquatableStructWithInstances_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_EquatableStructWithInstances_release_handle_nullable(handle);
// End of EquatableStructWithInstances "private" section.
