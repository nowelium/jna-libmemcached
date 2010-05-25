package libmemcached.wrapper.function;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.types.memcached_server_list_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.ReturnType;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StorageTest {
    
 protected memcached_st mmc;
    
    protected memcached_server_list_st servers = null;
    
    @Before
    public void memcached_st_create(){
        mmc = Memcached.memcached_create();
    }
    
    @After
    public void memcached_st_destroy(){
        if(servers != null){
            Flush.memcached_flush(mmc, 0);
            ServerList.memcached_server_list_free(servers);
        }
        Memcached.memcached_free(mmc);
    }

    private void connectServer() {
        try {
            servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
            ServerList.memcached_server_push(mmc, servers);
        } catch(LibMemcachedException e){
            Assert.fail(e.getMessage() + ":" + e.getReturnType());
        }
    }
    
    @Test
    public void memcached_cas_unsupport() throws LibMemcachedException {
        connectServer();
        
        // Behavior.memcached_behavior_set(mmc, BehaviorType.SUPPORT_CAS, 1); <= default
        
        Storage.memcached_set(mmc, "hoge", "1234", 10, 0);
        Get.memcached_mget(mmc, "hoge");
        memcached_result_st result_st = Get.memcached_fetch_result(mmc);
        
        System.out.println("cas value => " + Result.memcached_result_cas(result_st));
        ReturnType rc = Storage.memcached_cas(mmc, "hoge", "4567", 10, 0, Result.memcached_result_cas(result_st));
        Assert.assertEquals(ReturnType.PROTOCOL_ERROR, rc);
    }
    
    @Test
    public void memcached_cas_not_eq() throws LibMemcachedException {
        connectServer();
        
        Behavior.memcached_behavior_set(mmc, BehaviorType.SUPPORT_CAS, 1);
        
        Storage.memcached_set(mmc, "hoge", "1234", 10, 0);
        Get.memcached_mget(mmc, "hoge");
        
        ReturnType rc = Storage.memcached_cas(mmc, "hoge", "4567", 10, 0, 123456);
        Assert.assertEquals(ReturnType.DATA_EXISTS, rc);
    }

    @Test
    public void memcached_cas() throws LibMemcachedException {
        connectServer();
        
        Behavior.memcached_behavior_set(mmc, BehaviorType.SUPPORT_CAS, 1);
        
        Storage.memcached_set(mmc, "hoge", "1234", 10, 0);
        Get.memcached_mget(mmc, "hoge");
        memcached_result_st result_st = Get.memcached_fetch_result(mmc);
        
        System.out.println("cas value => " + Result.memcached_result_cas(result_st));
        ReturnType rc = Storage.memcached_cas(mmc, "hoge", "4567", 10, 0, Result.memcached_result_cas(result_st));
        Assert.assertEquals(ReturnType.SUCCESS, rc);
    }
    
}
