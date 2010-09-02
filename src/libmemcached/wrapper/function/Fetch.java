package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_execute_function;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.Pointer;

public class Fetch extends Function {

    public static ReturnType memcached_fetch_execute(memcached_st ptr, memcached_execute_function callback, Pointer context, int number_of_callbacks){
        final int rc = getMemcached().memcached_fetch_execute(ptr, callback, context, number_of_callbacks);
        return ReturnType.get(rc);
    }
}
