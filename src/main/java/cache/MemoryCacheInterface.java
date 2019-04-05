package cache;
/*
*
*/
public interface MemoryCacheInterface<Key,Value> {
    public Value get(Key key);
    public void put(Key key, Value value);
    public Value remove(Key key);
    public void clear();
    public Integer getSize();
    public Boolean containsKey(Key key);
}
