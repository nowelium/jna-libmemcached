package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_dump_function;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.Pointer;

public class Dump extends Function {
    public static ReturnType memcached_dump(memcached_st ptr, memcached_dump_function function, Pointer context, int number_of_callbacks){
        final int rc = getMemcached().memcached_dump(ptr, function, context, number_of_callbacks);
        return ReturnType.get(rc);
    }
}
