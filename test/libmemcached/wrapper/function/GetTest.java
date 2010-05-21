package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

import org.junit.Assert;
import org.junit.Test;


public class GetTest {
    
    @Test
    public void memcached_mget(){
        memcached_st mmc = Memcached.memcached_create();
        try {
            //Assert.assertEquals(Get.memcached_mget(mmc, "key-1"), ReturnType.NO_SERVERS);
            
            Server.memcached_server_add(mmc, "localhost", 11211);
            Assert.assertEquals(Get.memcached_mget(mmc, "key-1"), ReturnType.SUCCESS);
            try {
                System.out.println(Get.memcached_fetch_result(mmc));
            } catch(Exception e){
                e.printStackTrace();
            }
            
            //Assert.assertEquals(Get.memcached_mget(mmc, "key-1", "key-2", "key-3"), ReturnType.SUCCESS);
        } finally {
            Memcached.memcached_free(mmc);
        }
    }

}
