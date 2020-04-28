/*
 *
 */
package com.example.smoke;
import com.example.NativeBase;
public final class EquatableClass extends NativeBase {
    /**
     * For internal use only.
     * @exclude
     */
    protected EquatableClass(final long nativeHandle) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);
    @Override
    public native boolean equals(Object rhs);
    @Override
    public native int hashCode();
}
