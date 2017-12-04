# Copyright (c) 2017 HERE Europe B.V.
#
# All rights reserved.  This software, including documentation, is protected
# by copyright controlled by HERE.  All rights are reserved.  Copying,
# including reproducing, storing, adapting or translating, any or all of
# this material requires the prior written consent of HERE.  This
# material also contains confidential information which may not be
# disclosed to others without the prior written consent of HERE.

if(DEFINED includeguard_ApigenSwiftModulemap)
    return()
endif()
set(includeguard_ApigenSwiftModulemap ON)

cmake_minimum_required(VERSION 3.5)

#.rst:
# ApigenSwiftModulemap
# -------------------
#
# This module builds Swift modulemap needed for the framework creation
#
# .. command:: apigen_swift_modulemap
#
# The general form of the command is::
#
#     apigen_swift_modulemap(target)
#

function(apigen_swift_modulemap target)

    get_target_property(GENERATOR ${target} APIGEN_TRANSPILER_GENERATOR)
    get_target_property(OUTPUT_DIR ${target} APIGEN_TRANSPILER_GENERATOR_OUTPUT_DIR)
    get_target_property(SWIFT_OUTPUT_DIR ${target} APIGEN_SWIFT_BUILD_OUTPUT_DIR)
    get_target_property(SWIFT_FRAMEWORK_VERSION ${target} APIGEN_SWIFT_FRAMEWORK_VERSION)

    if(NOT ${GENERATOR} MATCHES swift)
        return()
    endif()

    set(MODULEMAP_FRAMEWORK_PATH "${SWIFT_OUTPUT_DIR}/${target}.framework/Versions/${SWIFT_FRAMEWORK_VERSION}/Modules/module.modulemap")
    set(MODULEMAP_CBRIDGE_PATH "${OUTPUT_DIR}/module.modulemap")

    # Module map generation
    ## Top level:
    set(CBRIDGE_MODULE_MAP "module ${target} {\n")
    #TODO(ATEAM-44): Remove compat locations
    file(GLOB_RECURSE cbridge_headers ${OUTPUT_DIR}/cbridge/*.h)

    foreach(header IN LISTS cbridge_headers)
        set(CBRIDGE_MODULE_MAP "${CBRIDGE_MODULE_MAP}\n    header \"${header}\"")
    endforeach()

    set(CBRIDGE_MODULE_MAP "${CBRIDGE_MODULE_MAP}\n}\n")

    # For each submodule there needs to be a modulemap created
    file(GLOB swift_modules LIST_DIRECTORIES true ${OUTPUT_DIR}/swift/*)

    foreach(module_path IN LISTS swift_modules)
        if(NOT IS_DIRECTORY ${module_path})
            continue()
        endif()
        get_filename_component(SUB_MODULE_NAME "${module_path}" NAME)
        set(MODULE_NAME "${target}.${SUB_MODULE_NAME}")
        #TODO(ATEAM-44): Remove compat locations
        set(MODULE_DIR ${OUTPUT_DIR}/cbridge/${SUB_MODULE_NAME})
        file(GLOB_RECURSE cbridge_headers ${MODULE_DIR}/*.h)

        set(CBRIDGE_MODULE_MAP "${CBRIDGE_MODULE_MAP}\nmodule ${MODULE_NAME} {")
        foreach(header IN LISTS cbridge_headers)
            set(CBRIDGE_MODULE_MAP "${CBRIDGE_MODULE_MAP}\n    header \"${header}\"")
        endforeach()
        set(CBRIDGE_MODULE_MAP "${CBRIDGE_MODULE_MAP}\n}\n")
    endforeach()

    file(WRITE ${MODULEMAP_CBRIDGE_PATH} "${CBRIDGE_MODULE_MAP}")

    file(WRITE
        ${MODULEMAP_FRAMEWORK_PATH}
        "module ${target} {\n}\n")

    file(WRITE
        ${SWIFT_OUTPUT_DIR}/module.modulemap
        "module ${target} {\n}")

    message(STATUS "[Swift] Creating modulemap files: ${MODULEMAP_FRAMEWORK_PATH} and ${MODULEMAP_CBRIDGE_PATH}")

endfunction(apigen_swift_modulemap)

