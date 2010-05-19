package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_execute_function;

import com.sun.jna.Pointer;

public interface fetch {
    /**
     * C func: memcached_return_t memcached_fetch_execute(
     *  memcached_st *ptr,
     *  memcached_execute_fn *callback,
     *  void *context,
     *  uint32_t number_of_callbacks
     *  );
     */
    public int memcached_fetch_execute(
        memcached_st ptr,
        memcached_execute_function callback,
        Pointer context,
        int number_of_callbacks
    );

}
