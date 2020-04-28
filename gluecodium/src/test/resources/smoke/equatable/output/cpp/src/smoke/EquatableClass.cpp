// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#include "smoke/EquatableClass.h"
namespace smoke {
EquatableClass::EquatableClass() {
}
EquatableClass::~EquatableClass() {
}
bool
EquatableClass::operator!=( const EquatableClass& rhs ) {
    return !( *this == rhs );
}
}
namespace gluecodium {
std::size_t
EqualityHash< std::shared_ptr< ::smoke::EquatableClass > >::operator( )( const std::shared_ptr< ::smoke::EquatableClass >& t ) const
{
    if ( !t ) {
        return 43;
    }
    return 43 ^ ::gluecodium::hash< ::smoke::EquatableClass >( )( *t );
};
}
