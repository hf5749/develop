package com.cmcc.wxanswer.cache;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by sbyang on 2016/7/5
 */
@Component
public class CustomUSERMemcachedClient extends CustomMemcachedClient implements ICustomMemcachedClient {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CustomUSERMemcachedClient(@Qualifier("userMemcachedClient") MemcachedClient userMemcachedClient) {
        try {
            logger.debug("初始化用户缓存对象......");
            this.setMemcachedClient(userMemcachedClient);
        } catch (Exception e) {
            logger.error("初始化用户缓存对象失败!", e);
            logger.debug("初始化用户存对象失败!", e);
        }
    }
}