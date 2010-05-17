package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;

public interface get {
    
    /**
     * C func: char *memcached_get(
     *  memcached_st *ptr,
     *  const char *key, size_t key_length.
     *  size_t *value_length,
     *  uint32_t *flags,
     *  memcached_return *error
     * );
     */
    public String memcached_get(
        memcached_st ptr, 
        String key, NativeLong key_length,
        NativeLongByReference value_length, 
        IntByReference flags,
        IntByReference error
    );

    /**
     * C func: memcached_return memcached_mget(
     *  memcached_st *ptr,
     *  char **keys, size_t *key_length,
     *  unsigned int number_of_keys
     * );
     */
    public int memcached_mget(
        memcached_st ptr, 
        String[] keys, NativeLongByReference key_length, 
        int number_of_keys
    );

    /**
     * C func: char *memcached_get_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  const char *key, size_t key_length,
     *  size_t *value_length,
     *  uint32_t *flags,
     *  memcached_return *error
     * );
     */
    public String memcached_get_by_key(
        memcached_st ptr, 
        String master_key, NativeLong master_key_length, 
        String key, NativeLong key_length,
        NativeLongByReference value_length, 
        IntByReference flags,
        IntByReference error
    );

    /**
     * C func: memcached_return memcached_mget_by_key(
     *  memcached_st *ptr,
     *  const char *master_key, size_t master_key_length,
     *  char **keys, size_t *key_length,
     *  unsigned int number_of_keys
     * );
     */
    public int memcached_mget_by_key(
        memcached_st ptr,
        String master_key, NativeLong master_key_length,
        String[] keys, NativeLongByReference key_length, 
        int number_of_keys
    );

    /**
     * C func: char *memcached_fetch(
     *  memcached_st *ptr,
     *  char *key, size_t *key_length,
     *  size_t *value_length, uint32_t *flags,
     *  memcached_return *error
     * );
     */
    public String memcached_fetch(
        memcached_st ptr, 
        byte[] key, NativeLongByReference key_length, 
        NativeLongByReference value_length,
        IntByReference flags, 
        IntByReference error
    );

    /**
     * C func: memcached_result_st *memcached_fetch_result(
     *  memcached_st *ptr,
     *  memcached_result_st *result,
     *  memcached_return *error
     * );
     */
    public memcached_result_st memcached_fetch_result(
        memcached_st ptr,
        memcached_result_st result,
        IntByReference error
    );

}
