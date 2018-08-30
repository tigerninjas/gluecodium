//
//
// Automatically generated. Do not modify. Your changes will be lost.
import Foundation
internal func getRef(_ ref: ObjcInterface?) -> RefHolder {
    guard let reference = ref else {
        return RefHolder(0)
    }
    if let instanceReference = reference as? NativeBase {
        return RefHolder(instanceReference.c_handle)
    }
    var functions = smoke_ObjcInterface_FunctionTable()
    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(reference).toOpaque()
    functions.release = {swift_class_pointer in
        if let swift_class = swift_class_pointer {
            Unmanaged<AnyObject>.fromOpaque(swift_class).release()
        }
    }
    let proxy = smoke_ObjcInterface_createProxy(functions)
    return RefHolder(ref: proxy, release: smoke_ObjcInterface_release)
}
@objc
public protocol ObjcInterface : AnyObject {
}
internal class _ObjcInterface: ObjcInterface {
    let c_instance : _baseRef
    init?(cObjcInterface: _baseRef) {
        guard cObjcInterface != 0 else {
            return nil
        }
        c_instance = cObjcInterface
    }
    deinit {
        smoke_ObjcInterface_release(c_instance)
    }
}
extension _ObjcInterface: NativeBase {
    var c_handle: _baseRef { return c_instance }
}