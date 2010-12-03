package libmemcached.wrapper;

import static org.junit.Assert.*;
import libmemcached.exception.LibMemcachedException;

import org.junit.Test;
import org.junit.Assert;

public class MemcachedStatsTest {
    
    @Test
    public void instance() throws LibMemcachedException {
        {
            MemcachedClient client = new MemcachedClient();
            client.addServer("localhost", 11211);
            MemcachedStats stat = client.createStats(null);
            Assert.assertNotNull(stat);
            System.out.println("cmd_get => " + stat.getValue("cmd_get"));
            stat.free();
            client.free();
        }
        {
            MemcachedClient client = new MemcachedClient();
            MemcachedStats stat = client.createStats(null, "localhost", 11211);
            Assert.assertNotNull(stat);
            System.out.println("cmd_get => " + stat.getValue("cmd_get"));
            stat.free();
            client.free();
        }
    }

    @Test
    public void testGetValue() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.addServer("localhost", 11211);
        
        MemcachedStats stat = client.createStats();
        Assert.assertNotNull(stat.getValue("cmd_get"));
        try {
            System.out.println("cmd_get => " + Long.parseLong(stat.getValue("cmd_get")));
        } catch(NumberFormatException e){
            e.printStackTrace();
            fail("cmd_get was return number");
        }
    }

    @Test
    public void testGetKeys() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.addServer("localhost", 11211);
        
        MemcachedStats stat = client.createStats();
        String[] keys = stat.getKeys();
        
        boolean has_cmd_get = false;
        boolean has_cmd_set = false;
        for(String key: keys){
            System.out.println("key: '" + key + "' => " + stat.getValue(key));
            if(key.equals("cmd_get")){
                has_cmd_get = true;
            }
            if(key.equals("cmd_set")){
                has_cmd_set = true;
            }
        }
        Assert.assertTrue(has_cmd_get);
        Assert.assertTrue(has_cmd_set);
    }

    @Test
    public void testFree() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.addServer("localhost", 11211);
        
        MemcachedStats stat = client.createStats();
        stat.free();
    }

}
