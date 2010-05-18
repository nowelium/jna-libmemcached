package libmemcached;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;

public interface storage {
    
    /**
     * C func: memcached_return memcached_set(
     *     memcached_st* ptr,
     *     const char* key, size_t key_length,
     *     const char* value, size_t value_length,
     *     time_t expiration,
     *     uint32_t flags
     * )
     */
    public int memcached_set(
        memcached_st ptr,
        String key, size_t key_length,
        String value, size_t value_length,
        time_t expiration,
        int flags
    );
    
    /**
     * C func: memcached_return memcached_add(
     *  memcached_st *ptr,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t  flags
     * );
     */
    public int memcached_add(
        memcached_st ptr,
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );
    
    /**
     * C func: memcached_return memcached_replace(
     *  memcached_st *ptr,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t  flags
     * );
     */
    public int memcached_replace(
        memcached_st ptr,
        String key, size_t key_length,
        String value, size_t value_length,
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_append(
     *  memcached_st *ptr,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_append(
        memcached_st ptr, 
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_prepend(
     *  memcached_st *ptr,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_prepend(
        memcached_st ptr, 
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_cas(
     *  memcached_st *ptr,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags,
     *  uint64_t cas
     * );
     */
    public int memcached_cas(
        memcached_st ptr, 
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags,
        long cas
    );

    /**
     * C func: memcached_return memcached_set_by_key(
     *  memcached_st *ptr, 
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_set_by_key(
       memcached_st ptr, 
       String master_key, size_t master_key_length, 
       String key, size_t key_length, 
       String value, size_t value_length, 
       time_t expiration,
       int flags
   );

    /**
     * C func: memcached_return memcached_add_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_add_by_key(
        memcached_st ptr, 
        String master_key, size_t master_key_length,
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_replace_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_replace_by_key(
        memcached_st ptr, 
        String master_key, size_t master_key_length,
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_prepend_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_prepend_by_key(
        memcached_st ptr, 
        String master_key, size_t master_key_length,
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_append_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags
     * );
     */
    public int memcached_append_by_key(
        memcached_st ptr,
        String master_key, size_t master_key_length,
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags
    );

    /**
     * C func: memcached_return memcached_cas_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  const char *value, size_t value_length,
     *  time_t expiration,
     *  uint32_t flags,
     *  uint64_t cas
     * );
     */
    public int memcached_cas_by_key(
        memcached_st ptr, 
        String master_key, size_t master_key_length,
        String key, size_t key_length,
        String value, size_t value_length, 
        time_t expiration,
        int flags,
        long cas
    );

}