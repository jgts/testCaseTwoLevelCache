package com;

import manager.CacheType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheProcessorTest {
    private final static Integer MEM_CACHE_CAPACITY=5;
    private final static Integer FILE_CACHE_CAPACITY=5;
    CacheProcessor cacheProcessor;


    @Before
    public void initTest(){
        cacheProcessor=new CacheProcessor(MEM_CACHE_CAPACITY,FILE_CACHE_CAPACITY,CacheType.LeastRecentlyUsed.getValue());
    }

    @Test
    public void put() {
        for(int i=0;i<20;i++){
            cacheProcessor.put(i,i+1);
        }
        assertEquals(MEM_CACHE_CAPACITY+FILE_CACHE_CAPACITY,cacheProcessor.getSize()*1L);
        assertEquals(FILE_CACHE_CAPACITY,cacheProcessor.getFileCache().getSize());
        assertEquals(MEM_CACHE_CAPACITY,cacheProcessor.getMemoryCache().getSize());
    }

    @Test
    public void clear() {
        cacheProcessor.put(1,2);
        assertEquals(1, cacheProcessor.getSize()*1L);
        cacheProcessor.clear();
        assertEquals(0L,cacheProcessor.getSize()*1L);
    }

}