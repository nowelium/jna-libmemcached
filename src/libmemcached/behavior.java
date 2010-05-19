package libmemcached;

import libmemcached.memcached.memcached_st;

public interface behavior {
    
    /**
     * C func: memcached_return_t memcached_behavior_set(
     *  memcached_st *ptr,
     *  const memcached_behavior_t flag,
     *  uint64_t data
     * );
     */
    public int memcached_behavior_set(memcached_st ptr, int flag, long data);

    /**
     * C func: uint64_t memcached_behavior_get(
     *  memcached_st *ptr,
     *  const memcached_behavior_t flag
     * )
     */
    public long memcached_behavior_get(memcached_st ptr, int flag);

    /**
     * C func: memcached_return_t memcached_behavior_set_distribution(
     *  memcached_st *ptr,
     *  memcached_server_distribution_t type
     * )
     */
    public int memcached_behavior_set_distribution(memcached_st ptr, int type);

    /**
     * C func: memcached_server_distribution_t memcached_behavior_get_distribution(
     *  memcached_st *ptr
     * )
     */
    public int memcached_behavior_get_distribution(memcached_st ptr);

    /**
     * C func: memcached_return_t memcached_behavior_set_key_hash(
     *  memcached_st *ptr,
     *  memcached_hash_t type
     * )
     */
    public int memcached_behavior_set_key_hash(memcached_st ptr, int type);

    /**
     * C func: memcached_hash_t memcached_behavior_get_key_hash(
     *  memcached_st *ptr
     * )
     */
    public int memcached_behavior_get_key_hash(memcached_st ptr);

    /**
     * C func: memcached_return_t memcached_behavior_set_distribution_hash(
     *  memcached_st *ptr,
     *  memcached_hash_t type
     * )
     */
    public int memcached_behavior_set_distribution_hash(memcached_st ptr, int type);

    /**
     * C func: memcached_hash_t memcached_behavior_get_distribution_hash(
     *  memcached_st *ptr
     * )
     */
    public int memcached_behavior_get_distribution_hash(memcached_st ptr);

}
