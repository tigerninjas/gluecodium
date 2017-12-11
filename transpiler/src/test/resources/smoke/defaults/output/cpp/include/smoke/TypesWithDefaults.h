// -------------------------------------------------------------------------------------------------
//
// Copyright (C) 2017 HERE Global B.V. and/or its affiliated companies. All rights reserved.
//
// This software, including documentation, is protected by copyright controlled by
// HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
// adapting or translating, any or all of this material requires the prior written
// consent of HERE Global B.V. This material also contains confidential information,
// which may not be disclosed to others without prior written consent of HERE Global B.V.
//
// -------------------------------------------------------------------------------------------------
//
// Automatically generated. Do not modify. Your changes will be lost.
//
// -------------------------------------------------------------------------------------------------

#pragma once

#include <cstdint>
#include <string>

namespace smoke {

enum class SomeEnum {
    FOO_VALUE,
    BAR_VALUE
};

struct StructWithDefaults {
    int32_t int_field = 42;
    float float_field = 3.14;
    bool bool_field = true;
    ::std::string string_field = "\\Jonny \"Magic\" Smith\n";
    ::smoke::SomeEnum enum_field = ::smoke::SomeEnum::BAR_VALUE;
};

}