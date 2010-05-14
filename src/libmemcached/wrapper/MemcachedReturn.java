package libmemcached.wrapper;

import libmemcached.memcached_constants.memcached_return;

public enum MemcachedReturn {
    SUCCESS(memcached_return.MEMCACHED_SUCCESS),
    FAILURE(memcached_return.MEMCACHED_FAILURE),
    HOST_LOOKUP_FAILURE(memcached_return.MEMCACHED_HOST_LOOKUP_FAILURE),
    CONNECTION_FAILURE(memcached_return.MEMCACHED_CONNECTION_FAILURE),
    CONNECTION_BIND_FAILURE(memcached_return.MEMCACHED_CONNECTION_BIND_FAILURE),
    WRITE_FAILURE(memcached_return.MEMCACHED_WRITE_FAILURE),
    READ_FAILURE(memcached_return.MEMCACHED_READ_FAILURE),
    UNKNOWN_READ_FAILURE(memcached_return.MEMCACHED_UNKNOWN_READ_FAILURE),
    PROTOCOL_ERROR(memcached_return.MEMCACHED_PROTOCOL_ERROR),
    CLIENT_ERROR(memcached_return.MEMCACHED_CLIENT_ERROR),
    SERVER_ERROR(memcached_return.MEMCACHED_SERVER_ERROR),
    CONNECTION_SOCKET_CREATE_FAILURE(memcached_return.MEMCACHED_CONNECTION_SOCKET_CREATE_FAILURE),
    DATA_EXISTS(memcached_return.MEMCACHED_DATA_EXISTS),
    DATA_DOES_NOT_EXIST(memcached_return.MEMCACHED_DATA_DOES_NOT_EXIST),
    NOTSTORED(memcached_return.MEMCACHED_NOTSTORED),
    STORED(memcached_return.MEMCACHED_STORED),
    NOTFOUND(memcached_return.MEMCACHED_NOTFOUND),
    MEMORY_ALLOCATION_FAILURE(memcached_return.MEMCACHED_MEMORY_ALLOCATION_FAILURE),
    PARTIAL_READ(memcached_return.MEMCACHED_PARTIAL_READ),
    SOME_ERRORS(memcached_return.MEMCACHED_SOME_ERRORS),
    NO_SERVERS(memcached_return.MEMCACHED_NO_SERVERS),
    END(memcached_return.MEMCACHED_END),
    DELETED(memcached_return.MEMCACHED_DELETED),
    VALUE(memcached_return.MEMCACHED_VALUE),
    STAT(memcached_return.MEMCACHED_STAT),
    ITEM(memcached_return.MEMCACHED_ITEM),
    ERRNO(memcached_return.MEMCACHED_ERRNO),
    FAIL_UNIX_SOCKET(memcached_return.MEMCACHED_FAIL_UNIX_SOCKET),
    NOT_SUPPORTED(memcached_return.MEMCACHED_NOT_SUPPORTED),
    NO_KEY_PROVIDED(memcached_return.MEMCACHED_NO_KEY_PROVIDED),
    FETCH_NOTFINISHED(memcached_return.MEMCACHED_FETCH_NOTFINISHED),
    TIMEOUT(memcached_return.MEMCACHED_TIMEOUT),
    BUFFERED(memcached_return.MEMCACHED_BUFFERED),
    BAD_KEY_PROVIDED(memcached_return.MEMCACHED_BAD_KEY_PROVIDED),
    INVALID_HOST_PROTOCOL(memcached_return.MEMCACHED_INVALID_HOST_PROTOCOL),
    SERVER_MARKED_DEAD(memcached_return.MEMCACHED_SERVER_MARKED_DEAD),
    UNKNOWN_STAT_KEY(memcached_return.MEMCACHED_UNKNOWN_STAT_KEY),
    MAXIMUM_RETURN(memcached_return.MEMCACHED_MAXIMUM_RETURN)
    ;
    
    private final int value;
    private MemcachedReturn(int value){
        this.value = value;
    }
    public int value(){
        return value;
    }
    
    public static MemcachedReturn get(int memcached_return_value){
        for(MemcachedReturn r: values()){
            if(r.value == memcached_return_value){
                return r;
            }
        }
        return MAXIMUM_RETURN;
    }

}
