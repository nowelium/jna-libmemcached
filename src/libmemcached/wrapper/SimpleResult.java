package libmemcached.wrapper;

public class SimpleResult {
    
    protected String key;
    protected String value;
    protected long length;
    protected int flags;
    public SimpleResult(String key, String value, long length, int flags){
        this.key = key;
        this.value = value;
        this.length = length;
        this.flags = flags;
    }
    public String getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    public long getLength(){
        return length;
    }
    public int getFlags(){
        return flags;
    }

}
