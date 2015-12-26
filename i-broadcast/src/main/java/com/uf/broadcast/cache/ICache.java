package com.uf.broadcast.cache;

public interface ICache {
    public Boolean store(String key ,Object value);
    public <T> T getObjectByKey(String key,Class<T> objectType);
}
