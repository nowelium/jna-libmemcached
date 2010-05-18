package libmemcached.wrapper;

import libmemcached.memcached;
import libmemcached.analyze.memcached_analysis_st;

public class MemcachedAnalyze {

    protected final memcached handler;
    protected final memcached_analysis_st analysis_st;
    
    protected MemcachedAnalyze(memcached_analysis_st analysis_st) {
        this.handler = MemcachedClient.handler;
        this.analysis_st = analysis_st;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        handler.memcached_analyze_free(analysis_st);
    }

}
