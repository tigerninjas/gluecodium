//
//
import Foundation
public struct EquatableStructWithInstances: Hashable {
    public var equatableClass: EquatableClass
    public var nonEquatableClass: NonEquatableClass
    public var equatableInterface: EquatableInterface
    public var nonEquatableInterface: NonEquatableInterface
    public var equatableClassNullable: EquatableClass?
    public var nonEquatableClassNullable: NonEquatableClass?
    public var equatableInterfaceNullable: EquatableInterface?
    public var nonEquatableInterfaceNullable: NonEquatableInterface?
    public init(equatableClass: EquatableClass, nonEquatableClass: NonEquatableClass, equatableInterface: EquatableInterface, nonEquatableInterface: NonEquatableInterface, equatableClassNullable: EquatableClass? = nil, nonEquatableClassNullable: NonEquatableClass? = nil, equatableInterfaceNullable: EquatableInterface? = nil, nonEquatableInterfaceNullable: NonEquatableInterface? = nil) {
        self.equatableClass = equatableClass
        self.nonEquatableClass = nonEquatableClass
        self.equatableInterface = equatableInterface
        self.nonEquatableInterface = nonEquatableInterface
        self.equatableClassNullable = equatableClassNullable
        self.nonEquatableClassNullable = nonEquatableClassNullable
        self.equatableInterfaceNullable = equatableInterfaceNullable
        self.nonEquatableInterfaceNullable = nonEquatableInterfaceNullable
    }
    internal init(cHandle: _baseRef) {
        equatableClass = EquatableClass_moveFromCType(smoke_EquatableStructWithInstances_equatableClass_get(cHandle))
        nonEquatableClass = NonEquatableClass_moveFromCType(smoke_EquatableStructWithInstances_nonEquatableClass_get(cHandle))
        equatableInterface = EquatableInterface_moveFromCType(smoke_EquatableStructWithInstances_equatableInterface_get(cHandle))
        nonEquatableInterface = NonEquatableInterface_moveFromCType(smoke_EquatableStructWithInstances_nonEquatableInterface_get(cHandle))
        equatableClassNullable = EquatableClass_moveFromCType(smoke_EquatableStructWithInstances_equatableClassNullable_get(cHandle))
        nonEquatableClassNullable = NonEquatableClass_moveFromCType(smoke_EquatableStructWithInstances_nonEquatableClassNullable_get(cHandle))
        equatableInterfaceNullable = EquatableInterface_moveFromCType(smoke_EquatableStructWithInstances_equatableInterfaceNullable_get(cHandle))
        nonEquatableInterfaceNullable = NonEquatableInterface_moveFromCType(smoke_EquatableStructWithInstances_nonEquatableInterfaceNullable_get(cHandle))
    }
}
internal func copyFromCType(_ handle: _baseRef) -> EquatableStructWithInstances {
    return EquatableStructWithInstances(cHandle: handle)
}
internal func moveFromCType(_ handle: _baseRef) -> EquatableStructWithInstances {
    defer {
        smoke_EquatableStructWithInstances_release_handle(handle)
    }
    return copyFromCType(handle)
}
internal func copyToCType(_ swiftType: EquatableStructWithInstances) -> RefHolder {
    let c_equatableClass = moveToCType(swiftType.equatableClass)
    let c_nonEquatableClass = moveToCType(swiftType.nonEquatableClass)
    let c_equatableInterface = moveToCType(swiftType.equatableInterface)
    let c_nonEquatableInterface = moveToCType(swiftType.nonEquatableInterface)
    let c_equatableClassNullable = moveToCType(swiftType.equatableClassNullable)
    let c_nonEquatableClassNullable = moveToCType(swiftType.nonEquatableClassNullable)
    let c_equatableInterfaceNullable = moveToCType(swiftType.equatableInterfaceNullable)
    let c_nonEquatableInterfaceNullable = moveToCType(swiftType.nonEquatableInterfaceNullable)
    return RefHolder(smoke_EquatableStructWithInstances_create_handle(c_equatableClass.ref, c_nonEquatableClass.ref, c_equatableInterface.ref, c_nonEquatableInterface.ref, c_equatableClassNullable.ref, c_nonEquatableClassNullable.ref, c_equatableInterfaceNullable.ref, c_nonEquatableInterfaceNullable.ref))
}
internal func moveToCType(_ swiftType: EquatableStructWithInstances) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref, release: smoke_EquatableStructWithInstances_release_handle)
}
internal func copyFromCType(_ handle: _baseRef) -> EquatableStructWithInstances? {
    guard handle != 0 else {
        return nil
    }
    let unwrappedHandle = smoke_EquatableStructWithInstances_unwrap_optional_handle(handle)
    return EquatableStructWithInstances(cHandle: unwrappedHandle) as EquatableStructWithInstances
}
internal func moveFromCType(_ handle: _baseRef) -> EquatableStructWithInstances? {
    defer {
        smoke_EquatableStructWithInstances_release_optional_handle(handle)
    }
    return copyFromCType(handle)
}
internal func copyToCType(_ swiftType: EquatableStructWithInstances?) -> RefHolder {
    guard let swiftType = swiftType else {
        return RefHolder(0)
    }
    let c_equatableClass = moveToCType(swiftType.equatableClass)
    let c_nonEquatableClass = moveToCType(swiftType.nonEquatableClass)
    let c_equatableInterface = moveToCType(swiftType.equatableInterface)
    let c_nonEquatableInterface = moveToCType(swiftType.nonEquatableInterface)
    let c_equatableClassNullable = moveToCType(swiftType.equatableClassNullable)
    let c_nonEquatableClassNullable = moveToCType(swiftType.nonEquatableClassNullable)
    let c_equatableInterfaceNullable = moveToCType(swiftType.equatableInterfaceNullable)
    let c_nonEquatableInterfaceNullable = moveToCType(swiftType.nonEquatableInterfaceNullable)
    return RefHolder(smoke_EquatableStructWithInstances_create_optional_handle(c_equatableClass.ref, c_nonEquatableClass.ref, c_equatableInterface.ref, c_nonEquatableInterface.ref, c_equatableClassNullable.ref, c_nonEquatableClassNullable.ref, c_equatableInterfaceNullable.ref, c_nonEquatableInterfaceNullable.ref))
}
internal func moveToCType(_ swiftType: EquatableStructWithInstances?) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref, release: smoke_EquatableStructWithInstances_release_optional_handle)
}
