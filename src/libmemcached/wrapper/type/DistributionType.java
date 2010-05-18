package libmemcached.wrapper.type;

import libmemcached.constants.memcached_server_distribution_t;

public enum DistributionType implements Type<DistributionType> {
    MODULA(memcached_server_distribution_t.MEMCACHED_DISTRIBUTION_MODULA),
    CONSISTENT(memcached_server_distribution_t.MEMCACHED_DISTRIBUTION_CONSISTENT),
    CONSISTENT_KETAMA(memcached_server_distribution_t.MEMCACHED_DISTRIBUTION_CONSISTENT_KETAMA),
    RANDOM(memcached_server_distribution_t.MEMCACHED_DISTRIBUTION_RANDOM),
    CONSISTENT_KETAMA_SPY(memcached_server_distribution_t.MEMCACHED_DISTRIBUTION_CONSISTENT_KETAMA_SPY),
    CONSISTENT_MAX(memcached_server_distribution_t.MEMCACHED_DISTRIBUTION_CONSISTENT_MAX),
    ;
    
    private static final Map<DistributionType> map = new Map<DistributionType>(DistributionType.class);
    private final int value;
    private DistributionType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    
    public static DistributionType get(int distribution_value){
        return map.get(distribution_value);
    }

}
