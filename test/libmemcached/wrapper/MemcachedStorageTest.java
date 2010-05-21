package libmemcached.wrapper;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.function.Get;
import libmemcached.wrapper.type.ReturnType;

import org.junit.Assert;
import org.junit.Test;

public class MemcachedStorageTest {
    
    @Test
    public void getMulti(){
    }
    
    @Test
    public void getMulti_fetcher(){
        MemcachedClient client = new MemcachedClient();
        client.addServer("localhost", 11211);
        
        int expiration = 10;
        int flags = 0;
        MemcachedStorage storage = client.getStorage();
        Assert.assertEquals(storage.set("key-1", "value-1", expiration, flags), ReturnType.SUCCESS);
        Assert.assertEquals(storage.set("key-2", "value-2", expiration, flags), ReturnType.SUCCESS);
        Assert.assertEquals(storage.set("key-3", "value-3", expiration, flags), ReturnType.SUCCESS);
        Assert.assertEquals(storage.set("key-4", "value-4", expiration, flags), ReturnType.SUCCESS);
        Assert.assertEquals(storage.set("key-5", "value-5", expiration, flags), ReturnType.SUCCESS);
        
        System.out.println(Get.memcached_mget(client.memcached_st, "key-1"));
        
        /*
        try {
            storage.getMulti(new MemcachedStorage.Fetcher() {
                public void fetch(SimpleResult result) {
                    System.out.println(result);
                }
            }, "key-1", "key-2", "key-3", "key-4", "key-5");
        } catch(LibMemcachedException e){
            e.printStackTrace();
        }
        */
    }

}
