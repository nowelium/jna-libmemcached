package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import libmemcached.compat.size_t;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.server.memcached_server_st;
import libmemcached.types.memcached_server_instance_st;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.ptr.IntByReference;

public class Server extends Function {
    
    public static memcached_server_instance_st memcached_server_by_key(memcached_st ptr, String key) throws LibMemcachedException {
        final size_t key_length = new size_t(key.getBytes().length);
        final IntByReference error = new IntByReference();
        final memcached_server_instance_st server_st = getMemcached().memcached_server_by_key(ptr, key, key_length, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return server_st;
    }
    
    public static void memcached_server_error_reset(memcached_server_st ptr){
        getMemcached().memcached_server_error_reset(ptr);
    }
    
    public static void memcached_server_free(memcached_server_st ptr){
        getMemcached().memcached_server_free(ptr);
    }
    
    public static memcached_server_instance_st memcached_server_clone(memcached_server_st destination, memcached_server_st source){
        return getMemcached().memcached_server_clone(destination, source);
    }
    
    public static memcached_server_instance_st memcached_server_get_last_disconnect(memcached_st ptr){
        return getMemcached().memcached_server_get_last_disconnect(ptr);
    }
    
    public static int memcached_server_response_count(memcached_server_instance_st self){
        return getMemcached().memcached_server_response_count(self);
    }
    
    public static String memcached_server_name(memcached_server_instance_st self){
        return getMemcached().memcached_server_name(self);
    }
    
    public static int memcached_server_port(memcached_server_instance_st self){
        return getMemcached().memcached_server_port(self);
    }
    
    public static String memcached_server_error(memcached_server_instance_st ptr){
        return getMemcached().memcached_server_error(ptr);
    }
    
    public static ReturnType memcached_server_add(memcached_st ptr, String hostname, int port){
        final int rc = getMemcached().memcached_server_add(ptr, hostname, port);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_server_add_with_weight(memcached_st ptr, String hostname, int port, int weight){
        final int rc = getMemcached().memcached_server_add_with_weight(ptr, hostname, port, weight);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_server_add_udp(memcached_st ptr, String hostname, int port){
        final int rc = getMemcached().memcached_server_add_udp(ptr, hostname, port);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_server_add_udp_with_weight(memcached_st ptr, String hostname, int port, int weight){
        final int rc = getMemcached().memcached_server_add_udp_with_weight(ptr, hostname, port, weight);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_server_add_unix_socket(memcached_st ptr, String filename){
        final int rc = getMemcached().memcached_server_add_unix_socket(ptr, filename);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_server_add_unix_socket_with_weight(memcached_st ptr, String filename, int weight){
        final int rc = getMemcached().memcached_server_add_unix_socket_with_weight(ptr, filename, weight);
        return ReturnType.get(rc);
    }

}
