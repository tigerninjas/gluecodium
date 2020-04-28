// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#pragma once
#include "gluecodium/Export.h"
#include "gluecodium/Hash.h"
namespace smoke {
class _GLUECODIUM_CPP_EXPORT EquatableClass {
public:
    EquatableClass();
    virtual ~EquatableClass() = 0;
public:
    bool operator==( const EquatableClass& rhs );
    bool operator!=( const EquatableClass& rhs );
};
}
namespace gluecodium {
template<>
struct hash< ::smoke::EquatableClass > {
    _GLUECODIUM_CPP_EXPORT std::size_t operator( )( const ::smoke::EquatableClass& t ) const;
};
template <>
struct EqualityHash< std::shared_ptr< ::smoke::EquatableClass > >
{
    _GLUECODIUM_CPP_EXPORT std::size_t operator( )( const std::shared_ptr< ::smoke::EquatableClass >& t ) const;
};
}
