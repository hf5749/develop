package com.cmcc.wxanswer.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.wxanswer.dao.ActivityDao;
import com.cmcc.wxanswer.dao.LimitDao;
import com.cmcc.wxanswer.model.Activity;
import com.cmcc.wxanswer.model.Limit;

@Service("LimitService")
public class LimitService {
	@Autowired
	private LimitDao ld;
	@Autowired
	private ActivityDao ad;
	@Transactional
	public int saveUser(Limit Limit){
		Limit save =  ld.save(Limit);
		if(save != null){
			return 1;
		}
		return 0;
	}
	// 查询该微信号是否已兑换过
	 public List<Limit> wxExchangeType(String openId) {
		 int a = 0;//0为未兑换过1为已兑换过
			String jpql = "select o from Limit o where o.openId=?1";
			List<Object> params = new ArrayList<Object>();
			params.add(openId);
			List<Limit> list = ld.findAllList(jpql, params);
			return list;
		} 
	// 查询该手机号是否已兑换过
	 public int phoneExchangeType(String phone) {
		 int a = 0;//0为未兑换过1为已兑换过
			String jpql = "select o from Limit o where o.Phone=?1";
			List<Object> params = new ArrayList<Object>();
			params.add(phone);
			List<Limit> list = ld.findAllList(jpql, params);
			if (0 == list.size()) {
				return a;
			}else {
				a=1;
			}
			return a;
		}
		// 查询已兑换数量
	 public List<Limit> getsum() {
			String jpql = "select o from Limit o";
			List<Object> params = new ArrayList<Object>();
			return ld.findAllList(jpql, params);
		}

}
