package libmemcached;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.MemcachedClient;
import libmemcached.wrapper.MemcachedStorage;
import libmemcached.wrapper.type.ReturnType;

public class Example_wrapper {
    public static void main(String...args){
        MemcachedClient client = new MemcachedClient();
        System.out.println(client.libraryVersion());
        ReturnType err = client.addServer("localhost", 11211);
        System.out.println(err);
        
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