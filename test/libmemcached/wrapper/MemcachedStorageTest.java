package libmemcached.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.ReturnType;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MemcachedStorageTest {
    
    protected MemcachedClient client;
    
    protected MemcachedServerList servers;
    
    @Before
    public void setup(){
        client = new MemcachedClient();
    }
    
    @After
    public void destroy(){
        if(null != servers){
            client.getStorage().flush(0);
            servers = null;
        }
        client.getBehavior().set(BehaviorType.SUPPORT_CAS, 0);
        client = null;
    }
    
    private void connectServer() throws LibMemcachedException {
        servers = client.getServerList();
        servers.append("localhost", 11211);
        servers.push();
    }
    
    private void behaviorCas(){
        client.getBehavior().set(BehaviorType.SUPPORT_CAS, 1);
    }
    
    @Test
    public void get_no_set() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        Assert.assertNull(storage.get("key-1"));
    }
    
    @Test
    public void get() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        Assert.assertEquals(storage.set("key-1", "value-1", 10, 0), ReturnType.SUCCESS);
        
        Assert.assertEquals(storage.get("key-1"), "value-1");
    }
    
    @Test
    public void getResult() throws LibMemcachedException {
        connectServer();
        
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
        connectServer();
        
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
        storage.getMulti(new Fetcher() {
            public void fetch(SimpleResult result) {
                Assert.assertEquals(map.get(result.key), result.value);
                map.remove(result.key);
            }
        }, map.keySet().toArray(new String[5]));
        Assert.assertEquals(map.size(), 0);
    }
    
    @Test
    public void gets_no_store() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        Assert.assertNull(storage.gets("hoge"));
    }
    
    @Test
    public void gets_no_behavior_set() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        storage.set("hoge", "1234", 10, 0);
        MemcachedResult result = storage.gets("hoge");
        System.out.println("cas => " + result.getCAS());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getCAS(), 0);
    }

    @Test
    public void gets_cas() throws LibMemcachedException {
        connectServer();
        behaviorCas();
        
        MemcachedStorage storage = client.getStorage();
        storage.set("hoge", "1234", 10, 0);
        MemcachedResult result = storage.gets("hoge");
        System.out.println("cas => " + result.getCAS());
        Assert.assertNotNull(result);
        Assert.assertTrue(-1 < result.getCAS());
    }

    @Test
    public void gets_1() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        storage.set("key-1", "value-1", 10, 0);
        
        {
            MemcachedResult result = storage.gets("key-1");
            Assert.assertEquals(result.getKey(), "key-1");
            Assert.assertEquals(result.getValue(), "value-1");
            Assert.assertNotNull(result.getCAS());
        }
        {
            MemcachedResult result = storage.gets("key-2");
            Assert.assertNull(result);
        }
    }
    
    @Test
    public void gets_2() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        storage.set("key-1", "value-1", 10, 0);
        storage.set("key-2", "value-2", 10, 0);
        
        {
            MemcachedResult result = storage.gets("key-1");
            Assert.assertEquals(result.getKey(), "key-1");
            Assert.assertEquals(result.getValue(), "value-1");
            Assert.assertNotNull(result.getCAS());
        }
        {
            MemcachedResult result = storage.gets("key-2");
            Assert.assertEquals(result.getKey(), "key-2");
            Assert.assertEquals(result.getValue(), "value-2");
            Assert.assertNotNull(result.getCAS());
        }
        {
            MemcachedResult result = storage.gets("key-3");
            Assert.assertNull(result);
        }
    }
    
    @Test
    public void gets_set() throws LibMemcachedException {
        connectServer();
        
        MemcachedStorage storage = client.getStorage();
        storage.gets("test");
        storage.set("test", "test-value", 10, 0);
        
        Assert.assertEquals(storage.get("test"), "test-value");
    }
    
    @Test
    public void gets_cas_2() throws LibMemcachedException {
        connectServer();
        behaviorCas();
        
        MemcachedStorage storage = client.getStorage();
        storage.gets("test");
        storage.set("test", "test-value", 10, 0);
        
        {
            MemcachedResult result = storage.gets("test");
            ReturnType rt = storage.cas("test", "test-value2", 10, 0, result.getCAS());
            if(!ReturnType.SUCCESS.equals(rt)){
                Assert.fail();
            }
        }
        {
            MemcachedResult result = storage.gets("test");
            ReturnType rt = storage.cas("test", "test-value3", 10, 0, result.getCAS());
            if(!ReturnType.SUCCESS.equals(rt)){
                Assert.fail();
            }
        }
        Assert.assertEquals(storage.get("test"), "test-value3");
    }
    
    @Test
    public void cas() throws LibMemcachedException {
        connectServer();
        behaviorCas();
        
        MemcachedStorage storage = client.getStorage();
        {
            ReturnType rt = storage.cas("test", "value-1", 10, 0, 0);
            // cas key dnt accept: 0
            Assert.assertEquals(rt, ReturnType.PROTOCOL_ERROR);
        }
        {
            ReturnType rt = storage.cas("test", "value-1", 10, 0, 1);
            Assert.assertEquals(rt, ReturnType.NOTFOUND);
        }
        
        MemcachedResult result = storage.gets("test");
        Assert.assertNull(result);
    }
    
    @Test
    public void cas_MT() throws LibMemcachedException, InterruptedException, ExecutionException {
        connectServer();
        behaviorCas();
        
        class T implements Callable<String> {
            MemcachedStorage s;
            T(MemcachedStorage s){
                this.s = s;
            }
            public String call() throws Exception {
                int i = 0;
                while(true){
                    if(10 < i++){
                        throw new RuntimeException("max execution");
                    }
                    
                    MemcachedResult r = s.gets("cas-test");
                    if(r == null){
                        s.set("cas-test", "0", 10, 0);
                        return "0";
                    }
                    int val = Integer.parseInt(r.getValue());
                    String valStr = Integer.toString(val + 1);
                    ReturnType rt = s.cas("cas-test", valStr, 10, 0, r.getCAS());
                    if(!ReturnType.SUCCESS.equals(rt)){
                        continue;
                    }
                    return valStr;
                }
            }
        }
        
        List<String> accepts = new ArrayList<String>();
        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        for(int i = 0; i < 50; i++){
            tasks.add(new T(client.getStorage()));
            accepts.add(Integer.toString(i));
        }
        
        ExecutorService exec = Executors.newSingleThreadExecutor();
        List<Future<String>> futures = exec.invokeAll(tasks);
        for(Future<String> f: futures){
            String v = f.get();
            System.out.println(v);
            Assert.assertTrue(accepts.contains(v));
            accepts.remove(v);
        }
        Assert.assertTrue(accepts.isEmpty());
    }
    
    @Test
    @Ignore("CAS does not supported BUFFER_REQUESTS behavior")
    public void cas_MT_with_behavior_buffer() throws LibMemcachedException, InterruptedException, ExecutionException {
        connectServer();
        behaviorCas();
        client.getBehavior().set(BehaviorType.BUFFER_REQUESTS, true);
        
        class T implements Callable<String> {
            MemcachedStorage s;
            T(MemcachedStorage s){
                this.s = s;
            }
            public String call() throws Exception {
                int i = 0;
                while(true){
                    if(10 < i++){
                        throw new RuntimeException("max execution");
                    }
                    
                    MemcachedResult r = s.gets("cas-test");
                    if(r == null){
                        s.set("cas-test", "0", 10, 0);
                        return "0";
                    }
                    int val = Integer.parseInt(r.getValue());
                    String valStr = Integer.toString(val + 1);
                    ReturnType rt = s.cas("cas-test", valStr, 10, 0, r.getCAS());
                    if(!ReturnType.SUCCESS.equals(rt)){
                        continue;
                    }
                    return valStr;
                }
            }
        }
        
        List<String> accepts = new ArrayList<String>();
        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        for(int i = 0; i < 50; i++){
            tasks.add(new T(client.getStorage()));
            accepts.add(Integer.toString(i));
        }
        
        ExecutorService exec = Executors.newSingleThreadExecutor();
        List<Future<String>> futures = exec.invokeAll(tasks);
        for(Future<String> f: futures){
            String v = f.get();
            System.out.println(v);
            Assert.assertTrue(accepts.contains(v));
            accepts.remove(v);
        }
        Assert.assertTrue(accepts.isEmpty());
    }
    
}
