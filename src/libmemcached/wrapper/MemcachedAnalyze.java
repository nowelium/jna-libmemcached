package libmemcached.wrapper;

import static libmemcached.wrapper.function.Analyze.memcached_analyze;
import static libmemcached.wrapper.function.Analyze.memcached_analyze_free;
import libmemcached.analyze.memcached_analysis_st;
import libmemcached.exception.LibMemcachedException;

public class MemcachedAnalyze {

    protected final memcached_analysis_st analysis_st;
    
    @SuppressWarnings("unused")
    private final Object finalizer = new Object(){
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            memcached_analyze_free(analysis_st);
        }
    };
    
    protected MemcachedAnalyze(MemcachedClient memcached, MemcachedStats stats) throws LibMemcachedException {
        this.analysis_st = memcached_analyze(memcached.memcached_st, stats.stat_st);
    }
    
}
