package com.cmcc.wxanswer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.wxanswer.dao.AnalysisDao;
import com.cmcc.wxanswer.dao.UserDao;
import com.cmcc.wxanswer.model.Analysis;
import com.cmcc.wxanswer.model.User;


@Service("UserService")
public class UserService {
	@Autowired
	private UserDao ud;
	@Autowired
	private AnalysisDao ad;
	@Transactional
	public int saveUser(User user){
		User save =  ud.save(user);
		if(save != null){
			return 1;
		}
		return 0;
	}
	//验证账号和密码
	public User checkUser(User user) {
			String jpql = "select o from User o where o.name = ?1 and o.pwd = ?2";
			List<Object> params = new ArrayList<Object>();
			params.add(user.getName());
			params.add(user.getPwd());
			List<User> list = ud.findAllList(jpql, params);
		    if(null != list && !list.isEmpty()){
		    	User resUser= list.get(0);
		    	return resUser;
		    }else{
		    	return null;
		    }
	}
	 //查找已答题人数和获奖人数
	public Map<String,Object> getCountRes() {
		String jpql = "select o from Analysis o";
		List<Object> params = new ArrayList<Object>();
		List<Analysis> list = ad.findAllList(jpql, params);
		Long overanswercount = 0l;
		Long count = 0l;
		Long maxCount = 0l;
		if(null != list && !list.isEmpty()){
			for (Analysis analysis : list) {
				overanswercount = overanswercount+analysis.getOveranswercount();
				count = count+analysis.getCount();
				if(analysis.getOveranswercount() > maxCount){
					maxCount = analysis.getOveranswercount();
				}
			}
		}
		Map<String,Object> map = new HashMap<>(); 
		map.put("overanswercount", overanswercount+"");
		map.put("count", count+"");
		map.put("list", list);
		map.put("maxCount", maxCount);
		return map;
	    
}
}
