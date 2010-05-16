package libmemcached;

import libmemcached.memcached.memcached_st;

public interface version {

    /**
     * C func: memcached_return_t memcached_version(memcached_st *ptr);
     */
    public int memcached_version(memcached_st ptr);
    
    /**
     * C func: const char * memcached_lib_version(void);
     */
    public String memcached_lib_version();
}
