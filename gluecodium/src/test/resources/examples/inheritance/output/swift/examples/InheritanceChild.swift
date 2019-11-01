//
//
import Foundation
public protocol InheritanceChild : InheritanceParent {
    func parentMethod(input: String) -> String
    func childMethod(input: UInt8) -> Int16
}
internal class _InheritanceChild: InheritanceChild {
    let c_instance : _baseRef
    init(cInheritanceChild: _baseRef) {
        guard cInheritanceChild != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cInheritanceChild
    }
    deinit {
        examples_InheritanceChild_release_handle(c_instance)
    }
    public func parentMethod(input: String) -> String {
        let c_input = moveToCType(input)
        return moveFromCType(examples_InheritanceParent_parentMethod(self.c_instance, c_input.ref))
    }
    public func childMethod(input: UInt8) -> Int16 {
        let c_input = moveToCType(input)
        return moveFromCType(examples_InheritanceChild_childMethod(self.c_instance, c_input.ref))
    }
}
@_cdecl("_CBridgeInitexamples_InheritanceChild")
internal func _CBridgeInitexamples_InheritanceChild(handle: _baseRef) -> UnsafeMutableRawPointer {
    let reference = _InheritanceChild(cInheritanceChild: handle)
    return Unmanaged<AnyObject>.passRetained(reference).toOpaque()
}
internal func getRef(_ ref: InheritanceChild?, owning: Bool = true) -> RefHolder {
    guard let reference = ref else {
        return RefHolder(0)
    }
    if let instanceReference = reference as? NativeBase {
        let handle_copy = examples_InheritanceChild_copy_handle(instanceReference.c_handle)
        return owning
            ? RefHolder(ref: handle_copy, release: examples_InheritanceChild_release_handle)
            : RefHolder(handle_copy)
    }
    var functions = examples_InheritanceChild_FunctionTable()
    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(reference).toOpaque()
    functions.release = {swift_class_pointer in
        if let swift_class = swift_class_pointer {
            Unmanaged<AnyObject>.fromOpaque(swift_class).release()
        }
    }
    functions.examples_InheritanceParent_parentMethod = {(swift_class_pointer, input) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InheritanceChild
        return copyToCType(swift_class.parentMethod(input: moveFromCType(input))).ref
    }
    functions.examples_InheritanceChild_childMethod = {(swift_class_pointer, input) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InheritanceChild
        return copyToCType(swift_class.childMethod(input: moveFromCType(input))).ref
    }
    let proxy = examples_InheritanceChild_create_proxy(functions)
    return owning ? RefHolder(ref: proxy, release: examples_InheritanceChild_release_handle) : RefHolder(proxy)
}
extension _InheritanceChild: NativeBase {
    var c_handle: _baseRef { return c_instance }
}
internal func InheritanceChild_copyFromCType(_ handle: _baseRef) -> InheritanceChild {
    if let swift_pointer = examples_InheritanceChild_get_swift_object_from_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? InheritanceChild {
        return re_constructed
    }
    if let swift_pointer = examples_InheritanceChild_get_typed(examples_InheritanceChild_copy_handle(handle)),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? InheritanceChild {
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func InheritanceChild_moveFromCType(_ handle: _baseRef) -> InheritanceChild {
    if let swift_pointer = examples_InheritanceChild_get_swift_object_from_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? InheritanceChild {
        examples_InheritanceChild_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = examples_InheritanceChild_get_typed(handle),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? InheritanceChild {
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func InheritanceChild_copyFromCType(_ handle: _baseRef) -> InheritanceChild? {
    guard handle != 0 else {
        return nil
    }
    return InheritanceChild_moveFromCType(handle) as InheritanceChild
}
internal func InheritanceChild_moveFromCType(_ handle: _baseRef) -> InheritanceChild? {
    guard handle != 0 else {
        return nil
    }
    return InheritanceChild_moveFromCType(handle) as InheritanceChild
}
internal func copyToCType(_ swiftClass: InheritanceChild) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: InheritanceChild) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: InheritanceChild?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: InheritanceChild?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}