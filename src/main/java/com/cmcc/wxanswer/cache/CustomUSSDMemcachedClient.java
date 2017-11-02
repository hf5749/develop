package com.cmcc.wxanswer.cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.spy.memcached.MemcachedClient;

/**
 * Created by sbyang on 2016/7/5
 */
@Component
public class CustomUSSDMemcachedClient extends CustomMemcachedClient implements ICustomMemcachedClient {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public CustomUSSDMemcachedClient(@Qualifier("ussdMemcachedClient") MemcachedClient ussdMemcachedClient) {
        try {
            logger.debug("初始化业务数据缓存对象......");
            this.setMemcachedClient(ussdMemcachedClient);
//            this.init();
        } catch (Exception e) {
            logger.error("初始化业务数据缓存对象失败!", e);
            logger.debug("初始化业务数据缓存对象失败!", e);
        }
    }
}