package cache;

public interface FileCacheInterface<Key,Value> {
    public Value get(Key key);
    public void put(Key key, Value value);
    public void remove(Key key);
    public void clear();
    public Integer getSize();
    public Boolean containsKey(Key key);
}
