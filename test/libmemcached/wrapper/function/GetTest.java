package libmemcached.wrapper.function;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.types.memcached_server_list_st;
import libmemcached.wrapper.type.ReturnType;

import org.junit.Assert;
import org.junit.Test;

public class GetTest {
    
    static {
        //System.setProperty("jna.dump_memory", "true");
    }
    
    @Test
    public void memcached_mget_no_servers() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            Assert.assertEquals(Get.memcached_mget(mmc, "key-1"), ReturnType.NO_SERVERS);
        } finally {
            Memcached.memcached_free(mmc);
        }
    }

    @Test
    public void memcached_mget_not_store() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            memcached_server_list_st servers = null;
            try {
                servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
                ServerList.memcached_server_push(mmc, servers);
                
                try {
                    Assert.assertEquals(Get.memcached_mget(mmc, "key-1"), ReturnType.SUCCESS);
                    Get.memcached_fetch_result(mmc);
                } catch(LibMemcachedException e){
                    Assert.assertEquals(e.getCode(), ReturnType.END.getValue());
                }
            } finally {
                ServerList.memcached_server_list_free(servers);
            }
        } finally {
            Memcached.memcached_free(mmc);
        }
    }
    
    @Test
    public void memcached_mget_single() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            memcached_server_list_st servers = null;
            try {
                servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
                ServerList.memcached_server_push(mmc, servers);
                
                Storage.memcached_set(mmc, "key-1", "value-1", 10, 0);
                Assert.assertEquals(Get.memcached_mget(mmc, "key-1"), ReturnType.SUCCESS);
                memcached_result_st result = Get.memcached_fetch_result(mmc);
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-1");
                Assert.assertEquals(Result.memcached_result_value(result), "value-1");
                
                Result.memcached_result_free(result);
                
                Assert.assertNull(Get.memcached_fetch_result(mmc));
            } finally {
                ServerList.memcached_server_list_free(servers);
            }
        } finally {
            Memcached.memcached_free(mmc);
        }
    }

    @Test
    public void memcached_mget_1set_2get() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            memcached_server_list_st servers = null;
            try {
                servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
                ServerList.memcached_server_push(mmc, servers);
                
                Storage.memcached_set(mmc, "key-1", "value-1", 10, 0);
                Assert.assertEquals(Get.memcached_mget(mmc, "key-1", "key-2"), ReturnType.SUCCESS);
                memcached_result_st result = Get.memcached_fetch_result(mmc);
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-1");
                Assert.assertEquals(Result.memcached_result_value(result), "value-1");
                
                Result.memcached_result_free(result);
                
                Assert.assertNull(Get.memcached_fetch_result(mmc));
            } finally {
                ServerList.memcached_server_list_free(servers);
            }
        } finally {
            Memcached.memcached_free(mmc);
        }
    }

    @Test
    public void memcached_mget_2set_1get() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            memcached_server_list_st servers = null;
            try {
                servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
                ServerList.memcached_server_push(mmc, servers);
                
                Storage.memcached_set(mmc, "key-1", "value-1", 10, 0);
                Storage.memcached_set(mmc, "key-2", "value-2", 10, 0);
                Assert.assertEquals(Get.memcached_mget(mmc, "key-1"), ReturnType.SUCCESS);
                memcached_result_st result = Get.memcached_fetch_result(mmc);
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-1");
                Assert.assertEquals(Result.memcached_result_value(result), "value-1");
                
                Result.memcached_result_free(result);
                
                Assert.assertNull(Get.memcached_fetch_result(mmc));
            } finally {
                ServerList.memcached_server_list_free(servers);
            }
        } finally {
            Memcached.memcached_free(mmc);
        }
    }

    @Test
    public void memcached_mget_2set_2get_1fetch() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            memcached_server_list_st servers = null;
            try {
                servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
                ServerList.memcached_server_push(mmc, servers);
                
                Storage.memcached_set(mmc, "key-1", "value-1", 10, 0);
                Storage.memcached_set(mmc, "key-2", "value-2", 10, 0);
                
                Assert.assertEquals(Get.memcached_mget(mmc, "key-1", "key-2"), ReturnType.SUCCESS);
                memcached_result_st result = Get.memcached_fetch_result(mmc);
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-1");
                Assert.assertEquals(Result.memcached_result_value(result), "value-1");
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-1");
                Assert.assertEquals(Result.memcached_result_value(result), "value-1");
                
                memcached_result_st r = Get.memcached_fetch_result(mmc);
                Assert.assertNotNull(r); // more results
                Result.memcached_result_free(r);
                
                Result.memcached_result_free(result);
            } finally {
                ServerList.memcached_server_list_free(servers);
            }
        } finally {
            Memcached.memcached_free(mmc);
        }
    }

    @Test
    public void memcached_mget_2set_2get_2fetch() throws LibMemcachedException {
        memcached_st mmc = Memcached.memcached_create();
        try {
            memcached_server_list_st servers = null;
            try {
                servers = ServerList.memcached_server_list_append(servers, "localhost", 11211);
                ServerList.memcached_server_push(mmc, servers);
                
                Storage.memcached_set(mmc, "key-1", "value-1", 10, 0);
                Storage.memcached_set(mmc, "key-2", "value-2", 10, 0);
                
                Assert.assertEquals(Get.memcached_mget(mmc, "key-1", "key-2"), ReturnType.SUCCESS);
                memcached_result_st result = Get.memcached_fetch_result(mmc);
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-1");
                Assert.assertEquals(Result.memcached_result_value(result), "value-1");
                
                Result.memcached_result_free(result);
                result = Get.memcached_fetch_result(mmc);
                
                Assert.assertEquals(Result.memcached_result_key_value(result), "key-2");
                Assert.assertEquals(Result.memcached_result_value(result), "value-2");
                
                Result.memcached_result_free(result);
            } finally {
                ServerList.memcached_server_list_free(servers);
            }
        } finally {
            Memcached.memcached_free(mmc);
        }
    }
    
}
