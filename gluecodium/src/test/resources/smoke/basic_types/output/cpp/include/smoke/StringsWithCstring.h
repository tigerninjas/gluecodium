// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#pragma once
#include "gluecodium/Export.h"
#include <string>
namespace smoke {
class _GLUECODIUM_CPP_EXPORT StringsWithCstring {
public:
    StringsWithCstring();
    virtual ~StringsWithCstring() = 0;
public:
    /**
     * Method that takes a C string as input and returns an std::string it as output.
     * \param[in] input_string
     * \return
     */
    static ::std::string return_input_string( const char* input_string );
    /**
     * Method that takes a C string as input and returns an std::string it as output.
     * \param[in] input_string
     * \return
     */
    static ::std::string return_input_string( const ::std::string& input_string );
};
}
