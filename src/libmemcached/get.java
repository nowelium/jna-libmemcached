package libmemcached;

import libmemcached.compat.size_t;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.types.memcached_execute_function;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

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
        String key, size_t key_length,
        LongByReference value_length, 
        IntByReference flags,
        IntByReference error
    );

    /**
     * C func: memcached_return memcached_mget(
     *  memcached_st *ptr,
     *  const char * const *keys,
     *  size_t *key_length,
     *  size_t number_of_keys
     * );
     */
    public int memcached_mget(
        memcached_st ptr, 
        String[] keys,
        size_t[] key_length, 
        size_t number_of_keys
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
        String master_key, size_t master_key_length, 
        String key, size_t key_length,
        IntByReference value_length, 
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
        String master_key, size_t master_key_length,
        String[] keys, size_t[] key_length, 
        size_t number_of_keys
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
        byte[] key, IntByReference key_length, 
        IntByReference value_length,
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
    
    /**
     * C func: memcached_return_t memcached_mget_execute(
     *  memcached_st *ptr,
     *  const char * const *keys,
     *  const size_t *key_length,
     *  const size_t number_of_keys,
     *  memcached_execute_fn *callback,
     *  void *context,
     *  const uint32_t number_of_callbacks
     * );
     */
    public int memcached_mget_execute(
        memcached_st ptr,
        String[] keys,
        size_t[] key_length,
        size_t number_of_keys,
        memcached_execute_function callback,
        Pointer context,
        int number_of_callbacks
    );

    /**
     * C func: memcached_return_t memcached_mget_execute_by_key(
     *  memcached_st *ptr,
     *  const char *master_key,
     *  size_t master_key_length,
     *  const char * const *keys,
     *  const size_t *key_length,
     *  size_t number_of_keys,
     *  memcached_execute_fn *callback,
     *  void *context,
     *  const uint32_t number_of_callbacks
     * );
     */
    public int memcached_mget_execute_by_key(
        memcached_st ptr,
        String master_key,
        size_t master_key_length,
        String[] keys,
        size_t[] key_length,
        size_t number_of_keys,
        memcached_execute_function callback,
        Pointer context,
        int number_of_callbacks
    );

}
