package libmemcached.wrapper.function;

import com.sun.jna.Pointer;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_calloc_function;
import libmemcached.types.memcached_free_function;
import libmemcached.types.memcached_malloc_function;
import libmemcached.types.memcached_realloc_function;
import libmemcached.wrapper.type.ReturnType;

public class Allocators extends Function {

    public static ReturnType memcached_set_memory_allocators(
        memcached_st ptr,
        memcached_malloc_function mem_malloc,
        memcached_free_function mem_free,
        memcached_realloc_function mem_realloc,
        memcached_calloc_function mem_calloc,
        Pointer context
    ){
        final int rc = getMemcached().memcached_set_memory_allocators(ptr, mem_malloc, mem_free, mem_realloc, mem_calloc, context);
        return ReturnType.get(rc);
    }
    
    public static void memcached_get_memory_allocators(
        memcached_st ptr,
        memcached_malloc_function mem_malloc,
        memcached_free_function mem_free,
        memcached_realloc_function mem_realloc,
        memcached_calloc_function mem_calloc
    ){
        getMemcached().memcached_get_memory_allocators(ptr, mem_malloc, mem_free, mem_realloc, mem_calloc);
    }
    
    public static Pointer memcached_get_memory_allocators_context(memcached_st ptr){
        return getMemcached().memcached_get_memory_allocators_context(ptr);
    }
    
}
