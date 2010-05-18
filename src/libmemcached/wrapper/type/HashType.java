package libmemcached.wrapper.type;

import libmemcached.constants.memcached_hash;

public enum HashType implements Type<HashType> {
    
    DEFAULT(memcached_hash.MEMCACHED_HASH_DEFAULT),
    MD5(memcached_hash.MEMCACHED_HASH_MD5),
    CRC(memcached_hash.MEMCACHED_HASH_CRC),
    FNV1_64(memcached_hash.MEMCACHED_HASH_FNV1_64),
    FNV1A_64(memcached_hash.MEMCACHED_HASH_FNV1A_64),
    FNV1_32(memcached_hash.MEMCACHED_HASH_FNV1_32),
    FNV1A_32(memcached_hash.MEMCACHED_HASH_FNV1A_32),
    HSIEH(memcached_hash.MEMCACHED_HASH_HSIEH),
    MURMUR(memcached_hash.MEMCACHED_HASH_MURMUR),
    JENKINS(memcached_hash.MEMCACHED_HASH_JENKINS),
    ;
    
    private static final Map<HashType> map = new Map<HashType>(HashType.class);
    private final int value;
    private HashType(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    public static HashType get(int value){
        return map.get(value);
    }
    
}
