package libmemcached.wrapper.function;

import libmemcached.types.memcached_server_list_st;

public class Parse extends Function {
    
    public static memcached_server_list_st memcached_servers_parse(String server_strings){
        return getMemcached().memcached_servers_parse(server_strings);
    }

}
