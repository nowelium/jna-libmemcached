package libmemcached;

import com.sun.jna.NativeLong;

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
        NativeLong key_length,
        int expiration
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
        NativeLong master_key_length,
        String key,
        NativeLong key_length,
        int expiration
    );
    
}
