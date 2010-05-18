package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_server_list_st;


public class Servers_func {
    
    public static void main(String...args){
        memcached memcached = LibMemcached.memcached;
        memcached_server_list_st ss = null;
        
        com.sun.jna.ptr.IntByReference err = new com.sun.jna.ptr.IntByReference();
        ss = memcached.memcached_server_list_append(ss, "localhost", 11211, err);
        System.out.println(new String(ss.hostname));
        System.out.println(err.getValue());
        ss = memcached.memcached_server_list_append(ss, "localhost", 11212, err);
        System.out.println(err.getValue());
        ss = memcached.memcached_server_list_append(ss, "localhost", 11213, err);
        System.out.println(err.getValue());
        ss = memcached.memcached_server_list_append(ss, "localhost", 11214, err);
        System.out.println(err.getValue());
        System.out.println(memcached.memcached_server_list_count(ss));
        memcached_st mmc = memcached.memcached_create(null);
        memcached.memcached_server_push(mmc, ss);
        {
            String key = "hello1";
            libmemcached.compat.size_t key_length = new libmemcached.compat.size_t(key.length());
            String value = "world1";
            libmemcached.compat.size_t value_length = new libmemcached.compat.size_t(value.length());
            libmemcached.compat.time_t expiration = new libmemcached.compat.time_t(10);
            memcached.memcached_set(mmc, key, key_length, value, value_length, expiration, 0);
        }
        {
            String key = "hello2";
            libmemcached.compat.size_t key_length = new libmemcached.compat.size_t(key.length());
            String value = "world2";
            libmemcached.compat.size_t value_length = new libmemcached.compat.size_t(value.length());
            libmemcached.compat.time_t expiration = new libmemcached.compat.time_t(10);
            memcached.memcached_set(mmc, key, key_length, value, value_length, expiration, 0);
        }
    }

}