package libmemcached;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;

public interface delete {
    /**
     * C func: memcached_return memcached_delete(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  time_t expiration
     * )
     */
    public int memcached_delete(
        memcached_st ptr,
        String key,
        size_t key_length,
        time_t expiration
    );
    
    /**
     * C func: memcached_return memcached_delete_by_key(
     *  memcached_st *ptr,
     *  const char *master_key,
     *  size_t master_key_length,
     *  const char *key,
     *  size_t key_length,
     *  time_t expiration
     * );
     */
    public int memcached_delete_by_key(
        memcached_st ptr, 
        String master_key,
        size_t master_key_length,
        String key,
        size_t key_length,
        time_t expiration
    );
    
}
