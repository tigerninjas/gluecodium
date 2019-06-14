// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
//
// Automatically generated. Do not modify. Your changes will be lost.
//
// -------------------------------------------------------------------------------------------------
#include "smoke/Equatable.h"
namespace smoke {
EquatableStruct::EquatableStruct( ) = default;
EquatableStruct::EquatableStruct( const bool bool_field, const int32_t int_field, const int64_t long_field, const float float_field, const double double_field, const ::std::string& string_field, const ::smoke::NestedEquatableStruct& struct_field, const ::smoke::SomeEnum enum_field, const ::std::vector< ::std::string >& array_field, const ::smoke::ErrorCodeToMessageMap& map_field )
    : bool_field( bool_field ), int_field( int_field ), long_field( long_field ), float_field( float_field ), double_field( double_field ), string_field( string_field ), struct_field( struct_field ), enum_field( enum_field ), array_field( array_field ), map_field( map_field )
{
}
bool EquatableStruct::operator==( const EquatableStruct& rhs ) const
{
    return bool_field == rhs.bool_field &&
        int_field == rhs.int_field &&
        long_field == rhs.long_field &&
        float_field == rhs.float_field &&
        double_field == rhs.double_field &&
        string_field == rhs.string_field &&
        struct_field == rhs.struct_field &&
        enum_field == rhs.enum_field &&
        array_field == rhs.array_field &&
        map_field == rhs.map_field;
}
bool EquatableStruct::operator!=( const EquatableStruct& rhs ) const
{
    return !( *this == rhs );
}
EquatableNullableStruct::EquatableNullableStruct( ) = default;
EquatableNullableStruct::EquatableNullableStruct( const ::genium::optional< bool >& bool_field, const ::genium::optional< int32_t >& int_field, const ::genium::optional< uint16_t >& uint_field, const ::genium::optional< float >& float_field, const ::genium::optional< ::std::string >& string_field, const ::genium::optional< ::smoke::NestedEquatableStruct >& struct_field, const ::genium::optional< ::smoke::SomeEnum >& enum_field, const ::genium::optional< ::std::vector< ::std::string > >& array_field, const ::genium::optional< ::smoke::ErrorCodeToMessageMap >& map_field )
    : bool_field( bool_field ), int_field( int_field ), uint_field( uint_field ), float_field( float_field ), string_field( string_field ), struct_field( struct_field ), enum_field( enum_field ), array_field( array_field ), map_field( map_field )
{
}
bool EquatableNullableStruct::operator==( const EquatableNullableStruct& rhs ) const
{
    return ( ( bool_field && rhs.bool_field )
            ? ( *bool_field == *rhs.bool_field )
            : ( static_cast< bool >( bool_field ) == static_cast< bool >( rhs.bool_field ) ) ) &&
        ( ( int_field && rhs.int_field )
            ? ( *int_field == *rhs.int_field )
            : ( static_cast< bool >( int_field ) == static_cast< bool >( rhs.int_field ) ) ) &&
        ( ( uint_field && rhs.uint_field )
            ? ( *uint_field == *rhs.uint_field )
            : ( static_cast< bool >( uint_field ) == static_cast< bool >( rhs.uint_field ) ) ) &&
        ( ( float_field && rhs.float_field )
            ? ( *float_field == *rhs.float_field )
            : ( static_cast< bool >( float_field ) == static_cast< bool >( rhs.float_field ) ) ) &&
        ( ( string_field && rhs.string_field )
            ? ( *string_field == *rhs.string_field )
            : ( static_cast< bool >( string_field ) == static_cast< bool >( rhs.string_field ) ) ) &&
        ( ( struct_field && rhs.struct_field )
            ? ( *struct_field == *rhs.struct_field )
            : ( static_cast< bool >( struct_field ) == static_cast< bool >( rhs.struct_field ) ) ) &&
        ( ( enum_field && rhs.enum_field )
            ? ( *enum_field == *rhs.enum_field )
            : ( static_cast< bool >( enum_field ) == static_cast< bool >( rhs.enum_field ) ) ) &&
        ( ( array_field && rhs.array_field )
            ? ( *array_field == *rhs.array_field )
            : ( static_cast< bool >( array_field ) == static_cast< bool >( rhs.array_field ) ) ) &&
        ( ( map_field && rhs.map_field )
            ? ( *map_field == *rhs.map_field )
            : ( static_cast< bool >( map_field ) == static_cast< bool >( rhs.map_field ) ) );
}
bool EquatableNullableStruct::operator!=( const EquatableNullableStruct& rhs ) const
{
    return !( *this == rhs );
}
NestedEquatableStruct::NestedEquatableStruct( ) = default;
NestedEquatableStruct::NestedEquatableStruct( const ::std::string& foo_field )
    : foo_field( foo_field )
{
}
bool NestedEquatableStruct::operator==( const NestedEquatableStruct& rhs ) const
{
    return foo_field == rhs.foo_field;
}
bool NestedEquatableStruct::operator!=( const NestedEquatableStruct& rhs ) const
{
    return !( *this == rhs );
}
}