package libmemcached.wrapper;

import static libmemcached.wrapper.function.Analyze.memcached_analyze_free;

import java.util.concurrent.atomic.AtomicBoolean;

import libmemcached.analyze.memcached_analysis_st;

public class MemcachedAnalyze {
    
    protected final AtomicBoolean free = new AtomicBoolean(false);

    protected final memcached_analysis_st analysis_st;
    
//    @SuppressWarnings("unused")
//    private final Object finalizer = new Object(){
//        @Override
//        protected void finalize() throws Throwable {
//            memcached_analyze_free(analysis_st);
//            super.finalize();
//        }
//    };
    
    protected MemcachedAnalyze(memcached_analysis_st analysis_st) {
        this.analysis_st = analysis_st;
    }
    
    public void free(){
        if(free.getAndSet(true)){
            return ;
        }
        memcached_analyze_free(analysis_st);
    }
    
}
