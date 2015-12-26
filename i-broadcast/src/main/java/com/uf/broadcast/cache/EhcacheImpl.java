package com.uf.broadcast.cache;

import com.uf.broadcast.entity.Publisher;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhcacheImpl implements ICache{
  private Cache session;
  public EhcacheImpl(){
    CacheManager singletonManager = CacheManager.create();
    session = new Cache("session", 1000000, true, false, 3600,3600);
    singletonManager.addCache(session);
  }
  @Override
  public Boolean store(String key, Object value) {
    Element el=new Element(key,value);  
    session.put(el);
    return true;
  }
  public <T> T getObjectByKey(String key,Class<T> objectType){
    Element element=session.get(key);
    if(element!=null&&objectType.isInstance(element.getObjectValue())){
      return (T)element.getObjectValue();
    }
    return null;
  }
}
