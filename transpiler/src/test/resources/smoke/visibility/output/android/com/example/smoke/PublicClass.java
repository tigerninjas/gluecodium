/*
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */

package com.example.smoke;

import com.example.NativeBase;

public class PublicClass extends NativeBase {

    enum InternalEnum {
        FOO(0),
        BAR(1);
        public final int value;
        InternalEnum(final int value) {
            this.value = value;
        }
    }

    static class InternalStruct {
        public String stringField;
        public InternalStruct() {}
        public InternalStruct(String stringField) {
            this.stringField = stringField;
        }
    }

    public static class PublicStruct {
        PublicClass.InternalStruct internalField = new PublicClass.InternalStruct();
        public PublicStruct() {}
        public PublicStruct(PublicClass.InternalStruct internalField) {
            this.internalField = internalField;
        }
    }

    /** For internal use only */
    protected PublicClass(final long nativeHandle) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);

    native PublicClass.InternalStruct internalMethod(final PublicClass.InternalStruct input);
    native PublicClass.InternalStruct getInternalStructAttribute();
    native void setInternalStructAttribute(final PublicClass.InternalStruct value);
}