package com.uf.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	private static CacheManager cacheManager=CacheManager.getInstance();
	private static String cacheName="testCache";
	static {
		Cache cache = new Cache(cacheName, 0, true, false, 60*10, 60*2);
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
}
