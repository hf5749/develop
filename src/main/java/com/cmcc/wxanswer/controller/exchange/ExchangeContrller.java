package com.cmcc.wxanswer.controller.exchange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.wxanswer.cache.CacheKey;
import com.cmcc.wxanswer.cache.CustomUSERMemcachedClient;
import com.cmcc.wxanswer.controller.activity.ActivityContrller;
import com.cmcc.wxanswer.model.ActivitySet;
import com.cmcc.wxanswer.model.Limit;
import com.cmcc.wxanswer.model.ResultObject;
import com.cmcc.wxanswer.service.ActivitySetService;
import com.cmcc.wxanswer.service.AnalysisService;
import com.cmcc.wxanswer.service.LimitService;
import com.cmcc.wxanswer.util.PhoneUtil;

/**
 * @title 用于获取活动相关信息
 * @author SZJ
 * @version 2017年6月9日
 */

@Controller("ExchangeContrller")
@RequestMapping(value = "/exchange")
public class ExchangeContrller {
	private static Logger logger = LoggerFactory.getLogger(ActivityContrller.class);

	@Autowired
	private LimitService ls;
	@Autowired
	private CustomUSERMemcachedClient customUSERMemcachedClient;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private ActivitySetService ass;

	@RequestMapping(value = "/exchange")
	@ResponseBody
	public ResultObject exchange(HttpServletRequest request, Model model) {
		String phone = request.getParameter("phone");// 电话号
		String accessToken = request.getParameter("accessToken");//accessToken
		String openId = (String) request.getSession().getAttribute("openId"); // 微信号
		logger.debug("session获取openId"+openId);
		String privince = (String) customUSERMemcachedClient.get(CacheKey.getAddressKey(openId));// 省份
		String operators = PhoneUtil.validateMobile(phone);// 获取该手机运营商
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (null == openId) {
			logger.debug("用户未登录"+openId);
			new ResultObject<>(5, "兑换失败");
		}
		if (null == operators) {
			logger.debug("未知运营商");
			new ResultObject<>(5, "兑换失败");
		}
		ActivitySet activityset = ass.count(date);
		long many = 0;
		if (operators.equals("1")) {
			many = activityset.getCmcccount();// 获取剩余礼品数量
		} else if (operators.equals("2")) {
			many = activityset.getCucccount();// 获取剩余礼品数量
		} else if (operators.equals("3")) {
			many = activityset.getCtcccount();// 获取剩余礼品数量
		}
		if (many > 0) {
			int phoneType = ls.phoneExchangeType(phone);// 判断该手机号是否兑换过
			if (0 == phoneType) {
				List<Limit> limits = ls.wxExchangeType(openId);
				int wxtype = limits.size();// 判断该微信号是否已兑换过
				if (wxtype > 0) {
					logger.debug("该微信号已兑换过");
					return new ResultObject<>(3, "该微信号已兑换过");
				}
				Limit limit = new Limit();
				limit.setPhone(phone);
				limit.setTime(new Date());
				limit.setOpenId(openId);
				ls.saveUser(limit);
				// 更新剩余兑换数量
				if (operators.equals("1")) {
					ass.updatecmccCount1(date);
					ass.updateCount(date);
				} else if (operators.equals("2")) {
					ass.updatecuccCount1(date);
					ass.updateCount(date);
				} else if (operators.equals("3")) {
					ass.updatecuccCount1(date);
					ass.updateCount(date);
				}
				// 更新兑换的人数
				try {
					// 更新对应兑换人数
					int updateRes = analysisService.updateCount(privince);
					if (updateRes > 0) {
						logger.debug("更新兑换人数成功 ");
					} else {
						logger.debug("更新兑换人数失败 ==>privince=" + privince);
					}
				} catch (Exception e) {
					logger.debug("更新兑换人数异常 " + e.getMessage() + "==>privince=" + privince);
				}
				Calendar cal = Calendar.getInstance();
				request.getSession().removeAttribute("openId");
				return new ResultObject<>(0, "兑换成功", cal.get(Calendar.MONTH) + 2);
			}
		} else {
			return new ResultObject<>(2, "兑换失败");
		}
		return new ResultObject<>(5, "兑换失败");

	}
}
