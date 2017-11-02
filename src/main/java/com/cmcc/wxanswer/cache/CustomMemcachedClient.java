package com.cmcc.wxanswer.cache;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.OperationTimeoutException;
import net.spy.memcached.internal.OperationFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by sbyang on 2016/7/5
 */
public abstract class CustomMemcachedClient implements ICustomMemcachedClient{

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    private MemcachedClient memcachedClient;

    public MemcachedClient getMemcachedClient() {
        return this.memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient){
        this.memcachedClient=memcachedClient;
    }

    public void init() {
        logger.debug("开始刷新缓存......");
        try {
            this.memcachedClient.flush();
        } catch (Exception e) {
            logger.debug("缓存刷新异常结束", e);
            logger.error("缓存刷新异常结束", e);
            return;
        }
        logger.debug("缓存刷新正常结束");
    }

    public Object get(String key) {
        logger.debug("进入GET方法");
        try {
            return this.memcachedClient.get(key);
        } catch (OperationTimeoutException operationTimeoutException) {
            logger.debug("GET方法异常,返回NULL", operationTimeoutException);
            logger.error("GET方法异常,返回NULL", operationTimeoutException);
            return null;
        }
    }

    public Future<Boolean> add(String key, int exp, Object o) {
        logger.debug("进入ADD方法");
        try {
            return this.memcachedClient.add(key, exp, o);
        } catch (Exception e) {
            logger.debug("ADD方法异常,返回NULL", e);
            logger.error("ADD方法异常,返回NULL", e);
            return null;
        }
    }

    public Future<Boolean> replace(String key, int exp, Object o) {
        logger.debug("进入REPLACE方法");
        try {
            return this.memcachedClient.replace(key, exp, o);
        } catch (Exception e) {
            logger.debug("REPLACE方法异常,返回NULL", e);
            logger.error("REPLACE方法异常,返回NULL", e);
            return null;
        }
    }

    public Future<Boolean> set(String key, int exp, Object o) {
        logger.debug("进入SET方法");
        try {
            return this.memcachedClient.set(key, exp, o);
        } catch (Exception e) {
            logger.debug("SET方法异常,返回NULL", e);
            logger.error("SET方法异常,返回NULL", e);
            return null;
        }
    }

    public Future<Boolean> delete(String key) {
        logger.debug("进入DELETE方法");
        try {
            return this.memcachedClient.delete(key);
        } catch (Exception e) {
            logger.debug("DELETE方法异常,返回NULL", e);
            logger.error("DELETE方法异常,返回NULL", e);
            return null;
        }
    }

    public Map<String, Object> getBulk(Collection<String> keys) {
        logger.debug("进入GETBULK方法");
        try {
            return this.memcachedClient.getBulk(keys);
        } catch (Exception e) {
            logger.debug("GETBULK方法异常,返回NULL", e);
            logger.error("GETBULK方法异常,返回NULL", e);
            return null;
        }
    }

    public Map<String, Object> getBulk(String... keys) {
        logger.debug("进入GETBULK方法");
        try {
            return this.memcachedClient.getBulk(keys);
        } catch (Exception e) {
            logger.debug("GETBULK方法异常,返回NULL", e);
            logger.error("GETBULK方法异常,返回NULL", e);
            return null;
        }
    }

    public Object getAndTouch(String key,int exp){
        logger.debug("进入GETANDTOUCH方法");
        try {
            CASValue<Object> casValue= this.memcachedClient.getAndTouch(key,exp);
            return casValue==null?null:casValue.getValue();
        } catch (Exception e) {
            logger.debug("GETANDTOUCH方法异常,返回NULL", e);
            logger.error("GETANDTOUCH方法异常,返回NULL", e);
            return null;
        }
    }

    public <T> OperationFuture<Boolean> touch(String key,int exp){
        logger.debug("进入TOUCH方法");
        try {
            return this.memcachedClient.touch(key,exp);
        } catch (Exception e) {
            logger.debug("TOUCH方法异常,返回NULL", e);
            logger.error("TOUCH方法异常,返回NULL", e);
            return null;
        }
    }
}
