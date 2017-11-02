package com.cmcc.wxanswer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <b>类名称：</b>JsonUtils
 * <b>类描述：</b>JSON工具类
 * <b>创建人：</b>ChenSong
 * <b>修改人：</b>ChenSong
 * <b>修改时间：</b>2016-8-2 上午11:22:14
 * <b>修改备注：</b>
 * @version v1.0<br/>
 */
public class JsonUtils {
    private JsonUtils() {
    }

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final JsonFactory factory = mapper.getFactory();

    static {
        // to enable standard indentation ("pretty-printing"):
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    /**
     * 对象转json     *
     * @param o
     * @return
     */
    public static String Bean2Json(Object o) {
        String json = null;
        try {
            json = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json转对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T Json2Bean(String json, Class<T> tClass) {
        T t = null;
        try {
            t = mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * json转对象（复杂类型）
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T Json2Bean(String json, TypeReference<T> typeReference) {
        T t = null;
        try {
            t = mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 对象通过json转化为另一个对象
     * @param o
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T Bean2Bean(Object o, Class<T> tClass) {
        return Json2Bean(Bean2Json(o), tClass);
    }

    /**
     * 对象通过json转化为另一个复杂对象
     * @param o
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T Bean2Bean(Object o, TypeReference<T> typeReference) {
        return Json2Bean(Bean2Json(o), typeReference);
    }
    
    
    /**
	 * @Title: jsonArrayToListByTarg
	 * @Description: 根据json中targ参数，json对象数组转化为java集合(三期门户端对接微服务专用方法)
	 * @param json json字符串
	 * @param targ 封装多个对象信息的标签名称，例如"body"
	 * @param tClass 目标对象
	 * @return  
	 * @throws
	 * @author ChenSong
	 * @moder ChenSong
	 * @modtime 2016-6-3 下午1:55:00
	 */
    public static <T> List<T> jsonArrayToListByTarg(String json,String targ, Class<T> tClass){
		if(json==null || "".equals(json))return null;
		List<T> list = new ArrayList<T>();
		Map mArr=JSON.parseObject(json,Map.class);//json-->map
		Object objs = mArr.get("result");
		Integer flag = null;
		if(objs instanceof Integer){
			flag = (Integer)objs;//得到成功标志
		}else{
			flag = Integer.parseInt((String)objs);
		}
		if(flag==0){
			JSONArray bodyList = (JSONArray) mArr.get(targ);//根据json的某个参数targ得到  得到json数组
			for (int i = 0; i < bodyList.size(); i++) {
				JSONObject jsonObject =(JSONObject)bodyList.get(i);//遍历数组得到json对象
				list.add(jsonObject.toJavaObject(tClass));//把json对象转成java对象然后添加到list集合中。
			}
			return (List<T>) list;
		}else{
			return null;
		}
	}
    
    public static <T> List<T> jsonArrayToList(JSONArray jsonArray, Class<T> tClass){
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject =(JSONObject)jsonArray.get(i);//遍历数组得到json对象
			list.add(jsonObject.toJavaObject(tClass));//把json对象转成java对象然后添加到list集合中。
		}
		return (List<T>) list;
	}
	
	/**
	 * @Title: jsonObjectToObjectByTarg
	 * @Description: 根据json中targ参数，json对象转化为java对象(三期门户端对接微服务专用方法)
	 * @param json json字符串
	 * @param targ 封装单个对象信息的标签名称，例如"body"
	 * @param tClass 目标对象
	 * @return  
	 * @throws
	 * @author ChenSong
	 * @moder ChenSong
	 * @modtime 2016-6-3 下午2:04:40
	 */
	public static <T> T jsonObjectToObjectByTarg(String json,String targ, Class<T> tClass){
		if(json==null || "".equals(json))return null;
		Map mObj=JSON.parseObject(json,Map.class);//json-->map
		Object objs = mObj.get("result");
		Integer flag = null;
		if(objs instanceof Integer){
			flag = (Integer)objs;//得到成功标志
		}else{
			flag = Integer.parseInt((String)objs);
		}
		if(flag==0){
			JSONObject body = (JSONObject) mObj.get(targ);//根据json的某个参数targ得到 json对象
			T t = body.toJavaObject(tClass);//把json对象转化为java对象返回。
			return t;
		}else{
			return null;
		}
		
	}
}
