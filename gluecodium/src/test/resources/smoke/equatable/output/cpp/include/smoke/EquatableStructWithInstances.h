// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#pragma once
#include "gluecodium/Export.h"
#include "gluecodium/Hash.h"
#include <memory>
namespace smoke {
    class EquatableClass;
    class EquatableInterface;
    class NonEquatableClass;
    class NonEquatableInterface;
}
namespace smoke {
struct _GLUECODIUM_CPP_EXPORT EquatableStructWithInstances {
    /// \warning @NotNull
    ::std::shared_ptr< ::smoke::EquatableClass > equatable_class;
    /// \warning @NotNull
    ::std::shared_ptr< ::smoke::NonEquatableClass > non_equatable_class;
    /// \warning @NotNull
    ::std::shared_ptr< ::smoke::EquatableInterface > equatable_interface;
    /// \warning @NotNull
    ::std::shared_ptr< ::smoke::NonEquatableInterface > non_equatable_interface;
    ::std::shared_ptr< ::smoke::EquatableClass > equatable_class_nullable;
    ::std::shared_ptr< ::smoke::NonEquatableClass > non_equatable_class_nullable;
    ::std::shared_ptr< ::smoke::EquatableInterface > equatable_interface_nullable;
    ::std::shared_ptr< ::smoke::NonEquatableInterface > non_equatable_interface_nullable;
    EquatableStructWithInstances( );
    EquatableStructWithInstances( ::std::shared_ptr< ::smoke::EquatableClass > equatable_class, ::std::shared_ptr< ::smoke::NonEquatableClass > non_equatable_class, ::std::shared_ptr< ::smoke::EquatableInterface > equatable_interface, ::std::shared_ptr< ::smoke::NonEquatableInterface > non_equatable_interface, ::std::shared_ptr< ::smoke::EquatableClass > equatable_class_nullable, ::std::shared_ptr< ::smoke::NonEquatableClass > non_equatable_class_nullable, ::std::shared_ptr< ::smoke::EquatableInterface > equatable_interface_nullable, ::std::shared_ptr< ::smoke::NonEquatableInterface > non_equatable_interface_nullable );
    bool operator==( const EquatableStructWithInstances& rhs ) const;
    bool operator!=( const EquatableStructWithInstances& rhs ) const;
};
}
namespace gluecodium {
template<>
struct hash< ::smoke::EquatableStructWithInstances > {
    _GLUECODIUM_CPP_EXPORT std::size_t operator( )( const ::smoke::EquatableStructWithInstances& t ) const;
};
}
