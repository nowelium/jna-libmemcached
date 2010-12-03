package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.Memcached.memcached_create;
import static libmemcached.wrapper.function.Memcached.memcached_free;
import static libmemcached.wrapper.function.Stats.memcached_stat;
import static libmemcached.wrapper.function.Stats.memcached_stat_free;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.stats.memcached_stat_st;

import org.junit.Assert;
import org.junit.Test;

public class StatsTest {
    
    @Test
    public void testMemcached_statMemcached() throws LibMemcachedException {
        /*
        memcached_st memc = memcached_create();
        memcached_stat_st stat = memcached_stat(memc);
        Assert.assertNotNull(stat);
        memcached_stat_free(memc, stat);
        memcached_free(memc);
        */
    }

    @Test
    public void testMemcached_statMemcached_stString() throws LibMemcachedException {
        /*
        memcached_st memc = memcached_create();
        {
            memcached_stat_st stat = memcached_stat(memc, null);
            Assert.assertNotNull(stat);
            memcached_stat_free(memc, stat);
        }
        {
            memcached_stat_st stat = memcached_stat(memc, "123456789");
            Assert.assertNotNull(stat);
            memcached_stat_free(memc, stat);
        }
        memcached_free(memc);
        */
    }

    @Test
    public void testMemcached_statMemcached_stStringStringInt() throws LibMemcachedException {
        memcached_st memc = memcached_create();
        {
            memcached_stat_st stat = memcached_stat(memc, null, "localhost", 11211);
            Assert.assertNotNull(stat);
            memcached_stat_free(memc, stat);
        }
        /*
        {
            memcached_stat_st stat = memcached_stat(memc, "", "localhost", 11211); // fail args == ""
            Assert.assertNotNull(stat);
            memcached_stat_free(memc, stat);
        }
        */
        memcached_free(memc);
    }

}
