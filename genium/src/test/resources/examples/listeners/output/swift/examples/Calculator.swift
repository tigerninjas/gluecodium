//
//
// Automatically generated. Do not modify. Your changes will be lost.

import Foundation



internal func getRef(_ ref: Calculator?) -> RefHolder {
    return RefHolder(ref?.c_instance ?? 0)
}
public class Calculator {
    let c_instance : _baseRef

    public init?(cCalculator: _baseRef) {
        guard cCalculator != 0 else {
            return nil
        }
        c_instance = cCalculator
    }

    deinit {
        examples_Calculator_release(c_instance)
    }
    public static func registerListener(listener: CalculatorListener) -> Void {
        let listenerHandle = getRef(listener)
        return examples_Calculator_registerListener(listenerHandle.ref)
    }

    public static func unregisterListener(listener: CalculatorListener) -> Void {
        let listenerHandle = getRef(listener)
        return examples_Calculator_unregisterListener(listenerHandle.ref)
    }

    public static func calculate() -> Void {
        return examples_Calculator_calculate()
    }

}

extension Calculator: NativeBase {
    var c_handle: _baseRef { return c_instance }
}