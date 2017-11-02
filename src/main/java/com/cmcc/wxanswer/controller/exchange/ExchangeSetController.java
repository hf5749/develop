package com.cmcc.wxanswer.controller.exchange;

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
import com.cmcc.wxanswer.model.Activity;
import com.cmcc.wxanswer.model.ActivitySet;
import com.cmcc.wxanswer.model.Limit;
import com.cmcc.wxanswer.model.ResultObject;
import com.cmcc.wxanswer.model.User;
import com.cmcc.wxanswer.service.ActivityService;
import com.cmcc.wxanswer.service.ActivitySetService;
import com.cmcc.wxanswer.service.LimitService;
import com.cmcc.wxanswer.util.Date1Util;
import com.cmcc.wxanswer.util.DateUtil;
import com.cmcc.wxanswer.util.Page;
@Controller("ExchangeSetContrller")
@RequestMapping(value = "/exchangeSet")
public class ExchangeSetController {
	private static Logger logger = LoggerFactory.getLogger(ActivityContrller.class);
	@Autowired
	private ActivityService as;
	@Autowired
	private LimitService ls;
	@Autowired
	private ActivitySetService ass;
	@Autowired
	private CustomUSERMemcachedClient customUSERMemcachedClient;
	@RequestMapping(value = "/exchangeSet")
	public String exchange(HttpServletRequest request, Model model) {
		int pageIndex =Integer.parseInt((request.getParameter("pageIndex")==null?"1":request.getParameter("pageIndex")));//页数
		int pageSize =Integer.parseInt((request.getParameter("pageSize")==null?"10":request.getParameter("pageIndex")));//每页数量
		User User = (User) request.getSession().getAttribute("User");
		Activity activity = as.getActivityCount();
		String stratDate = activity.getStartDate();//活动开始时间
		String endDate = activity.getEndtDate();//活动结束时间
		String activityRule = activity.getActivityRule();//活动规则
		List<Limit> limit = ls.getsum();
		Long changeSum = (long) limit.size();//已兑换人数
		Page page = ass.getlist(pageSize,pageIndex);
		model.addAttribute("ActivityRule",as.getActivityCount().getActivityRule());
		model.addAttribute("stratDate", stratDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("activityRule", activityRule);
		model.addAttribute("changeSum", changeSum);
		model.addAttribute("PageCount",page.getPageCount());
		model.addAttribute("PageIndex",page.getPageIndex());
		model.addAttribute("PageSize",page.getPageSize());
		model.addAttribute("list",page.getList());
		model.addAttribute("User", User);
		return "/pages/trafficData";
	}
	
	@RequestMapping(value = "/exchangeSet1")
	@ResponseBody
	public ResultObject exchangeSetTime(HttpServletRequest request, Model model) {
			 try {
				String endTime =Date1Util.getDate3(request.getParameter("endTime"));
				int type = as.updateEndTime(endTime);//更新活动结束时间
				List<ActivitySet> list= ass.getmaxdate();
				Date a = list.get(0).getData();
				int day = DateUtil.daysBetween(a,DateUtil.string2Date(endTime));
				for (int i = 0; i < day; i++) {
					Date c = Date1Util.getBeforeOrAfterDay(a, i+1);
					ActivitySet activitySet =new ActivitySet();
					activitySet.setSum(10000);
					activitySet.setCmcccount(8000);
					activitySet.setCucccount(1000);
					activitySet.setCtcccount(1000);
					activitySet.setData(c);
					activitySet.setOverplus(10000);
					ass.saveUser(activitySet);
				}
				ass.delect(DateUtil.string2Date(endTime));
				this.exchange(request, model);
				logger.debug("更新结束时间成功");
				 return new ResultObject<>(1, "设置结束日期成功");
			} catch (Exception e) {
				logger.debug("更新结束时间失败");
				e.printStackTrace();
			}//活动结束时间
			 return new ResultObject<>(-1, "设置结束日期失败");
	}
	@RequestMapping(value = "/exchangeSet2")
	@ResponseBody
	public ResultObject<Object> exchangeSetRule(HttpServletRequest request, Model model) {
		try {
			String rule = request.getParameter("rule");//活动规则
			int type = as.updateRule(rule);
			if (type>0) {
				customUSERMemcachedClient.delete(CacheKey.getActivityKey());
				logger.debug("更新活动规则成功");
				this.exchange(request, model);
			}
			return new ResultObject<>(1, "设置成功");
		} catch (Exception e) {
			return new ResultObject<>(-1, "设置失败");
		}
	}
	@RequestMapping(value = "/exchangeSet3")
	@ResponseBody
	public ResultObject<Object> exchangeSetSum(HttpServletRequest request, Model model) {
		try {
		String time = request.getParameter("time");//时间
		int sum =Integer.valueOf(request.getParameter("sum")); //数量
		int count = ass.count(time).getSum();//总数
		double s = (double)sum/(double)count;
		int type = ass.exchangeSum(sum,time);//设置总数
		ActivitySet activitySet = ass.count(time);
		int cmcccount = activitySet.getCmcccount();
		cmcccount = (int) (cmcccount*s);
		ass.updatecmccCount(cmcccount,time);//设置移动剩余数量
		int cucccount = activitySet.getCucccount();
		cucccount = (int) (cucccount*s);
		ass.updatecuccCount(cucccount,time);//设置联通剩余数量
		int ctcccount = activitySet.getCtcccount();
		ctcccount = (int) (ctcccount*s);
		ass.updatectccCount(ctcccount,time);//设置电信剩余数量
		int overplu = cmcccount+cucccount+ctcccount;
		int type1 = ass.updateCount(overplu,time);//设置时更新剩余数量
		logger.debug("设置数量成功");
		this.exchange(request, model);
		return new ResultObject<>(1, "设置数量成功");
		} catch (Exception e) {
			return new ResultObject<>(-1, "设置数量失败");
		}
	}
}
