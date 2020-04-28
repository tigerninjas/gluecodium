//
//
#include "cbridge/include/smoke/cbridge_EquatableClass.h"
#include "cbridge_internal/include/BaseHandleImpl.h"
#include "cbridge_internal/include/TypeInitRepository.h"
#include "cbridge_internal/include/WrapperCache.h"
#include "gluecodium/Optional.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/EquatableClass.h"
#include <memory>
#include <new>
void smoke_EquatableClass_release_handle(_baseRef handle) {
    auto ptr_ptr = get_pointer<std::shared_ptr<::smoke::EquatableClass>>(handle);
    auto& wrapper_cache = get_wrapper_cache();
    if (wrapper_cache_is_alive) {
        wrapper_cache.remove_cached_wrapper(ptr_ptr->get());
    }
    delete ptr_ptr;
}
_baseRef smoke_EquatableClass_copy_handle(_baseRef handle) {
    return handle
        ? reinterpret_cast<_baseRef>(checked_pointer_copy(*get_pointer<std::shared_ptr<::smoke::EquatableClass>>(handle)))
        : 0;
}
const void* smoke_EquatableClass_get_swift_object_from_wrapper_cache(_baseRef handle) {
    return handle
        ? get_wrapper_cache().get_cached_wrapper(get_pointer<std::shared_ptr<::smoke::EquatableClass>>(handle)->get())
        : nullptr;
}
void smoke_EquatableClass_cache_swift_object_wrapper(_baseRef handle, const void* swift_pointer) {
    if (!handle) return;
    get_wrapper_cache().cache_wrapper(get_pointer<std::shared_ptr<::smoke::EquatableClass>>(handle)->get(), swift_pointer);
}
bool smoke_EquatableClass_equal(_baseRef lhs, _baseRef rhs) {
    return **get_pointer<std::shared_ptr<::smoke::EquatableClass>>(lhs) == **get_pointer<std::shared_ptr<::smoke::EquatableClass>>(rhs);
}
uint64_t smoke_EquatableClass_hash(_baseRef handle) {
    return ::gluecodium::hash<std::shared_ptr<::smoke::EquatableClass>::element_type>()(**get_pointer<std::shared_ptr<::smoke::EquatableClass>>(handle));
}

