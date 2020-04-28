//
//
import Foundation
public class EquatableClass {
    let c_instance : _baseRef
    init(cEquatableClass: _baseRef) {
        guard cEquatableClass != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cEquatableClass
    }
    deinit {
        smoke_EquatableClass_release_handle(c_instance)
    }
}
internal func getRef(_ ref: EquatableClass?, owning: Bool = true) -> RefHolder {
    guard let c_handle = ref?.c_instance else {
        return RefHolder(0)
    }
    let handle_copy = smoke_EquatableClass_copy_handle(c_handle)
    return owning
        ? RefHolder(ref: handle_copy, release: smoke_EquatableClass_release_handle)
        : RefHolder(handle_copy)
}
extension EquatableClass: NativeBase {
    var c_handle: _baseRef { return c_instance }
}
extension EquatableClass: Hashable {
    public static func == (lhs: EquatableClass, rhs: EquatableClass) -> Bool {
        return smoke_EquatableClass_equal(lhs.c_handle, rhs.c_handle)
    }
    public func hash(into hasher: inout Hasher) {
        hasher.combine(smoke_EquatableClass_hash(c_handle))
    }
}
internal func EquatableClass_copyFromCType(_ handle: _baseRef) -> EquatableClass {
    if let swift_pointer = smoke_EquatableClass_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? EquatableClass {
        return re_constructed
    }
    let result = EquatableClass(cEquatableClass: smoke_EquatableClass_copy_handle(handle))
    smoke_EquatableClass_cache_swift_object_wrapper(handle, Unmanaged<AnyObject>.passUnretained(result).toOpaque())
    return result
}
internal func EquatableClass_moveFromCType(_ handle: _baseRef) -> EquatableClass {
    if let swift_pointer = smoke_EquatableClass_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? EquatableClass {
        return re_constructed
    }
    let result = EquatableClass(cEquatableClass: handle)
    smoke_EquatableClass_cache_swift_object_wrapper(handle, Unmanaged<AnyObject>.passUnretained(result).toOpaque())
    return result
}
internal func EquatableClass_copyFromCType(_ handle: _baseRef) -> EquatableClass? {
    guard handle != 0 else {
        return nil
    }
    return EquatableClass_moveFromCType(handle) as EquatableClass
}
internal func EquatableClass_moveFromCType(_ handle: _baseRef) -> EquatableClass? {
    guard handle != 0 else {
        return nil
    }
    return EquatableClass_moveFromCType(handle) as EquatableClass
}
internal func copyToCType(_ swiftClass: EquatableClass) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: EquatableClass) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: EquatableClass?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: EquatableClass?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
