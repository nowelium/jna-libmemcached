package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_dump_function;

import com.sun.jna.Pointer;

public interface dump {
    
    /**
     * C func: memcached_return_t memcached_dump(
     *  memcached_st *ptr,
     *  memcached_dump_fn *function,
     *  void *context,
     *  uint32_t number_of_callbacks
     * );
     */
    public int memcached_dump(
        memcached_st ptr,
        memcached_dump_function function,
        Pointer context,
        int number_of_callbacks
    );

}
