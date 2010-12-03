package libmemcached.wrapper;

import libmemcached.exception.LibMemcachedException;

import org.junit.Test;

public class MemcachedAnalyzeTest {

    @Test
    public void testFree() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        MemcachedStats stats = client.createStats("cmd_get");
        MemcachedAnalyze analyze = client.createAnalyze(stats);
        analyze.free();
    }

}
