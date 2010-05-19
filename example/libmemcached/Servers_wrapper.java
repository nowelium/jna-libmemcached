package libmemcached;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.MemcachedClient;
import libmemcached.wrapper.MemcachedServerList;
import libmemcached.wrapper.MemcachedStorage;

public class Servers_wrapper {
    
    public static void main(String...args){
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