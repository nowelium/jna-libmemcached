package libmemcached;

import com.sun.jna.NativeLong;

import libmemcached.memcached.memcached_st;

public interface auto {
    /**
     * C func: memcached_return memcached_decrement(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint32_t offset,
     *  uint64_t *value
     * );
     */
    public int memcached_decrement(
        memcached_st ptr, 
        String key,
        NativeLong key_length,
        int offset,
        long value
    );
    /**
     * C func: memcached_return memcached_decrement_with_initial(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint64_t offset,
     *  uint64_t initial,
     *  time_t expiration,
     *  uint64_t *value
     * );
     */
    public int memcached_decrement_with_initial(
        memcached_st ptr,
        String key,
        NativeLong key_length,
        long offset,
        long initial,
        int expiration,
        long value
    );
    
    /**
     * C func: memcached_return memcached_increment(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint32_t offset,
     *  uint64_t *value
     * );
     */
    public int memcached_increment(
        memcached_st ptr, 
        String key,
        NativeLong key_length,
        int offset,
        long value
    );
    
    /**
     * C func: memcached_return memcached_increment_with_initial(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint64_t offset,
     *  uint64_t initial,
     *  time_t expiration,
     *  uint64_t *value
     * );
     */
    public int memcached_increment_with_initial(
        memcached_st ptr,
        String key,
        NativeLong key_length,
        long offset,
        long initial,
        int expiration,
        long value
    );
}
