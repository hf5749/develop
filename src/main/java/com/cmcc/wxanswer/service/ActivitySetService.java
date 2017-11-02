package com.cmcc.wxanswer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.wxanswer.dao.ActivitySetDao;
import com.cmcc.wxanswer.model.Activity;
import com.cmcc.wxanswer.model.ActivitySet;
import com.cmcc.wxanswer.util.Page;
@Service("ActivitySetService")
public class ActivitySetService {
	@Autowired
	private ActivitySetDao asd;
	@Transactional
	public int saveUser(ActivitySet activitySet){
		ActivitySet save =  asd.save(activitySet);
		if(save != null){
			return 1;
		}
		return 0;
	}
	// 设置可兑换总数
	 public int exchangeSum(int sum,String date) {
			String jpql = "update EDU_WXANSWER_SET o set o.sum = ?1 where to_days(data) = to_days(?2)";
			List<Object> params = new ArrayList<Object>();
			params.add(sum);
			params.add(date);
			return asd.update(jpql, params);
		}
	 //查询剩余数量
	 public ActivitySet count(String date) {
			String jpql = "select o from ActivitySet o where to_days(data) = to_days(?1)";
			List<Object> params = new ArrayList<Object>();
			params.add(date);
			List<ActivitySet> list = asd.findAllList(jpql, params);
			return list.get(0);
		}
	//查询
	 public Page getlist(int pageSize,int pageIndex) {
			String jpql = "select o from ActivitySet o order by o.id asc";
			List<Object> params = new ArrayList<Object>();
			Page page = asd.getAllPage(jpql, params, pageSize, pageIndex);
			return page;
		}
		//兑换时更新移动剩余数量
		public int updatecmccCount1(String date) {
			String jpql = "update EDU_WXANSWER_SET o set o.cmcccount = o.cmcccount-1 where to_days(data) = to_days(?1)";
			List<Object> params = new ArrayList<Object>();
			params.add(date);
			return asd.update(jpql, params);
			}
		//兑换时更新联通剩余数量
		public int updatecuccCount1(String date) {
			String jpql = "update EDU_WXANSWER_SET o set o.cucccount = o.cmcccount-1 where to_days(data) = to_days(?1)";
			List<Object> params = new ArrayList<Object>();
			params.add(date);
			return asd.update(jpql, params);
			}
		//兑换时更新电信剩余数量
		public int updatectccCount1(String date) {
			String jpql = "update EDU_WXANSWER_SET o set o.ctcccount = o.cmcccount-1 where to_days(data) = to_days(?1)";
			List<Object> params = new ArrayList<Object>();
			params.add(date);
			return asd.update(jpql, params);
			}
		//兑换时更新剩余数量
		public int updateCount(String date) {
			String jpql = "update EDU_WXANSWER_SET o set o.overplus = o.overplus-1 where to_days(data) = to_days(?1)";
			List<Object> params = new ArrayList<Object>();
			params.add(date);
			return asd.update(jpql, params);
			}
		//后台设置时更新移动剩余数量
		public int updatecmccCount(int count,String time) {
			String jpql = "update EDU_WXANSWER_SET o set o.cmcccount = ?1 where o.data=?2";
			List<Object> params = new ArrayList<Object>();
			params.add(count);
			params.add(time);
			return asd.update(jpql, params);
			}
		//后台设置时更新联通剩余数量
		public int updatecuccCount(int count,String time) {
			String jpql = "update EDU_WXANSWER_SET o set o.cucccount = ?1 where o.data=?2";
			List<Object> params = new ArrayList<Object>();
			params.add(count);
			params.add(time);
			return asd.update(jpql, params);
			}
		//后台设置时更新电信剩余数量
		public int updatectccCount(int count,String time) {
			String jpql = "update EDU_WXANSWER_SET o set o.ctcccount = ?1 where o.data=?2";
			List<Object> params = new ArrayList<Object>();
			params.add(count);
			params.add(time);
			return asd.update(jpql, params);
			}
		//设置时更新剩余数量
		public int updateCount(int sum,String date) {
			String jpql = "update EDU_WXANSWER_SET o set o.overplus = ?1 where o.data = ?2";
			List<Object> params = new ArrayList<Object>();
			params.add(sum);
			params.add(date);
			return asd.update(jpql, params);
			}
		//获取最大时间
		public List<ActivitySet> getmaxdate() {
			String jpql = "select o from ActivitySet o order by o.data desc";
			List<Object> params = new ArrayList<Object>();
			List<ActivitySet> list = asd.findAllList(jpql, params);
			return list;
			}
		//获取最大时间
		public void delect(Date date) {
//			String jpql = "delect from ActivitySet o where o.data =>?1";
			String jpql = "delete from ActivitySet o where o.data>?1";
			List<Object> params = new ArrayList<Object>();
			params.add(date);
			asd.batchDelete(jpql, params);
			}
}
