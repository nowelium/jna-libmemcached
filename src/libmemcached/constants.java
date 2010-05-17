package libmemcached;

public interface constants {
    
    public static final int MEMCACHED_DEFAULT_PORT = 11211;
    
    public static final int MEMCACHED_MAX_KEY = 251; /* We add one to have it null terminated */
    
    public static final int MEMCACHED_MAX_BUFFER = 8196;
    
    public static final int MEMCACHED_MAX_HOST_SORT_LENGTH = 86; /* Used for Ketama */
    
    public static final int MEMCACHED_POINTS_PER_SERVER = 100;
    
    public static final int MEMCACHED_POINTS_PER_SERVER_KETAMA = 160;
    
    public static final int MEMCACHED_CONTINUUM_SIZE = MEMCACHED_POINTS_PER_SERVER * 100; /* This would then set max hosts to 100 */
    
    public static final int MEMCACHED_STRIDE = 4;
    
    public static final int MEMCACHED_DEFAULT_TIMEOUT = 1000;
    
    public static final int MEMCACHED_CONTINUUM_ADDITION = 10; /* How many extra slots we should build for in the continuum */
    
    public static final int MEMCACHED_PREFIX_KEY_MAX_SIZE = 128;
    
    public static final long MEMCACHED_EXPIRATION_NOT_ADD = 0xffffffffL;
    
    public static final int MEMCACHED_VERSION_STRING_LENGTH = 24;
    
    public static interface memcached_return {
        public static final int MEMCACHED_SUCCESS = 0;
        public static final int MEMCACHED_FAILURE = 1;
        public static final int MEMCACHED_HOST_LOOKUP_FAILURE = 2;
        public static final int MEMCACHED_CONNECTION_FAILURE = 3;
        public static final int MEMCACHED_CONNECTION_BIND_FAILURE = 4;
        public static final int MEMCACHED_WRITE_FAILURE = 5;
        public static final int MEMCACHED_READ_FAILURE = 6;
        public static final int MEMCACHED_UNKNOWN_READ_FAILURE = 7;
        public static final int MEMCACHED_PROTOCOL_ERROR = 8;
        public static final int MEMCACHED_CLIENT_ERROR = 9;
        public static final int MEMCACHED_SERVER_ERROR = 10;
        public static final int MEMCACHED_CONNECTION_SOCKET_CREATE_FAILURE = 11;
        public static final int MEMCACHED_DATA_EXISTS = 12;
        public static final int MEMCACHED_DATA_DOES_NOT_EXIST = 13;
        public static final int MEMCACHED_NOTSTORED = 14;
        public static final int MEMCACHED_STORED = 15;
        public static final int MEMCACHED_NOTFOUND = 16;
        public static final int MEMCACHED_MEMORY_ALLOCATION_FAILURE = 17;
        public static final int MEMCACHED_PARTIAL_READ = 18;
        public static final int MEMCACHED_SOME_ERRORS = 19;
        public static final int MEMCACHED_NO_SERVERS = 20;
        public static final int MEMCACHED_END = 21;
        public static final int MEMCACHED_DELETED = 22;
        public static final int MEMCACHED_VALUE = 23;
        public static final int MEMCACHED_STAT = 24;
        public static final int MEMCACHED_ITEM = 25;
        public static final int MEMCACHED_ERRNO = 26;
        public static final int MEMCACHED_FAIL_UNIX_SOCKET = 27;
        public static final int MEMCACHED_NOT_SUPPORTED = 28;
        public static final int MEMCACHED_NO_KEY_PROVIDED = 29; /* Deprecated. Use MEMCACHED_BAD_KEY_PROVIDED! */
        public static final int MEMCACHED_FETCH_NOTFINISHED = 30;
        public static final int MEMCACHED_TIMEOUT = 31;
        public static final int MEMCACHED_BUFFERED = 32;
        public static final int MEMCACHED_BAD_KEY_PROVIDED = 33;
        public static final int MEMCACHED_INVALID_HOST_PROTOCOL = 34;
        public static final int MEMCACHED_SERVER_MARKED_DEAD = 35;
        public static final int MEMCACHED_UNKNOWN_STAT_KEY = 36;
        public static final int MEMCACHED_E2BIG = 37;
        public static final int MEMCACHED_INVALID_ARGUMENTS = 38;
        public static final int MEMCACHED_KEY_TOO_BIG = 39;
        public static final int MEMCACHED_AUTH_PROBLEM = 40;
        public static final int MEMCACHED_AUTH_FAILURE = 41;
        public static final int MEMCACHED_AUTH_CONTINUE = 42;
        public static final int MEMCACHED_MAXIMUM_RETURN = 43; /* Always add new error code before */
    }
    
    public static interface memcached_server_distribution_t {
        public static final int MEMCACHED_DISTRIBUTION_MODULA = 0;
        public static final int MEMCACHED_DISTRIBUTION_CONSISTENT = 1;
        public static final int MEMCACHED_DISTRIBUTION_CONSISTENT_KETAMA = 2;
        public static final int MEMCACHED_DISTRIBUTION_RANDOM = 3;
        public static final int MEMCACHED_DISTRIBUTION_CONSISTENT_KETAMA_SPY = 4;
        public static final int MEMCACHED_DISTRIBUTION_CONSISTENT_MAX = 5;
    }
    
    public static interface memcached_behavior {
        public static final int MEMCACHED_BEHAVIOR_NO_BLOCK = 0;
        public static final int MEMCACHED_BEHAVIOR_TCP_NODELAY = 1;
        public static final int MEMCACHED_BEHAVIOR_HASH = 2;
        public static final int MEMCACHED_BEHAVIOR_KETAMA = 3;
        public static final int MEMCACHED_BEHAVIOR_SOCKET_SEND_SIZE = 4;
        public static final int MEMCACHED_BEHAVIOR_SOCKET_RECV_SIZE = 5;
        public static final int MEMCACHED_BEHAVIOR_CACHE_LOOKUPS = 6;
        public static final int MEMCACHED_BEHAVIOR_SUPPORT_CAS = 7;
        public static final int MEMCACHED_BEHAVIOR_POLL_TIMEOUT = 8;
        public static final int MEMCACHED_BEHAVIOR_DISTRIBUTION = 9;
        public static final int MEMCACHED_BEHAVIOR_BUFFER_REQUESTS = 10;
        public static final int MEMCACHED_BEHAVIOR_USER_DATA = 11;
        public static final int MEMCACHED_BEHAVIOR_SORT_HOSTS = 12;
        public static final int MEMCACHED_BEHAVIOR_VERIFY_KEY = 13;
        public static final int MEMCACHED_BEHAVIOR_CONNECT_TIMEOUT = 14;
        public static final int MEMCACHED_BEHAVIOR_RETRY_TIMEOUT = 15;
        public static final int MEMCACHED_BEHAVIOR_KETAMA_WEIGHTED = 16;
        public static final int MEMCACHED_BEHAVIOR_KETAMA_HASH = 17;
        public static final int MEMCACHED_BEHAVIOR_BINARY_PROTOCOL = 18;
        public static final int MEMCACHED_BEHAVIOR_SND_TIMEOUT = 19;
        public static final int MEMCACHED_BEHAVIOR_RCV_TIMEOUT = 20;
        public static final int MEMCACHED_BEHAVIOR_SERVER_FAILURE_LIMIT = 21;
        public static final int MEMCACHED_BEHAVIOR_IO_MSG_WATERMARK = 22;
        public static final int MEMCACHED_BEHAVIOR_IO_BYTES_WATERMARK = 23;
        public static final int MEMCACHED_BEHAVIOR_IO_KEY_PREFETCH = 24;
        public static final int MEMCACHED_BEHAVIOR_HASH_WITH_PREFIX_KEY = 25;
        public static final int MEMCACHED_BEHAVIOR_NOREPLY = 26;
        public static final int MEMCACHED_BEHAVIOR_USE_UDP = 27;
        public static final int MEMCACHED_BEHAVIOR_AUTO_EJECT_HOSTS = 28;
        public static final int MEMCACHED_BEHAVIOR_NUMBER_OF_REPLICAS = 29;
        public static final int MEMCACHED_BEHAVIOR_RANDOMIZE_REPLICA_READ = 30;
        public static final int MEMCACHED_BEHAVIOR_CORK = 31;
        public static final int MEMCACHED_BEHAVIOR_TCP_KEEPALIVE = 32;
        public static final int MEMCACHED_BEHAVIOR_TCP_KEEPIDLE = 33;
        public static final int MEMCACHED_BEHAVIOR_MAX = 34;
    }
    
    public static interface memcached_callback_t {
        public static final int MEMCACHED_CALLBACK_PREFIX_KEY = 0;
        public static final int MEMCACHED_CALLBACK_USER_DATA = 1;
        public static final int MEMCACHED_CALLBACK_CLEANUP_FUNCTION = 2;
        public static final int MEMCACHED_CALLBACK_CLONE_FUNCTION = 3;
        // {{{ if enabled deprecated
        public static final int MEMCACHED_CALLBACK_MALLOC_FUNCTION = 4;
        public static final int MEMCACHED_CALLBACK_REALLOC_FUNCTION = 5;
        public static final int MEMCACHED_CALLBACK_FREE_FUNCTION = 6;
        // }}} if enabled deprecated
        public static final int MEMCACHED_CALLBACK_GET_FAILURE = 7;
        public static final int MEMCACHED_CALLBACK_DELETE_TRIGGER = 8;
        public static final int MEMCACHED_CALLBACK_MAX = 9;
    }
    
    public static interface memcached_hash {
        public static final int MEMCACHED_HASH_DEFAULT = 0;
        public static final int MEMCACHED_HASH_MD5 = 1;
        public static final int MEMCACHED_HASH_CRC = 2;
        public static final int MEMCACHED_HASH_FNV1_64 = 3;
        public static final int MEMCACHED_HASH_FNV1A_64 = 4;
        public static final int MEMCACHED_HASH_FNV1_32 = 5;
        public static final int MEMCACHED_HASH_FNV1A_32 = 6;
        public static final int MEMCACHED_HASH_HSIEH = 7;
        public static final int MEMCACHED_HASH_MURMUR = 8;
        public static final int MEMCACHED_HASH_JENKINS = 9;
        public static final int MEMCACHED_HASH_CUSTOM = 10;
        public static final int MEMCACHED_HASH_MAX = 11;
    }
    
    public static interface memcached_connection {
        public static final int MEMCACHED_CONNECTION_UNKNOWN = 0;
        public static final int MEMCACHED_CONNECTION_TCP = 1;
        public static final int MEMCACHED_CONNECTION_UDP = 2;
        public static final int MEMCACHED_CONNECTION_UNIX_SOCKET = 3;
        public static final int MEMCACHED_CONNECTION_MAX = 4;
    }
    
}
