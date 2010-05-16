package libmemcached;

import libmemcached.memcached.memcached_st;

public interface strerror {
    /**
     * C func: const char *memcached_strerror(memcached_st *ptr, memcached_return_t rc)
     */
    public String memcached_strerror(memcached_st ptr, int rc);
}
