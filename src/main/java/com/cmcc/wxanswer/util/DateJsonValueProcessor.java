package com.cmcc.wxanswer.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

/**
 * TODO
 * @author sbyang
 * @data 2016/7/5
 * @version 1.0
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
	
	private static final Logger log = Logger.getLogger(DateJsonValueProcessor.class);

	/** * 默认的日期转换格式. */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	
    /** * 日期时间转换格式. */ 
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** * 日期转换器. */
    private DateFormat dateFormat;

    /**
     * 构造方法.
     *
     * @param datePattern 日期格式
     */
    public DateJsonValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
            log.info(ex);
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    }

    /**
     * 转换数组？.
     *
     * @param value Object
     * @param jsonConfig 配置
     * @return Object
     */
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    /**
     * 转换对象.
     *
     * @param key String
     * @param value Object
     * @param jsonConfig 配置
     * @return Object
     */
    public Object processObjectValue(String key, Object value,
        JsonConfig jsonConfig) {
        return process(value);
    }

    /**
     * 格式化日期.
     *
     * @param value Object
     * @return Object
     */
    private Object process(Object value) {
        try {
            return dateFormat.format((Date) value);
        } catch (Exception ex) {
            return null;
        }
    }
}

