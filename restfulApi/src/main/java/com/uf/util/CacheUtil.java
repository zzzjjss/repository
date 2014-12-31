package com.uf.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;

public class CacheUtil {
	private static CacheManager cacheManager;
	private static String cacheName="testCache";
	static {
		Configuration cacheManagerConfig = new Configuration()
	    .diskStore(new DiskStoreConfiguration().path("c:/jason/data"));
		cacheManager=new CacheManager(cacheManagerConfig);
		CacheConfiguration config=new CacheConfiguration();
		config.persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP));
		config.name(cacheName);
		config.maxBytesLocalHeap(300, MemoryUnit.MEGABYTES);
		config.timeToLiveSeconds(60*10);
		config.timeToIdleSeconds(60*2);
		Cache cache = new Cache(config);
		cacheManager.addCache(cache);
		
	}
	public static  void saveObj(Object key,Object value){
		Cache cache=cacheManager.getCache(cacheName);
		Element element=new Element(key, value) ;
		cache.put(element);
	}
	public static Object getObj(Object key){
		Cache cache=cacheManager.getCache(cacheName);
		Element el=cache.get(key);
		if(el!=null){
			return el.getObjectValue();
		}
		return null;
	}
	public static void removeObj(Object key){
		Cache cache=cacheManager.getCache(cacheName);
		cache.remove(key);
	}
	public static void main(String[] args) {
		for(int i=0;i<10000;i++){
			System.out.println(CacheUtil.getObj(i));
		}
		
	}
}
