package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import com.sun.jna.ptr.IntByReference;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.server.memcached_server_st;
import libmemcached.types.memcached_server_list_st;
import libmemcached.wrapper.type.ReturnType;

public class ServerList extends Function {
    
    public static void memcached_server_list_free(memcached_server_list_st ptr){
        getMemcached().memcached_server_list_free(ptr);
    }
    
    public static ReturnType memcached_server_push(memcached_st ptr, memcached_server_list_st list){
        final int rc = getMemcached().memcached_server_push(ptr, list);
        return ReturnType.get(rc);
    }
    
    public static memcached_server_list_st memcached_server_list_append(memcached_server_list_st server_list_st, String hostname, int port) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        
        final memcached_server_list_st list_st = getMemcached().memcached_server_list_append(server_list_st, hostname, port, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(rc));
        }
        return list_st;
    }

    public static memcached_server_list_st memcached_server_list_append_with_weight(memcached_server_list_st server_list_st, String hostname, int port, int weight) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        
        final memcached_server_list_st list_st = getMemcached().memcached_server_list_append_with_weight(server_list_st, hostname, port, weight, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(rc));
        }
        return list_st;
    }
    
    public static int memcached_server_list_count(memcached_server_list_st ptr){
        return getMemcached().memcached_server_list_count(ptr);
    }
    
    public static int memcached_servers_set_count(memcached_server_list_st servers, int count){
        return getMemcached().memcached_servers_set_count(servers, count);
    }
    
    public static memcached_server_st memcached_server_list(memcached_st ptr){
        return getMemcached().memcached_server_list(ptr);
    }
    
    public static void memcached_server_list_set(memcached_st self, memcached_server_list_st list){
        getMemcached().memcached_server_list_set(self, list);
    }
}
