package libmemcached;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.MemcachedClient;
import libmemcached.wrapper.MemcachedStorage;
import libmemcached.wrapper.ReturnType;

public class Wrapper {
    public static void main(String...args){
        MemcachedClient client = new MemcachedClient();
        System.out.println(client.libraryVersion());
        try {
            client.addServer("localhost", 11211);
        } catch(LibMemcachedException e){
            e.printStackTrace();
        }
        
        MemcachedStorage storage = client.getStorage();
        
        ReturnType rt = storage.set("hoge", "1234", 10, 0);
        System.out.println(rt);
        
        try {
            System.out.println(storage.get("hoge"));
        } catch(LibMemcachedException e){
            e.printStackTrace();
        }
        
    }

}
