/*
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */
package com.example.examples;
import android.support.annotation.NonNull;
import com.example.NativeBase;
import java.util.List;
public class Arrays extends NativeBase {
    public static class SyncResult {
        public long lastUpdatedTimeStamp;
        public long numberOfChanges;
        public SyncResult(long lastUpdatedTimeStamp, long numberOfChanges) {
            this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
            this.numberOfChanges = numberOfChanges;
        }
    }
    /**
     * For internal use only.
     * @exclude
     */
    protected Arrays(final long nativeHandle) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);
    @NonNull
    public static native List<Arrays.SyncResult> explicitArrayMethod(@NonNull final List<Arrays.SyncResult> input);
    @NonNull
    public static native List<Arrays.SyncResult> implicitArrayMethod(@NonNull final List<Arrays.SyncResult> input);
}
