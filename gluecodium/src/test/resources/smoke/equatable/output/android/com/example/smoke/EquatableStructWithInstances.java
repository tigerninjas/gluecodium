/*
 *
 */
package com.example.smoke;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public final class EquatableStructWithInstances {
    @NonNull
    public EquatableClass equatableClass;
    @NonNull
    public NonEquatableClass nonEquatableClass;
    @NonNull
    public EquatableInterface equatableInterface;
    @NonNull
    public NonEquatableInterface nonEquatableInterface;
    @Nullable
    public EquatableClass equatableClassNullable;
    @Nullable
    public NonEquatableClass nonEquatableClassNullable;
    @Nullable
    public EquatableInterface equatableInterfaceNullable;
    @Nullable
    public NonEquatableInterface nonEquatableInterfaceNullable;
    public EquatableStructWithInstances(@NonNull final EquatableClass equatableClass, @NonNull final NonEquatableClass nonEquatableClass, @NonNull final EquatableInterface equatableInterface, @NonNull final NonEquatableInterface nonEquatableInterface, @Nullable final EquatableClass equatableClassNullable, @Nullable final NonEquatableClass nonEquatableClassNullable, @Nullable final EquatableInterface equatableInterfaceNullable, @Nullable final NonEquatableInterface nonEquatableInterfaceNullable) {
        this.equatableClass = equatableClass;
        this.nonEquatableClass = nonEquatableClass;
        this.equatableInterface = equatableInterface;
        this.nonEquatableInterface = nonEquatableInterface;
        this.equatableClassNullable = equatableClassNullable;
        this.nonEquatableClassNullable = nonEquatableClassNullable;
        this.equatableInterfaceNullable = equatableInterfaceNullable;
        this.nonEquatableInterfaceNullable = nonEquatableInterfaceNullable;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EquatableStructWithInstances)) return false;
        final EquatableStructWithInstances other = (EquatableStructWithInstances) obj;
        return java.util.Objects.equals(this.equatableClass, other.equatableClass) &&
                java.util.Objects.equals(this.nonEquatableClass, other.nonEquatableClass) &&
                java.util.Objects.equals(this.equatableInterface, other.equatableInterface) &&
                java.util.Objects.equals(this.nonEquatableInterface, other.nonEquatableInterface) &&
                java.util.Objects.equals(this.equatableClassNullable, other.equatableClassNullable) &&
                java.util.Objects.equals(this.nonEquatableClassNullable, other.nonEquatableClassNullable) &&
                java.util.Objects.equals(this.equatableInterfaceNullable, other.equatableInterfaceNullable) &&
                java.util.Objects.equals(this.nonEquatableInterfaceNullable, other.nonEquatableInterfaceNullable);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.equatableClass != null ? this.equatableClass.hashCode() : 0);
        hash = 31 * hash + (this.nonEquatableClass != null ? this.nonEquatableClass.hashCode() : 0);
        hash = 31 * hash + (this.equatableInterface != null ? this.equatableInterface.hashCode() : 0);
        hash = 31 * hash + (this.nonEquatableInterface != null ? this.nonEquatableInterface.hashCode() : 0);
        hash = 31 * hash + (this.equatableClassNullable != null ? this.equatableClassNullable.hashCode() : 0);
        hash = 31 * hash + (this.nonEquatableClassNullable != null ? this.nonEquatableClassNullable.hashCode() : 0);
        hash = 31 * hash + (this.equatableInterfaceNullable != null ? this.equatableInterfaceNullable.hashCode() : 0);
        hash = 31 * hash + (this.nonEquatableInterfaceNullable != null ? this.nonEquatableInterfaceNullable.hashCode() : 0);
        return hash;
    }
}
