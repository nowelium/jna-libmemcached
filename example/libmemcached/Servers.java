package libmemcached;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.MemcachedClient;
import libmemcached.wrapper.MemcachedServerList;
import libmemcached.wrapper.MemcachedStorage;

public class Servers {
    
    public static void main(String...args){
//        memcached memcached = LibMemcached.memcached;
//        memcached_server_list_st ss = null;
//        
//        com.sun.jna.ptr.IntByReference err = new com.sun.jna.ptr.IntByReference();
//        ss = memcached.memcached_server_list_append(ss, "localhost", 11211, err);
//        System.out.println(new String(ss.hostname));
//        System.out.println(err.getValue());
//        ss = memcached.memcached_server_list_append(ss, "localhost", 11212, err);
//        System.out.println(err.getValue());
//        ss = memcached.memcached_server_list_append(ss, "localhost", 11213, err);
//        System.out.println(err.getValue());
//        ss = memcached.memcached_server_list_append(ss, "localhost", 11214, err);
//        System.out.println(err.getValue());
//        System.out.println(memcached.memcached_server_list_count(ss));
//        memcached_st mmc = memcached.memcached_create(null);
//        memcached.memcached_server_push(mmc, ss);
//        {
//            String key = "hello1";
//            libmemcached.compat.size_t key_length = new libmemcached.compat.size_t(key.length());
//            String value = "world1";
//            libmemcached.compat.size_t value_length = new libmemcached.compat.size_t(value.length());
//            libmemcached.compat.time_t expiration = new libmemcached.compat.time_t(10);
//            memcached.memcached_set(mmc, key, key_length, value, value_length, expiration, 0);
//        }
//        {
//            String key = "hello2";
//            libmemcached.compat.size_t key_length = new libmemcached.compat.size_t(key.length());
//            String value = "world2";
//            libmemcached.compat.size_t value_length = new libmemcached.compat.size_t(value.length());
//            libmemcached.compat.time_t expiration = new libmemcached.compat.time_t(10);
//            memcached.memcached_set(mmc, key, key_length, value, value_length, expiration, 0);
//        }
        
        MemcachedClient client = new MemcachedClient();
        MemcachedServerList servers = client.getServerList();
        try {
            servers.append("localhost", 11211);
            servers.append("localhost", 11212);
            servers.append("localhost", 11213);
            servers.append("localhost", 11214);
            
            System.out.println(servers.count());
            servers.push();
            
            MemcachedStorage storage = client.getStorage();
            storage.set("hello1", "world1", 10, 0);
            storage.set("hello2", "world2", 10, 0);
            storage.set("hello3", "world3", 10, 0);
            storage.set("hello4", "world4", 10, 0);
        } catch(LibMemcachedException e){
            e.printStackTrace();
        }
    }

}