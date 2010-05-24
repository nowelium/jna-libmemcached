package libmemcached.wrapper;

import java.util.HashMap;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.type.ReturnType;

import org.junit.Assert;
import org.junit.Test;

public class MemcachedStorageTest {
    
    @Test
    public void get() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.addServer("localhost", 11211);
        
        MemcachedStorage storage = client.getStorage();
        Assert.assertEquals(storage.set("key-1", "value-1", 10, 0), ReturnType.SUCCESS);
        
        SimpleResult result = storage.getResult("key-1");
        Assert.assertEquals(result.getKey(), "key-1");
        Assert.assertEquals(result.getValue(), "value-1");
        Assert.assertEquals(result.getFlags(), 0);
    }
    
    @Test
    public void getMulti(){
    }
    
    @Test
    public void getMulti_fetcher() throws LibMemcachedException {
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
        
        final HashMap<String, String> map = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            put("key-1", "value-1");
            put("key-2", "value-2");
            put("key-3", "value-3");
            put("key-4", "value-4");
            put("key-5", "value-5");
        }};
        storage.getMulti(new MemcachedStorage.Fetcher() {
            public void fetch(SimpleResult result) {
                Assert.assertEquals(map.get(result.key), result.value);
                map.remove(result.key);
            }
        }, map.keySet().toArray(new String[5]));
        Assert.assertEquals(map.size(), 0);
    }

}