package com;

import manager.CacheType;

import java.util.Objects;
import java.util.Scanner;

public class CacheStrategy {
    public CacheStrategy() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter firtst level cache size:");
        Integer firstLevelCacheSize=in.nextInt();
        if ((firstLevelCacheSize==null)||(firstLevelCacheSize<=0)){
            throw new  RuntimeException("Firtst level cache size is reuired");
        }
        System.out.print("Enter second level cache size:");
        Integer secondLevelCacheSize=in.nextInt();
        if ((secondLevelCacheSize==null)||(secondLevelCacheSize<=0)){
            throw new  RuntimeException("Second level cache size is reuired");
        }
        System.out.print("Enter cache type:0-LRU,1-MRU,2-LFU:");
        Integer cacheStrategy=in.nextInt();
        if (cacheStrategy==null){
            throw new  RuntimeException("Cache type is reuired");
        }
        if ((cacheStrategy<0)||(cacheStrategy>2)){
            throw new  RuntimeException("Cache type is miss match");
        }

        CacheProcessor process=new CacheProcessor(firstLevelCacheSize,secondLevelCacheSize,cacheStrategy);
        System.out.println("Heat up cache");
        for (int i=0;i<firstLevelCacheSize+secondLevelCacheSize+1;i++){
            Integer v=Integer.valueOf((int) (Math.random()*100));
            System.out.println("put key: "+i+" value: "+v);
            process.put(i,v);
            if (Objects.equals(2,cacheStrategy)){
                if (v%2==0){
                    int z = (int)(Math.random() * 10);
                    for(int j=0;j<z;j++){
                        //increase frequrency
                        process.get(i);
                    }
                }
            }
        }
        System.out.println("Reading from cache");
        for (int i=0;i<firstLevelCacheSize+secondLevelCacheSize+1;i++){
            Object object= process.get(i);
            String msg=null;
            if (object==null){
                msg=" evicted by "+CacheType.fromInt(cacheStrategy);
            }else {
                msg=String.valueOf(object);
            }
            System.out.println("get key: "+i+" value: "+msg);
        }


    }
}
