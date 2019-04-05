package com;

import manager.CacheType;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheStrategyTest {
    private final static Integer MEM_CACHE_CAPACITY=5;
    private final static Integer FILE_CACHE_CAPACITY=5;
    private final static String KEY="OverloadKey";
    private final static String VALUE="OverloadValue";



    CacheProcessor cacheProcessor;

    @Test
    public void LFUCache() {
        cacheProcessor=new CacheProcessor(MEM_CACHE_CAPACITY,FILE_CACHE_CAPACITY,CacheType.LeastFrequentlyUsed.getValue());
        loadCache();
        heatUpCache();
        overloadCache();
        assertNotNull(cacheProcessor.getMemoryCache().get(KEY));
        assertNotNull(cacheProcessor.getFileCache().get(0));



    }
    public void overloadCache(){
        cacheProcessor.put(KEY,VALUE);
    }

    public  void loadCache(){
        for (int i=0;i<MEM_CACHE_CAPACITY+FILE_CACHE_CAPACITY;i++){
            cacheProcessor.put(i,i+1);
        }
    }
    public void heatUpCache(){
        for (int i=0;i<MEM_CACHE_CAPACITY+FILE_CACHE_CAPACITY;i++){
            for (int j=0;j<i;j++){
                cacheProcessor.get(i);
            }
        }
    }

    @Test
    public void MRUCache() {
        cacheProcessor=new CacheProcessor(MEM_CACHE_CAPACITY,FILE_CACHE_CAPACITY,CacheType.MostRecentlyUsed.getValue());
        loadCache();
        heatUpCache();
        overloadCache();
        assertNotNull(cacheProcessor.getMemoryCache().get(KEY));
        assertNotNull(cacheProcessor.getFileCache().get(4));
    }

    @Test
    public void LRUCache() {
        cacheProcessor=new CacheProcessor(MEM_CACHE_CAPACITY,FILE_CACHE_CAPACITY,CacheType.LeastRecentlyUsed.getValue());
        loadCache();
        heatUpCache();
        overloadCache();
        assertNotNull(cacheProcessor.getMemoryCache().get(KEY));
        assertNotNull(cacheProcessor.getFileCache().get(0));
    }
}