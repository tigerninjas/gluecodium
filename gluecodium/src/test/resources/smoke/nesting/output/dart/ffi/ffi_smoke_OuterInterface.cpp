#include "ffi_smoke_OuterInterface.h"
#include "ConversionBase.h"
#include "CallbacksQueue.h"
#include "IsolateContext.h"
#include "ProxyCache.h"
#include "smoke/OuterInterface.h"
#include <memory>
#include <string>
#include <memory>
#include <new>
class smoke_OuterInterface_Proxy : public ::smoke::OuterInterface {
public:
    smoke_OuterInterface_Proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0)
        : token(token), isolate_id(isolate_id), deleter(deleter), f0(f0) { }
    ~smoke_OuterInterface_Proxy() {
        gluecodium::ffi::cbqm.enqueueCallback(isolate_id, [this]() {
            (*reinterpret_cast<void (*)(uint64_t, FfiOpaqueHandle)>(deleter))(token, this);
        });
    }
    std::string
    foo(const std::string& input) override {
        FfiOpaqueHandle _result_handle;
        dispatch([&]() { (*reinterpret_cast<int64_t (*)(uint64_t, FfiOpaqueHandle, FfiOpaqueHandle*)>(f0))(token,
            gluecodium::ffi::Conversion<std::string>::toFfi(input),
            &_result_handle
        ); });
        auto _result = gluecodium::ffi::Conversion<std::string>::toCpp(_result_handle);
        delete reinterpret_cast<std::string*>(_result_handle);
        return _result;
    }
private:
    uint64_t token;
    int32_t isolate_id;
    FfiOpaqueHandle deleter;
    FfiOpaqueHandle f0;
    inline void dispatch(std::function<void()>&& callback) const
    {
        gluecodium::ffi::IsolateContext::is_current(isolate_id)
            ? callback()
            : gluecodium::ffi::cbqm.enqueueCallback(isolate_id, std::move(callback)).wait();
    }
};
class smoke_OuterInterface_InnerInterface_Proxy : public ::smoke::OuterInterface::InnerInterface {
public:
    smoke_OuterInterface_InnerInterface_Proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0)
        : token(token), isolate_id(isolate_id), deleter(deleter), f0(f0) { }
    ~smoke_OuterInterface_InnerInterface_Proxy() {
        gluecodium::ffi::cbqm.enqueueCallback(isolate_id, [this]() {
            (*reinterpret_cast<void (*)(uint64_t, FfiOpaqueHandle)>(deleter))(token, this);
        });
    }
    std::string
    foo(const std::string& input) override {
        FfiOpaqueHandle _result_handle;
        dispatch([&]() { (*reinterpret_cast<int64_t (*)(uint64_t, FfiOpaqueHandle, FfiOpaqueHandle*)>(f0))(token,
            gluecodium::ffi::Conversion<std::string>::toFfi(input),
            &_result_handle
        ); });
        auto _result = gluecodium::ffi::Conversion<std::string>::toCpp(_result_handle);
        delete reinterpret_cast<std::string*>(_result_handle);
        return _result;
    }
private:
    uint64_t token;
    int32_t isolate_id;
    FfiOpaqueHandle deleter;
    FfiOpaqueHandle f0;
    inline void dispatch(std::function<void()>&& callback) const
    {
        gluecodium::ffi::IsolateContext::is_current(isolate_id)
            ? callback()
            : gluecodium::ffi::cbqm.enqueueCallback(isolate_id, std::move(callback)).wait();
    }
};
#ifdef __cplusplus
extern "C" {
#endif
FfiOpaqueHandle
library_smoke_OuterInterface_foo__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle input) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
    return gluecodium::ffi::Conversion<std::string>::toFfi(
        (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::OuterInterface>>::toCpp(_self)).foo(
            gluecodium::ffi::Conversion<std::string>::toCpp(input)
        )
    );
}
FfiOpaqueHandle
library_smoke_OuterInterface_InnerClass_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::OuterInterface::InnerClass>(
            *reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerClass>*>(handle)
        )
    );
}
void
library_smoke_OuterInterface_InnerClass_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerClass>*>(handle);
}
FfiOpaqueHandle
library_smoke_OuterInterface_InnerClass_get_raw_pointer(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerClass>*>(handle)->get()
    );
}
FfiOpaqueHandle
library_smoke_OuterInterface_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::OuterInterface>(
            *reinterpret_cast<std::shared_ptr<::smoke::OuterInterface>*>(handle)
        )
    );
}
void
library_smoke_OuterInterface_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::OuterInterface>*>(handle);
}
FfiOpaqueHandle
library_smoke_OuterInterface_get_raw_pointer(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        reinterpret_cast<std::shared_ptr<::smoke::OuterInterface>*>(handle)->get()
    );
}
FfiOpaqueHandle
library_smoke_OuterInterface_InnerInterface_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::OuterInterface::InnerInterface>(
            *reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerInterface>*>(handle)
        )
    );
}
void
library_smoke_OuterInterface_InnerInterface_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerInterface>*>(handle);
}
FfiOpaqueHandle
library_smoke_OuterInterface_InnerInterface_get_raw_pointer(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerInterface>*>(handle)->get()
    );
}
FfiOpaqueHandle
library_smoke_OuterInterface_create_proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0) {
    auto cached_proxy = gluecodium::ffi::get_cached_proxy<smoke_OuterInterface_Proxy>(token);
    std::shared_ptr<smoke_OuterInterface_Proxy>* proxy_ptr;
    if (cached_proxy) {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_OuterInterface_Proxy>(cached_proxy);
    } else {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_OuterInterface_Proxy>(
            new (std::nothrow) smoke_OuterInterface_Proxy(token, isolate_id, deleter, f0)
        );
        gluecodium::ffi::cache_proxy(token, *proxy_ptr);
    }
    return reinterpret_cast<FfiOpaqueHandle>(proxy_ptr);
}
FfiOpaqueHandle
library_smoke_OuterInterface_InnerInterface_create_proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0) {
    auto cached_proxy = gluecodium::ffi::get_cached_proxy<smoke_OuterInterface_InnerInterface_Proxy>(token);
    std::shared_ptr<smoke_OuterInterface_InnerInterface_Proxy>* proxy_ptr;
    if (cached_proxy) {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_OuterInterface_InnerInterface_Proxy>(cached_proxy);
    } else {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_OuterInterface_InnerInterface_Proxy>(
            new (std::nothrow) smoke_OuterInterface_InnerInterface_Proxy(token, isolate_id, deleter, f0)
        );
        gluecodium::ffi::cache_proxy(token, *proxy_ptr);
    }
    return reinterpret_cast<FfiOpaqueHandle>(proxy_ptr);
}
FfiOpaqueHandle
library_smoke_OuterInterface_get_type_id(FfiOpaqueHandle handle) {
    const auto& type_repository = ::gluecodium::get_type_repository(static_cast<::smoke::OuterInterface*>(nullptr));
    const auto& type_id = type_repository.get_id(reinterpret_cast<std::shared_ptr<::smoke::OuterInterface>*>(handle)->get());
    return reinterpret_cast<FfiOpaqueHandle>(new (std::nothrow) std::string(type_id));
}
FfiOpaqueHandle
library_smoke_OuterInterface_InnerInterface_get_type_id(FfiOpaqueHandle handle) {
    const auto& type_repository = ::gluecodium::get_type_repository(static_cast<::smoke::OuterInterface::InnerInterface*>(nullptr));
    const auto& type_id = type_repository.get_id(reinterpret_cast<std::shared_ptr<::smoke::OuterInterface::InnerInterface>*>(handle)->get());
    return reinterpret_cast<FfiOpaqueHandle>(new (std::nothrow) std::string(type_id));
}
#ifdef __cplusplus
}
#endif
