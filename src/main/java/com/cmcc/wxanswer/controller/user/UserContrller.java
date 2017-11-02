package com.cmcc.wxanswer.controller.user;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.wxanswer.model.ResultObject;
import com.cmcc.wxanswer.model.User;
import com.cmcc.wxanswer.service.ActivityService;
import com.cmcc.wxanswer.service.UserService;


@Controller("UserController")
public class UserContrller {
	 @Autowired
	 UserService us;
	 @Autowired
	 ActivityService as;
	 
	 
	 
	 @SuppressWarnings("rawtypes")
	 @RequestMapping(value = "/login")
	 @ResponseBody
	 public ResultObject Login(HttpServletRequest request,User user,Model model){ 
		//验证用户账号和密码
	    User resUser = us.checkUser(user);
	    if(null != resUser){
	    	 request.getSession().setAttribute("user", resUser);
	    	 return new ResultObject<>(0, "登录成功");
	    }
		return new ResultObject<>(-1, "账号名或密码错误");	 
	 }
	 /*
	  * 数据流量分析
	  */
	 @RequestMapping(value = "/activityAnalyData")
	 public String activityAnalyData(HttpServletRequest request,User user,Model model){ 
	    	//验证成功 获取截止时间
	    	Calendar now = Calendar.getInstance();
	    	String showTime = now.get(Calendar.YEAR)+"年"+(now.get(Calendar.MONTH) + 1)+"月"+now.get(Calendar.DAY_OF_MONTH)+"日";
	    	//获取活动访问量
	    	 Long visitCount = as.getActivityCount().getVisitCount();
	    	//获取完成答题人数、获奖人数和省份数据列表
	    	 Map<String,Object> map = us.getCountRes();
	    	 model.addAttribute("showTime", showTime);
	    	 model.addAttribute("visitCount", visitCount);
	    	 model.addAttribute("map", map);
	    	 return "/pages/analyData"; 
	 }
	 /*
	  * 登录
	  */
	 @RequestMapping(value = "/toLogin")
	 public String toLogin(HttpServletRequest request,User user,Model model){ 
	    	 return "/pages/login"; 
	 }
}
