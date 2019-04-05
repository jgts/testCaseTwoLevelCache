package com;

import cache.FileCache;
import cache.MemoryCache;

import java.io.Serializable;

public interface CacheProcessorInterface<Key,Value extends Serializable> {
    public void put(Key key,Value value);
    public Value get(Key key);
    public void clear();
    public Integer getSize();
    public MemoryCache<Key, Value> getMemoryCache();
    public FileCache<Key, Value> getFileCache();

}
