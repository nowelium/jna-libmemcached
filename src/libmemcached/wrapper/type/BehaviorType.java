package libmemcached.wrapper.type;

import libmemcached.constants.memcached_behavior;

public enum BehaviorType implements Type<BehaviorType> {
    NO_BLOCK(memcached_behavior.MEMCACHED_BEHAVIOR_NO_BLOCK),
    TCP_NODELAY(memcached_behavior.MEMCACHED_BEHAVIOR_TCP_NODELAY),
    HASH(memcached_behavior.MEMCACHED_BEHAVIOR_HASH),
    KETAMA(memcached_behavior.MEMCACHED_BEHAVIOR_KETAMA),
    SOCKET_SEND_SIZE(memcached_behavior.MEMCACHED_BEHAVIOR_SOCKET_SEND_SIZE),
    SOCKET_RECV_SIZE(memcached_behavior.MEMCACHED_BEHAVIOR_SOCKET_RECV_SIZE),
    CACHE_LOOKUPS(memcached_behavior.MEMCACHED_BEHAVIOR_CACHE_LOOKUPS),
    SUPPORT_CAS(memcached_behavior.MEMCACHED_BEHAVIOR_SUPPORT_CAS),
    POLL_TIMEOUT(memcached_behavior.MEMCACHED_BEHAVIOR_POLL_TIMEOUT),
    DISTRIBUTION(memcached_behavior.MEMCACHED_BEHAVIOR_DISTRIBUTION),
    BUFFER_REQUESTS(memcached_behavior.MEMCACHED_BEHAVIOR_BUFFER_REQUESTS),
    USER_DATA(memcached_behavior.MEMCACHED_BEHAVIOR_USER_DATA),
    SORT_HOSTS(memcached_behavior.MEMCACHED_BEHAVIOR_SORT_HOSTS),
    VERIFY_KEY(memcached_behavior.MEMCACHED_BEHAVIOR_VERIFY_KEY),
    CONNECT_TIMEOUT(memcached_behavior.MEMCACHED_BEHAVIOR_CONNECT_TIMEOUT),
    RETRY_TIMEOUT(memcached_behavior.MEMCACHED_BEHAVIOR_RETRY_TIMEOUT),
    KETAMA_WEIGHTED(memcached_behavior.MEMCACHED_BEHAVIOR_KETAMA_WEIGHTED),
    KETAMA_HASH(memcached_behavior.MEMCACHED_BEHAVIOR_KETAMA_HASH),
    BINARY_PROTOCOL(memcached_behavior.MEMCACHED_BEHAVIOR_BINARY_PROTOCOL),
    SND_TIMEOUT(memcached_behavior.MEMCACHED_BEHAVIOR_SND_TIMEOUT),
    RCV_TIMEOUT(memcached_behavior.MEMCACHED_BEHAVIOR_RCV_TIMEOUT),
    SERVER_FAILURE_LIMIT(memcached_behavior.MEMCACHED_BEHAVIOR_SERVER_FAILURE_LIMIT),
    IO_MSG_WATERMARK(memcached_behavior.MEMCACHED_BEHAVIOR_IO_MSG_WATERMARK),
    IO_BYTES_WATERMARK(memcached_behavior.MEMCACHED_BEHAVIOR_IO_BYTES_WATERMARK),
    IO_KEY_PREFETCH(memcached_behavior.MEMCACHED_BEHAVIOR_IO_KEY_PREFETCH),
    HASH_WITH_PREFIX_KEY(memcached_behavior.MEMCACHED_BEHAVIOR_HASH_WITH_PREFIX_KEY),
    NOREPLY(memcached_behavior.MEMCACHED_BEHAVIOR_NOREPLY),
    USE_UDP(memcached_behavior.MEMCACHED_BEHAVIOR_USE_UDP),
    AUTO_EJECT_HOSTS(memcached_behavior.MEMCACHED_BEHAVIOR_AUTO_EJECT_HOSTS),
    NUMBER_OF_REPLICAS(memcached_behavior.MEMCACHED_BEHAVIOR_NUMBER_OF_REPLICAS),
    RANDOMIZE_REPLICA_READ(memcached_behavior.MEMCACHED_BEHAVIOR_RANDOMIZE_REPLICA_READ),
    CORK(memcached_behavior.MEMCACHED_BEHAVIOR_CORK),
    TCP_KEEPALIVE(memcached_behavior.MEMCACHED_BEHAVIOR_TCP_KEEPALIVE),
    TCP_KEEPIDLE(memcached_behavior.MEMCACHED_BEHAVIOR_TCP_KEEPIDLE),
    MAX(memcached_behavior.MEMCACHED_BEHAVIOR_MAX),
    ;
    
    private static final Map<BehaviorType> map = new Map<BehaviorType>(BehaviorType.class);
    private final int value;
    private BehaviorType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    
    public static BehaviorType get(int behavior_value){
        return map.get(behavior_value);
    }

}
