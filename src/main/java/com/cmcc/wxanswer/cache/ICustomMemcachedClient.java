package com.cmcc.wxanswer.cache;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * Created by sbyang on 2016/7/5
 */
public interface ICustomMemcachedClient {

    public MemcachedClient getMemcachedClient();

    public void init();

    public Object get(String key);

    public Future<Boolean> add(String key, int exp, Object o);

    public Future<Boolean> replace(String key, int exp, Object o);

    public Future<Boolean> set(String key, int exp, Object o);

    public Future<Boolean> delete(String key);

    public Map<String, Object> getBulk(Collection<String> keys);

    public Map<String, Object> getBulk(String... keys);

    public Object getAndTouch(String key, int exp);

    public <T> OperationFuture<Boolean> touch(String key, int exp);
}