package com.cmcc.wxanswer.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Hex;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
/**
 * 
 * @author peterYang 2016/7/5
 *
 */
public class Md5 {
	public static String md5Hex(String str) {
	    MessageDigest md5 = null;
	    try {
	        md5 = MessageDigest.getInstance("MD5");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    byte[] datas = new byte[0];
	    try {
	        datas = str.getBytes("UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    byte[] bs = md5.digest(datas);
	    String result = new String(new Hex().encode(bs));
	    return result;
	}
	public static String generateSign(String data) throws UnsupportedEncodingException {
	    String sign = md5Hex(URLEncoder.encode(data));
	    return sign;
	}
	
	public static <K,V> MultiValueMap<K,V> getArgs(String url,String method,String type,MultiValueMap<K,V> map) throws UnsupportedEncodingException{
		//访问接口路径
		String serviceUrl = AppPropertiesUtil.getValue(url);
		//访问令牌
		String apikey=AppPropertiesUtil.getValue("apikey");
		//密钥
		String secretkey = AppPropertiesUtil.getValue("secretkey");
		long timestamp = System.currentTimeMillis();
		//最终路径
		StringBuilder buff = new StringBuilder();
		//获取sign
		StringBuilder builder = new StringBuilder();
		Map treeMap = new TreeMap();
		MultiValueMap<String, Object>  mult = new LinkedMultiValueMap<>();
		
		buff.append(serviceUrl);
		buff.append(method);
		buff.append("?");
		if("post".equals(type)){
			builder.append(serviceUrl.replaceAll("^http", "POSThttp"));
		}
		if("get".equals(type)){
			builder.append(serviceUrl.replaceAll("^http", "GEThttp"));
		}
		builder.append(method);
		treeMap.put("apikey", apikey);
		treeMap.put("timestamp", timestamp);
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry entry = (Entry) iter.next();
			String key = (String) entry.getKey();
			String value = entry.getValue().toString().replace("[", "").replace("]", "");
			treeMap.put(key, value);
		}
		Iterator it = treeMap.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			buff.append(entry.getKey()+"=");
			buff.append(entry.getValue()+"&");
			builder.append(entry.getKey()+"=");
			builder.append(entry.getValue());
			mult.add(entry.getKey()+"", entry.getValue()+"");
		}
		buff.append("apikey="+apikey);
		buff.append("&timestamp="+timestamp);
		builder.append(secretkey);
		String sign = md5Hex(URLEncoder.encode(builder.toString(), "UTF-8"));
		buff.append("&sign="+sign);
		mult.add("sign", sign+"");
		return (MultiValueMap<K, V>) mult;
	}
	
	public static void main(String[] args) {
		try {
			String result = md5Hex("waJi5ODnqkWfTOTOn0ZC+A==$app_key7afe247f0379b864$methodxk.school.list$timestamp2016-01-01 24:00:00pagedindex1pagedsize20provinceid2waJi5ODnqkWfTOTOn0ZC+A==");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
