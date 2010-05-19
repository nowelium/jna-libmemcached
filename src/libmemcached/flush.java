package libmemcached;

import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;

public interface flush {
    /**
     * C func: memcached_return_t memcached_flush(memcached_st *ptr, time_t expiration)
     */
    public int memcached_flush(memcached_st ptr, time_t expiration);
}
