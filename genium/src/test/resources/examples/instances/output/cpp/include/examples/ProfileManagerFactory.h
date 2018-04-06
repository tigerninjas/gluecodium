// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
//
// Automatically generated. Do not modify. Your changes will be lost.
//
// -------------------------------------------------------------------------------------------------

#pragma once

#include "examples/ProfileManager.h"
#include "examples/ProfileManagerInterface.h"
#include <memory>

namespace examples {
    class ProfileManager;
}
namespace examples {
    class ProfileManagerInterface;
}

namespace examples {

class ProfileManagerFactory {
public:
    virtual ~ProfileManagerFactory() = 0;

public:
static ::std::shared_ptr< ::examples::ProfileManager > create_profile_manager(  );
static ::std::shared_ptr< ::examples::ProfileManagerInterface > create_profile_manager_interface(  );
};

}