// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
//
// Automatically generated. Do not modify. Your changes will be lost.
//
// -------------------------------------------------------------------------------------------------

#pragma once

#include <cstdint>
#include <string>
#include <unordered_map>

namespace examples {

class Maps {
public:
    virtual ~Maps() = 0;
public:
using NameMap = ::std::unordered_map< uint64_t, ::std::string >;

public:
static ::examples::Maps::NameMap map_method( const ::examples::Maps::NameMap& input );

};

}