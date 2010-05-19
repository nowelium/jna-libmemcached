package libmemcached;

import static libmemcached.wrapper.function.Memcached.memcached_create;
import static libmemcached.wrapper.function.Memcached.memcached_free;
import static libmemcached.wrapper.function.Server.memcached_server_add;
import static libmemcached.wrapper.function.Storage.memcached_set;
import static libmemcached.wrapper.function.Get.memcached_get;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.SimpleResult;

public class Functional {
    
    public static void main(String...args){
        memcached_st mmc = memcached_create();
        try {
            memcached_server_add(mmc, "localhost", 11211);
            
            memcached_set(mmc, "hello", "world", 10, 0);
            
            try {
                SimpleResult result = memcached_get(mmc, "hello");
                System.out.println("value = " + result.getValue() + ", flags = " + result.getFlags());
            } catch (LibMemcachedException e) {
                e.printStackTrace();
            }
        } finally {
            memcached_free(mmc);
        }
    }

}
