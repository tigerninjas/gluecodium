// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#include "smoke/EquatableClass.h"
#include "smoke/EquatableInterface.h"
#include "smoke/EquatableStructWithInstances.h"
#include "smoke/NonEquatableClass.h"
#include "smoke/NonEquatableInterface.h"
#include <utility>
namespace smoke {
EquatableStructWithInstances::EquatableStructWithInstances( )
    : equatable_class{ }, non_equatable_class{ }, equatable_interface{ }, non_equatable_interface{ }, equatable_class_nullable{ }, non_equatable_class_nullable{ }, equatable_interface_nullable{ }, non_equatable_interface_nullable{ }
{
}
EquatableStructWithInstances::EquatableStructWithInstances( ::std::shared_ptr< ::smoke::EquatableClass > equatable_class, ::std::shared_ptr< ::smoke::NonEquatableClass > non_equatable_class, ::std::shared_ptr< ::smoke::EquatableInterface > equatable_interface, ::std::shared_ptr< ::smoke::NonEquatableInterface > non_equatable_interface, ::std::shared_ptr< ::smoke::EquatableClass > equatable_class_nullable, ::std::shared_ptr< ::smoke::NonEquatableClass > non_equatable_class_nullable, ::std::shared_ptr< ::smoke::EquatableInterface > equatable_interface_nullable, ::std::shared_ptr< ::smoke::NonEquatableInterface > non_equatable_interface_nullable )
    : equatable_class( std::move( equatable_class ) ), non_equatable_class( std::move( non_equatable_class ) ), equatable_interface( std::move( equatable_interface ) ), non_equatable_interface( std::move( non_equatable_interface ) ), equatable_class_nullable( std::move( equatable_class_nullable ) ), non_equatable_class_nullable( std::move( non_equatable_class_nullable ) ), equatable_interface_nullable( std::move( equatable_interface_nullable ) ), non_equatable_interface_nullable( std::move( non_equatable_interface_nullable ) )
{
}
bool EquatableStructWithInstances::operator==( const EquatableStructWithInstances& rhs ) const
{
    return ( ( equatable_class && rhs.equatable_class )
            ? ( *equatable_class == *rhs.equatable_class )
            : ( static_cast< bool >( equatable_class ) == static_cast< bool >( rhs.equatable_class ) ) ) &&
        non_equatable_class == rhs.non_equatable_class &&
        ( ( equatable_interface && rhs.equatable_interface )
            ? ( *equatable_interface == *rhs.equatable_interface )
            : ( static_cast< bool >( equatable_interface ) == static_cast< bool >( rhs.equatable_interface ) ) ) &&
        non_equatable_interface == rhs.non_equatable_interface &&
        ( ( equatable_class_nullable && rhs.equatable_class_nullable )
            ? ( *equatable_class_nullable == *rhs.equatable_class_nullable )
            : ( static_cast< bool >( equatable_class_nullable ) == static_cast< bool >( rhs.equatable_class_nullable ) ) ) &&
        non_equatable_class_nullable == rhs.non_equatable_class_nullable &&
        ( ( equatable_interface_nullable && rhs.equatable_interface_nullable )
            ? ( *equatable_interface_nullable == *rhs.equatable_interface_nullable )
            : ( static_cast< bool >( equatable_interface_nullable ) == static_cast< bool >( rhs.equatable_interface_nullable ) ) ) &&
        non_equatable_interface_nullable == rhs.non_equatable_interface_nullable;
}
bool EquatableStructWithInstances::operator!=( const EquatableStructWithInstances& rhs ) const
{
    return !( *this == rhs );
}
}
namespace gluecodium {
std::size_t
hash< ::smoke::EquatableStructWithInstances >::operator( )( const ::smoke::EquatableStructWithInstances& t ) const
{
    size_t hash_value = 43;
    hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::equatable_class ) >( )( t.equatable_class ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::non_equatable_class ) >( )( t.non_equatable_class ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::equatable_interface ) >( )( t.equatable_interface ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::non_equatable_interface ) >( )( t.non_equatable_interface ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::equatable_class_nullable ) >( )( t.equatable_class_nullable ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::non_equatable_class_nullable ) >( )( t.non_equatable_class_nullable ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::equatable_interface_nullable ) >( )( t.equatable_interface_nullable ) ) << 1;
hash_value = ( hash_value ^ ::gluecodium::hash< decltype( ::smoke::EquatableStructWithInstances::non_equatable_interface_nullable ) >( )( t.non_equatable_interface_nullable ) ) << 1;
    return hash_value;
}
}
