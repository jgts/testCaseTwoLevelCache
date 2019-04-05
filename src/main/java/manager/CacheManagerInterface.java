package manager;

public interface CacheManagerInterface<Key> {
    public Key getAndRemove();
    public Long get(Key key);
    public void put(Key key);
    public Boolean containsKey(Key key);
    public void clear();
}
