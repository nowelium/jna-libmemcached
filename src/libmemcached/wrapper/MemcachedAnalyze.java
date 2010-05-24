package libmemcached.wrapper;

import static libmemcached.wrapper.function.Analyze.memcached_analyze_free;
import libmemcached.analyze.memcached_analysis_st;

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
    
    protected MemcachedAnalyze(memcached_analysis_st analysis_st) {
        this.analysis_st = analysis_st;
    }
    
}
