package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class FlushBuffers extends Function {
    public static ReturnType memcached_flush_buffers(memcached_st mem){
        final int rc = getMemcached().memcached_flush_buffers(mem);
        return ReturnType.get(rc);
    }
}
