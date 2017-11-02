package com.cmcc.wxanswer.controller.activity;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.wxanswer.cache.CacheKey;
import com.cmcc.wxanswer.cache.CustomUSERMemcachedClient;
import com.cmcc.wxanswer.model.Activity;
import com.cmcc.wxanswer.model.ResultObject;
import com.cmcc.wxanswer.service.ActivityService;
import com.cmcc.wxanswer.util.AddressUtil;
import com.cmcc.wxanswer.util.IpUtils;
import com.cmcc.wxanswer.util.SignUtil;
import com.cmcc.wxanswer.util.WxUtil;



/**
 * @title 用于获取活动相关信息
 * @author HF
 * @version 2017年6月6日 上午10:44:17
 */
@Controller("ActivityContrller")
@RequestMapping(value="/activity")
public class ActivityContrller {
	private static Logger logger = LoggerFactory.getLogger(ActivityContrller.class);
	@Autowired
	private ActivityService as;
	@Autowired
	private CustomUSERMemcachedClient customUSERMemcachedClient;
	 @RequestMapping(value = "/index")
	 public String index(HttpServletRequest request,Model model){
		//获取微信code
		String code = request.getParameter("code");
		logger.debug("获取code==》code="+code);
		logger.debug("获取请求参数==》"+request.getQueryString());
		//获取随机的openId
		String openId = UUID.randomUUID().toString();
/*	
 * 以下代码暂时弃用
 * 	
 * try {
			//获取用户openId
			Map<String,String> map = WxUtil.getWxUser(code, "post");
			openId = map.get("openid");
		} catch (Exception e1) {
			logger.debug("获取openId异常==》code="+code);
		}*/
		try {
			String ip = IpUtils.getIpAddr(request);
			String visitAddress = AddressUtil.getAddresses("ip="+ip, "utf-8");
			if(StringUtils.isBlank(visitAddress) || visitAddress.equals("0")){
				customUSERMemcachedClient.set(CacheKey.getAddressKey(openId), 60*60, "北京市");
			}else{
				customUSERMemcachedClient.set(CacheKey.getAddressKey(openId), 60*60, visitAddress);
			}	
		} catch (Exception e) {
			logger.debug("获取访问Ip省份异常"+e.getMessage());
		}
		Activity activity = null;
		try {
			//更新访问人数
				Long visitCount = (Long) customUSERMemcachedClient.get(CacheKey.getVisitCount());
			    if(null != visitCount){
			    	visitCount++;
			    	customUSERMemcachedClient.set(CacheKey.getVisitCount(), 24*60*60, visitCount);
			    }else{
			    	customUSERMemcachedClient.set(CacheKey.getVisitCount(), 24*60*60, 0l);
			    }
//			int res = as.updateVisitCount();
//			if(res > 0){
//				logger.debug("更新访问人数成功");
//			}else{
//				logger.debug("更新访问人数失败");
//			}
			//获取活动规则
			activity = as.getActivity();
			//保存用户标示openId
			logger.debug("保存用户标示==》openId="+openId);
			request.getSession().setAttribute("openId", openId);
		} catch (Exception e) {
			logger.debug("获取活动信息失败"+e.getMessage());
		}
		 model.addAttribute("activity", activity);
		return "/pages/index";	 
	 }
	 /*
	  * 主页跳转
	  */
	 @RequestMapping(value = "/")
	 public String toIndex(HttpServletRequest request,Model model){
		//获取微信code
//		String url = WxUtil.getCodeUrl();
		return "redirect:/activity/index";	 
	 }
	 /*
	  * 活动分享
	  */
	 @SuppressWarnings("rawtypes")
	 @RequestMapping(value = "/share")
	 @ResponseBody
	 public ResultObject share(HttpServletRequest request,Model model){
		String url = request.getParameter("url");
		String ticKet ="";
		try {
			//获取ticKet
			ticKet = WxUtil.getTicKet(WxUtil.REQUEST_TYPE_GET);
		} catch (Exception e) {
			logger.error("获取ticket异常:"+e.getMessage(), e);
			return new ResultObject<>(-1, "获取ticket异常,分享失败");	
		}
		try {
			Map<String, String> map = SignUtil.sign(ticKet, url);
			return new ResultObject<>(0, "获取签名数据成功",map);	
		} catch (Exception e) {
			return new ResultObject<>(-2, "获取签名数据异常+ticKet="+ticKet+"url="+url+e);	
		}	 
	 }
	 /*
	  * 判断活动是否开始
	  */
	 @SuppressWarnings("rawtypes")
	 @RequestMapping(value = "/isStart")
	 @ResponseBody
	 public ResultObject isStart(HttpServletRequest request,Model model){
		//判断活动是否开始
		boolean isNotStart = as.isNotStart();
		if(isNotStart){
			return new ResultObject<>(-1, "活动尚未开始！");	
		}
		//判断活动是否结束
		boolean isEnd = as.isEnd();
		if(isEnd){
			return new ResultObject<>(-2, "活动已经结束！");	
		}
		return new ResultObject<>(0, "活动进行中！");
		
	 }
}
