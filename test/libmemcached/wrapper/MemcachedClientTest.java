package libmemcached.wrapper;

import org.junit.Test;

public class MemcachedClientTest {
    
    @Test
    public void test_free() {
        MemcachedClient client = new MemcachedClient();
        client.free();
        client.free(); // double free
    }

}
