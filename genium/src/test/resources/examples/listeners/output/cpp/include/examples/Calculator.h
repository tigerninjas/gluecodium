// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
//
// Automatically generated. Do not modify. Your changes will be lost.
//
// -------------------------------------------------------------------------------------------------

#pragma once

#include "examples/CalculatorListener.h"
#include <memory>

namespace examples {
    class CalculatorListener;
}

namespace examples {

class Calculator {
public:
    virtual ~Calculator() = 0;
public:
static void register_listener( const ::std::shared_ptr< ::examples::CalculatorListener >& listener );
static void unregister_listener( const ::std::shared_ptr< ::examples::CalculatorListener >& listener );
static void calculate(  );

};

}