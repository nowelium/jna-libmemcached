package libmemcached;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.MemcachedClient;
import libmemcached.wrapper.MemcachedServerList;

public class Servers {
    
    public static void main(String...args){
//        memcached memcached = LibMemcached.memcached;
//        memcached_server_st servers = null;
//        
//        IntByReference err = new IntByReference();
//        servers = memcached.memcached_server_list_append(servers, "localhost", 11211, err);
//        System.out.println(new String(servers.hostname));
//        System.out.println(err.getValue());
//        servers = memcached.memcached_server_list_append(servers, "localhost", 11212, err);
//        System.out.println(err.getValue());
//        servers = memcached.memcached_server_list_append(servers, "localhost", 11213, err);
//        System.out.println(err.getValue());
//        servers = memcached.memcached_server_list_append(servers, "localhost", 11214, err);
//        System.out.println(err.getValue());
//        System.out.println(memcached.memcached_server_list_count(servers));
        
        
        MemcachedClient client = new MemcachedClient();
        MemcachedServerList servers = client.getServerList();
        try {
            servers.append("localhost", 11211);
            servers.append("localhost", 21211);
            servers.append("localhost", 31211);
            servers.append("localhost", 41211);
            
            System.out.println(servers.count());
        } catch(LibMemcachedException e){
            e.printStackTrace();
        }
    }

}