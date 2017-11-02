package com.cmcc.wxanswer.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * 调用微信公共方法
 * @author Administrator
 *
 */
public class WxUtil {
	
	static final Logger logger = LoggerFactory.getLogger(WxUtil.class);
	public static final String REQUEST_TYPE_POST = "post";
	public static final String REQUEST_TYPE_GET = "get";
	static String appid = AppPropertiesUtil.getValue("appid");
	static String appsecret = AppPropertiesUtil.getValue("appsecret");
	static String grant_type = AppPropertiesUtil.getValue("grant_type");
	static String access_token_url = AppPropertiesUtil.getValue("access_token_url");
	//判断有没有关注公众号地址
	static String wxgzhAddress = AppPropertiesUtil.getValue("wxgzhAddress");
	//获取微信code地址
	static String wxcodeUrl = AppPropertiesUtil.getValue("wxcode_url");
	/**
	 * 通过用户授权获取code
	 * @param 
	 * @param type
	 * @return
	 */
	public static Map<String, String> getCode(String type){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		String redirectUri = AppPropertiesUtil.getValue("redirect_uri");
		try {
			 redirectUri = URLEncoder.encode(redirectUri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.debug("回调地址转码错误redirectUri="+redirectUri);
		}
		map.add("appid", appid);
		map.add("redirect_uri", redirectUri);
		map.add("response_type", "code");
		map.add("scope", "snsapi_base");
		map.add("state", "state=1#wechat_redirect");
		logger.debug("开始调用微信-获取code："+ access_token_url + ":" + JSON.toJSONString(map));
		String exchange = "";
    	if(REQUEST_TYPE_POST.equalsIgnoreCase(type)){
    		exchange = new RestTemplate().postForObject(wxcodeUrl, map, String.class);
    	}else{
    		exchange = new RestTemplate().getForObject(wxcodeUrl, String.class);
    	}
    	Map<String, String> resultMap = JSON.parseObject(exchange, Map.class);
		logger.debug("调用微信-获取code接口：返回结果：" + exchange);
		return resultMap;
	}

	/**
	 * 通过code换取网页授权access_token调用地址
	 * @param code
	 * @param type
	 * @return
	 */
	public static Map<String, String> getWxUser(String code, String type){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("code", code);
		map.add("appid", appid);
		map.add("secret", appsecret);
		map.add("grant_type", grant_type);
		logger.debug("开始调用微信-通过code换取网页授权："+ access_token_url + ":" + JSON.toJSONString(map));
		String exchange = "";
    	if(REQUEST_TYPE_POST.equalsIgnoreCase(type)){
    		exchange = new RestTemplate().postForObject(access_token_url, map, String.class);
    	}else{
    		exchange = new RestTemplate().getForObject(access_token_url, String.class);
    	}
    	Map<String, String> resultMap = JSON.parseObject(exchange, Map.class);
		logger.debug("调用微信-通过code换取网页授权：返回结果：" + exchange);
		return resultMap;
	}
	
	/**
	 * 判断用户有没有关注公众号
	 * @param code
	 * @param type
	 * @return
	 */
	public static Map<String, String> getWxUser(String access_token, String openid, String type){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("access_token", access_token);
		map.add("openid", openid);
		map.add("lang", "zh_CN");
		logger.debug("开始调用微信-判断用户有没有关注公众号："+ wxgzhAddress + ":" + JSON.toJSONString(map));
		String exchange = "";
    	if(REQUEST_TYPE_POST.equalsIgnoreCase(type)){
    		exchange = new RestTemplate().postForObject(wxgzhAddress, map, String.class);
    	}else{
    		String address = wxgzhAddress + "?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
    		exchange = new RestTemplate().getForObject(address, String.class);
    	}
    	logger.debug("调用微信-判断用户有没有关注公众号：返回结果：" + exchange);
    	Map<String, String> resultMap = JSON.parseObject(exchange, Map.class);
    	return resultMap;
	}
	
	/**
	 * 获取用户有没有关注微信公众号
	 * @param access_token
	 * @param openid
	 * @param type
	 * @param countList
	 * @return
	 */
	public static boolean getWxUser(String access_token, String openid, String type, List<String> countList){
		if(countList.size()==4){
			return false;
		}
		countList.add("1");
		Map<String, String> resultMap = getWxUser(access_token, openid, type);
    	if(null != resultMap){
    		Object obj = resultMap.get("subscribe");
    		if(null != obj){
    			Integer b = Integer.parseInt(obj.toString());
    			if(b!=0){
    				return true;
    			}
    		}else{
    			Map<String, String> tokenMap = getAccessToken(REQUEST_TYPE_GET);
    			if(StringUtils.isNotBlank(tokenMap.get("access_token"))){
					String c = new Date().getTime() + "-:-" + tokenMap.get("access_token");
					FileUtil.createFile("accessToken", c);
					return getWxUser(tokenMap.get("access_token"), openid, type, countList);
				}
    			
    		}
    	}
    	return false;
	}
	
	/**
	 * 获取AccessToken
	 * @param type
	 * @return
	 */
	public static Map<String, String> getAccessToken(String type){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("grant_type", "client_credential");
		map.add("appid", appid);
		map.add("secret", appsecret);
		String accessTokenAddress = "https://api.weixin.qq.com/cgi-bin/token";
		logger.debug("开始调用微信-获取公众号accessToken参数："+ accessTokenAddress + ":" + JSON.toJSONString(map));
		String exchange = "";
    	if(REQUEST_TYPE_POST.equalsIgnoreCase(type)){
    		exchange = new RestTemplate().postForObject(accessTokenAddress, map, String.class);
    	}else{
    		accessTokenAddress += "?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
    		exchange = new RestTemplate().getForObject(accessTokenAddress, String.class);
    	}
    	Map<String, String> resultMap = JSON.parseObject(exchange, Map.class);
    	logger.debug("调用微信-获取公众号accessToken：返回结果：" + exchange);
    	return resultMap;
	}
	
	/**
	 * 获取AccessToken
	 * @param type
	 * @return
	 */
	public static String getAccessTokenStr(String type){
		String content = FileUtil.readTxtFile("accessToken");
		logger.debug("获取accessToken内容带时间戳：" + content);
		if(content.contains("-:-")){
			String[] contents = content.split("-:-");
			boolean b = DateUtil.calLastedTime(Long.parseLong(contents[0]), 7100);
			if(b){
				Map<String, String> map = getAccessToken(type);
				if(StringUtils.isNotBlank(map.get("access_token"))){
					String c = new Date().getTime() + "-:-" + map.get("access_token");
					FileUtil.createFile("accessToken", c);
					return map.get("access_token");
				}
			}else{
				return contents[1];
			}
		}else{
			Map<String, String> map = getAccessToken(type);
			if(StringUtils.isNotBlank(map.get("access_token"))){
				String c = new Date().getTime() + "-:-" + map.get("access_token");
				FileUtil.createFile("accessToken", c);
				return map.get("access_token");
			}
			logger.debug("通过调微信接口没有获取到access_token");
		}
		return "";
	}
	
	
	
	/**
	 * jsapi_ticket是公众号用于调用微信JS接口的临时票据
	 * @param code
	 * @param type
	 * @return
	 */
	public static Map<String, String> getTicKet(String access_token, String type){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("access_token", access_token);
		map.add("type", "jsapi");
		String ticKetAddress = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		logger.debug("开始调用微信-jsapi_ticket是公众号用于调用微信JS接口的临时票据："+ ticKetAddress + ":" + JSON.toJSONString(map));
		String exchange = "";
		if(REQUEST_TYPE_POST.equalsIgnoreCase(type)){
			exchange = new RestTemplate().postForObject(ticKetAddress, map, String.class);
		}else{
			ticKetAddress += "?access_token="+access_token+"&type=jsapi";
			exchange = new RestTemplate().getForObject(ticKetAddress, String.class);
		}
		Map<String, String> resultMap = JSON.parseObject(exchange, Map.class);
		logger.debug("调用微信-jsapi_ticket是公众号用于调用微信JS接口的临时票据：返回结果：" + exchange);
		return resultMap;			
	}
	
	/**
	 * 获取ticKet
	 * @param access_token
	 * @param type
	 * @return
	 */
	public static String getTicKet(String type){
		String access_token = getAccessTokenStr(type);
		if("".equals(access_token)){
			logger.debug("获取微信access_token失败了");
			return "";
		}
		String content = FileUtil.readTxtFile("ticKet");
		if(content.contains("-:-")){
			String[] contents = content.split("-:-");
			boolean b = DateUtil.calLastedTime(Long.parseLong(contents[0]), 7100);
			if(b){
				Map<String, String> map = getTicKet(access_token, type);
				if(StringUtils.isNotBlank(map.get("ticket"))){
					String c = new Date().getTime() + "-:-" + map.get("ticket");
					FileUtil.createFile("ticKet", c);
					return map.get("ticket");
				}
			}else{
				return contents[1];
			}
		}else{
			Map<String, String> map = getTicKet(access_token, type);
			if(StringUtils.isNotBlank(map.get("ticket"))){
				String c = new Date().getTime() + "-:-" + map.get("ticket");
				FileUtil.createFile("ticKet", c);
				return map.get("ticket");
			}
			logger.debug("通过调微信接口没有获取到ticKet参数为：access_token："+access_token);
		}
		return "";
	}
//微信获取code地址
	public static String getCodeUrl() {
		String redirectUri = AppPropertiesUtil.getValue("redirect_uri");
		try {
			 redirectUri = URLEncoder.encode(redirectUri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.debug("回调地址转码错误redirectUri="+redirectUri);
		}
		return wxcodeUrl+"?appid="+appid+"&redirect_uri="+redirectUri+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	}
	
}
