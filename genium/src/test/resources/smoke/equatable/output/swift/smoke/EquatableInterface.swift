//
//
// Automatically generated. Do not modify. Your changes will be lost.

import Foundation

internal func getRef(_ ref: EquatableInterface?) -> RefHolder {
    return RefHolder(ref?.c_instance ?? 0)
}

public class EquatableInterface {
    let c_instance : _baseRef
    public init?(cEquatableInterface: _baseRef) {
        guard cEquatableInterface != 0 else {
            return nil
        }
        c_instance = cEquatableInterface
    }
    deinit {
        smoke_EquatableInterface_release(c_instance)
    }

    public struct EquatableStruct: Equatable {
        public var intField: Int32
        public var stringField: String
        public init(intField: Int32, stringField: String) {
            self.intField = intField
            self.stringField = stringField
        }
        internal init?(cEquatableStruct: _baseRef) {
            intField = smoke_EquatableInterface_EquatableStruct_intField_get(cEquatableStruct)
            do {
                let stringField_handle = smoke_EquatableInterface_EquatableStruct_stringField_get(cEquatableStruct)
                defer {
                    std_string_release(stringField_handle)
                }
                stringField = String(cString: std_string_data_get(stringField_handle))
            }
        }
        internal func convertToCType() -> _baseRef {
            let result = smoke_EquatableInterface_EquatableStruct_create()
            fillFunction(result)
            return result
        }
        internal func fillFunction(_ cEquatableStruct: _baseRef) -> Void {
            smoke_EquatableInterface_EquatableStruct_intField_set(cEquatableStruct, intField)
            smoke_EquatableInterface_EquatableStruct_stringField_set(cEquatableStruct, stringField)
        }
    }
}

extension EquatableInterface: NativeBase {
    var c_handle: _baseRef { return c_instance }
}