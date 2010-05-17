package libmemcached.wrapper;

import libmemcached.constants.memcached_connection;

public enum ConnectionType {
    UNKNOWN(memcached_connection.MEMCACHED_CONNECTION_UNKNOWN),
    TCP(memcached_connection.MEMCACHED_CONNECTION_TCP),
    UDP(memcached_connection.MEMCACHED_CONNECTION_UDP),
    UNIX_SOCKET(memcached_connection.MEMCACHED_CONNECTION_UNIX_SOCKET),
    MAX(memcached_connection.MEMCACHED_CONNECTION_MAX)
    ;
    
    private final int value;
    private ConnectionType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    
    public static ConnectionType get(int connection_type_value){
        for(ConnectionType ct: values()){
            if(ct.value == connection_type_value){
                return ct;
            }
        }
        return MAX;
    }

}
