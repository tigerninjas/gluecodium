//
// Copyright (C) 2017 HERE Global B.V. and/or its affiliated companies. All rights reserved.
//
// This software, including documentation, is protected by copyright controlled by
// HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
// adapting or translating, any or all of this material requires the prior written
// consent of HERE Global B.V. This material also contains confidential information,
// which may not be disclosed to others without prior written consent of HERE Global B.V.
//
// Automatically generated. Do not modify. Your changes will be lost.
#pragma once
#ifdef __cplusplus
extern "C" {
#endif

#include <stdint.h>

typedef uint32_t smoke_Errors_InternalError;


typedef struct {
    void* const private_pointer;
} smoke_ErrorsRef;


void smoke_Errors_release(smoke_ErrorsRef handle);

smoke_Errors_InternalError smoke_Errors_methodWithErrors();

#ifdef __cplusplus
}
#endif