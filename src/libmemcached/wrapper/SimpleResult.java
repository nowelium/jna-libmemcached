package libmemcached.wrapper;

public class SimpleResult {
    
    protected final String key;
    
    protected final String value;
    
    protected final long length;
    
    protected final int flags;
    
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
    
    @Override
    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        buf.append("key:").append(key).append(",");
        buf.append("value:").append(value).append(",");
        buf.append("length:").append(length).append(",");
        buf.append("flags:").append(flags);
        buf.append("}");
        return buf.toString();
    }

}
