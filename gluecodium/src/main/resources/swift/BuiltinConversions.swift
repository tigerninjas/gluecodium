// -------------------------------------------------------------------------------------------------
// Copyright (C) 2016-2019 HERE Europe B.V.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// SPDX-License-Identifier: Apache-2.0
// License-Filename: LICENSE
//
// -------------------------------------------------------------------------------------------------

import Foundation

struct PrimitiveHolder<T> {
    let ref: T

    init(_ ref: T) {
        self.ref = ref
    }
}

extension String {
    internal func convertToCType() -> _baseRef {
        let result = std_string_create_handle(self)
        precondition(result != 0, "Out of memory")
        return result
    }
}

internal func copyFromCType(_ handle: _baseRef) -> String {
    return String(data: Data(bytes: std_string_data_get(handle),
                  count: Int(std_string_size_get(handle))), encoding: .utf8)!
}

internal func moveFromCType(_ handle: _baseRef) -> String {
    defer {
        std_string_release_handle(handle)
    }
    return copyFromCType(handle)
}

internal func copyToCType(_ swiftType: String) -> RefHolder {
    return RefHolder(std_string_create_handle(swiftType))
}

internal func moveToCType(_ swiftType: String) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref, release: std_string_release_handle)
}

internal func copyFromCType(_ handle: _baseRef) -> String? {
    guard handle != 0 else {
        return nil
    }
    let unwrappedHandle = std_string_unwrap_optional_handle(handle)
    return copyFromCType(unwrappedHandle) as String
}

internal func moveFromCType(_ handle: _baseRef) -> String? {
    defer {
        std_string_release_optional_handle(handle)
    }
    return copyFromCType(handle)
}

internal func copyToCType(_ swiftType: String?) -> RefHolder {
    guard let swiftType = swiftType else {
        return RefHolder(0)
    }
    return RefHolder(std_string_create_optional_handle(swiftType))
}

internal func moveToCType(_ swiftType: String?) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref, release: std_string_release_optional_handle)
}

internal func copyFromCType(_ handle: _baseRef) -> Data {
    guard let byteArrayData = byteArray_data_get(handle) else {
        return Data()
    }
    return Data(bytes: byteArrayData, count: Int(byteArray_size_get(handle)))
}

internal func moveFromCType(_ handle: _baseRef) -> Data {
    defer {
        byteArray_release_handle(handle)
    }
    return copyFromCType(handle)
}

internal func copyToCType(_ swiftType: Data) -> RefHolder {
    let handle = byteArray_create_handle()
    swiftType.withUnsafeBytes { (ptr: UnsafeRawBufferPointer) in
        let uint8ptr = ptr.baseAddress?.assumingMemoryBound(to: UInt8.self)
        byteArray_assign(handle, uint8ptr, ptr.count)
    }
    return RefHolder(handle)
}

internal func moveToCType(_ swiftType: Data) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref, release: byteArray_release_handle)
}

internal func copyFromCType(_ handle: _baseRef) -> Data? {
    guard handle != 0 else {
        return nil
    }
    let unwrappedHandle = byteArray_unwrap_optional_handle(handle)
    return copyFromCType(unwrappedHandle) as Data
}

internal func moveFromCType(_ handle: _baseRef) -> Data? {
    defer {
        byteArray_release_optional_handle(handle)
    }
    return copyFromCType(handle)
}

internal func copyToCType(_ swiftType: Data?) -> RefHolder {
    guard let swiftType = swiftType else {
        return RefHolder(0)
    }
    let handle = byteArray_create_optional_handle()
    swiftType.withUnsafeBytes { (ptr: UnsafeRawBufferPointer) in
        let uint8ptr = ptr.baseAddress?.assumingMemoryBound(to: UInt8.self)
        byteArray_assign_optional(handle, uint8ptr, swiftType.count)
    }
    return RefHolder(handle)
}

internal func moveToCType(_ swiftType: Data?) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref, release: byteArray_release_optional_handle)
}

internal func copyFromCType(_ seconds_since_epoch: Double) -> Date {
    return Date(timeIntervalSince1970: seconds_since_epoch)
}

internal func moveFromCType(_ seconds_since_epoch: Double) -> Date {
    return copyFromCType(seconds_since_epoch)
}

internal func copyToCType(_ swiftType: Date) -> PrimitiveHolder<Double> {
    return PrimitiveHolder(swiftType.timeIntervalSince1970)
}

internal func moveToCType(_ swiftType: Date) -> PrimitiveHolder<Double> {
    return copyToCType(swiftType)
}

internal func copyFromCType(_ handle: _baseRef) -> Date? {
    guard handle != 0 else {
        return nil
    }
    let unwrappedHandle = chrono_time_point_unwrap_optional_handle(handle)
    return copyFromCType(unwrappedHandle) as Date
}

internal func moveFromCType(_ handle: _baseRef) -> Date? {
    defer {
        chrono_time_point_release_optional_handle(handle)
    }
    return copyFromCType(handle)
}

internal func copyToCType(_ swiftType: Date?) -> RefHolder {
    guard let swiftType = swiftType else {
        return RefHolder(0)
    }
    return RefHolder(chrono_time_point_create_optional_handle(copyToCType(swiftType).ref))
}

internal func moveToCType(_ swiftType: Date?) -> RefHolder {
    return RefHolder(ref: copyToCType(swiftType).ref,
                     release: chrono_time_point_release_optional_handle)
}

// catch primitive types
internal func copyFromCType<T>(_ primitive: T) -> T {
    return primitive
}

// catch primitive types
internal func moveFromCType<T>(_ primitive: T) -> T {
    return primitive
}

// catch primitive types
internal func copyToCType<T>(_ primitive: T) -> PrimitiveHolder<T> {
    return PrimitiveHolder(primitive)
}

// catch primitive types
internal func moveToCType<T>(_ primitive: T) -> PrimitiveHolder<T> {
    return PrimitiveHolder(primitive)
}
