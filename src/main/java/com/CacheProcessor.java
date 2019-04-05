package com;

import cache.FileCache;
import cache.MemoryCache;
import manager.CacheManagerFactory;
import manager.CacheManagerInterface;
import manager.CacheType;

import java.io.Serializable;

public class CacheProcessor<Key,Value extends Serializable> implements CacheProcessorInterface<Key,Value>  {
    private final MemoryCache<Key,Value> memoryCache;
    private final FileCache<Key,Value> fileCache;
    private final CacheManagerInterface memoryCacheManager;
    private final CacheManagerInterface fileCacheManager;

    @Override
    public MemoryCache<Key, Value> getMemoryCache() {
        return memoryCache;
    }
    @Override
    public FileCache<Key, Value> getFileCache() {
        return fileCache;
    }

    public CacheProcessor(Integer memoryCachCapacity,Integer fileCacheCapacity,Integer cacheType) {
        memoryCache=new MemoryCache<>(memoryCachCapacity);
        fileCache=new FileCache(fileCacheCapacity);
        memoryCacheManager=CacheManagerFactory.createCacheManager(CacheType.fromInt(cacheType));
        fileCacheManager=CacheManagerFactory.createCacheManager(CacheType.fromInt(cacheType));
    }


    @Override
    public synchronized void put(Key key,Value value){
        //if((memoryCache.isNotFull())&&(!memoryCache.containsKey(key))){
        if(memoryCache.isNotFull()){
            //first will fill memory cache
            memoryCache.put(key,value);
            if (!memoryCacheManager.containsKey(key)){
                memoryCacheManager.put(key);
            }
        }else if (fileCache.isNotFull()){
            //second will fill file cache
            fileCache.put(key,value);
            if (!fileCacheManager.containsKey(key)){
                fileCacheManager.put(key);
            }
        }else{
            //both caches are full
            //we MUST! check if key is already exist in file cache or memory cache it helps to avoid
            //situation when  memory cache conatains key with old value, file cache contains the same key but with new value
            if (memoryCache.containsKey(key)){
                memoryCache.put(key,value);
                memoryCacheManager.put(key);
                return;
            }
            if (fileCache.containsKey(key)){
                fileCache.put(key,value);
                fileCacheManager.put(key);
                return;
            }
            //get and remove eldest/newest object according cache configuration from memory cache
            //remove eldest/newest object according cache configuration  from file cache
            //put object from memory cache to file cache
            //put new object to mem cach

             Key oldMemKey= (Key) memoryCacheManager.getAndRemove();
             Value oldMemValue = memoryCache.remove(oldMemKey);
             memoryCache.put(key,value);
             memoryCacheManager.put(key);

            Key oldFileKey= (Key) fileCacheManager.getAndRemove();
            fileCache.remove(oldFileKey);
            fileCache.put(oldMemKey,oldMemValue);
            fileCacheManager.put(oldMemKey);
        }
    }
    @Override
    public synchronized Value get(Key key){
        if (memoryCache.containsKey(key)){
            memoryCacheManager.put(key);
            return memoryCache.get(key);
        }else if (fileCache.containsKey(key)){
            fileCacheManager.put(key);
            return fileCache.get(key);
        }else{
            return null;
        }
    }
    @Override
    public synchronized void clear(){
        memoryCache.clear();
        memoryCacheManager.clear();
        fileCache.clear();
        fileCacheManager.clear();
    }
    @Override
    public synchronized Integer getSize(){
        return memoryCache.getSize()+fileCache.getSize();
    }

}
