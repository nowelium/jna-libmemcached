package libmemcached;

import libmemcached.memcached.memcached_st;

public interface verbosity {
    /**
     * C func: memcached_return_t memcached_verbosity(memcached_st *ptr, uint32_t verbosity)
     */
    public int memcached_verbosity(memcached_st ptr, int verbosity);
}
