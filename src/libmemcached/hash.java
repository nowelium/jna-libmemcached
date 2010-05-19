package libmemcached;

import libmemcached.compat.hashkit_st;
import libmemcached.compat.size_t;
import libmemcached.memcached.memcached_st;

public interface hash {
    /**
     * C func: uint32_t memcached_generate_hash_value(
     *  const char *key,
     *  size_t key_length,
     *  memcached_hash_t hash_algorithm
     * );
     */
    public int memcached_generate_hash_value(String key, size_t key_length, int hash_algorithm);

    /**
     * C func: const hashkit_st *memcached_get_hashkit(const memcached_st *ptr)
     */
    public hashkit_st memcached_get_hashkit(memcached_st ptr);

    /**
     * C func: memcached_return_t memcached_set_hashkit(memcached_st *ptr, hashkit_st *hashk)
     */
    public int memcached_set_hashkit(memcached_st ptr, hashkit_st hashk);

    /**
     * C func: uint32_t memcached_generate_hash(const memcached_st *ptr, const char *key, size_t key_length)
     */
    public int memcached_generate_hash(memcached_st ptr, String key, size_t key_length);

    /**
     * C func: void memcached_autoeject(memcached_st *ptr)
     */
    public void memcached_autoeject(memcached_st ptr);

}
