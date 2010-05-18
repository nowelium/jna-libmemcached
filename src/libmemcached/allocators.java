package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_calloc_function;
import libmemcached.types.memcached_free_function;
import libmemcached.types.memcached_malloc_function;
import libmemcached.types.memcached_realloc_function;

import com.sun.jna.Pointer;

public interface allocators {
    /**
     * C func: memcached_return_t memcached_set_memory_allocators(
     *  memcached_st *ptr,
     *  memcached_malloc_fn mem_malloc,
     *  memcached_free_fn mem_free,
     *  memcached_realloc_fn mem_realloc,
     *  memcached_calloc_fn mem_calloc,
     *  void *context
     * );
     */
    public int memcached_set_memory_allocators(
        memcached_st ptr,
        memcached_malloc_function mem_malloc,
        memcached_free_function mem_free,
        memcached_realloc_function mem_realloc,
        memcached_calloc_function mem_calloc,
        Pointer context
    );

    /**
     * C func: void memcached_get_memory_allocators(
     *  const memcached_st *ptr,
     *  memcached_malloc_fn *mem_malloc,
     *  memcached_free_fn *mem_free,
     *  memcached_realloc_fn *mem_realloc,
     *  memcached_calloc_fn *mem_calloc
     * );
     */
    public void memcached_get_memory_allocators(
        memcached_st ptr,
        memcached_malloc_function mem_malloc,
        memcached_free_function mem_free,
        memcached_realloc_function mem_realloc,
        memcached_calloc_function mem_calloc
    );

    /**
     * C func: void *memcached_get_memory_allocators_context(const memcached_st *ptr)
     */
    public Pointer memcached_get_memory_allocators_context(memcached_st ptr);

}
