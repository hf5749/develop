package com.cmcc.wxanswer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.wxanswer.cache.CacheKey;
import com.cmcc.wxanswer.cache.CustomUSERMemcachedClient;
import com.cmcc.wxanswer.dao.ActivityDao;
import com.cmcc.wxanswer.dao.NewActivityDao;
import com.cmcc.wxanswer.model.Activity;

@Service("ActivityService")
public class ActivityService {
	@Autowired
	private ActivityDao ad;
	@Autowired
	private NewActivityDao newAd;
	@Autowired
	private CustomUSERMemcachedClient customUSERMemcachedClient;
	private static Logger logger = LoggerFactory.getLogger(ActivityService.class);
     //活动信息保存	
	@Transactional
	public int saveUser(Activity activity){
		Activity save =  ad.save(activity);
		if(save != null){
			return 1;
		}
		return 0;
	}
   // 查询活动信息
	public Activity getActivity() {
		Activity activity = (Activity) customUSERMemcachedClient.get(CacheKey.getActivityKey());
		if(null == activity){
			String jpql = "select o from Activity o";
			List<Object> params = new ArrayList<Object>();
			activity = ad.findAllList(jpql, params).get(0);
			customUSERMemcachedClient.set(CacheKey.getActivityKey(), 24*60*60, activity);
			return activity;
		}
		return activity;
	}
  //更新答题人数
	public int updateOverAnswerCount() {
		String jpql = "update EDU_WXANSWER_ACTIVITY o set o.overAnswerCount = o.overAnswerCount+1";
		List<Object> params = new ArrayList<Object>();
		return ad.update(jpql, params);
	}
  //更新访问人数
	public int updateVisitCount() {
		String jpql = "update EDU_WXANSWER_ACTIVITY o set o.visitCount = o.visitCount+1";
		List<Object> params = new ArrayList<Object>();
		return ad.update(jpql, params);
	}
	  // 查询活动信息
	public Activity getActivityCount() {
		String jpql = "select o from Activity o";
	    List<Object> params = new ArrayList<Object>();   
		return ad.findAllList(jpql, params).get(0);
	}
	// 更新活动结束时间
	public int updateEndTime(String date) {
		String jpql = "update EDU_WXANSWER_ACTIVITY o set o.endtime = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		return ad.update(jpql, params);
	}
	// 更新活动规则
	public int updateRule(String rule) {
		String jpql = "update EDU_WXANSWER_ACTIVITY o set o.activityrule = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(rule);
		return ad.update(jpql, params);
	}
	//判断活动是否开始
	public boolean isNotStart() {
		String jpql = "select o from Activity o where TO_DAYS(NOW()) - TO_DAYS(o.startTime) < 0";
	    List<Object> params = new ArrayList<Object>(); 
		List<Activity> resList = ad.findAllList(jpql, params);
		if(null != resList && ! resList.isEmpty()){
			return true;
		}else{
		    return false;
		}
		
	}
	//判断活动是否结束
	public boolean isEnd() {
		String jpql = "select o from Activity o where TO_DAYS(NOW()) - TO_DAYS(o.endTime) > 0";
	    List<Object> params = new ArrayList<Object>(); 
		List<Activity> resList = ad.findAllList(jpql, params);
		if(null != resList && ! resList.isEmpty()){
			return true;
		}else{
		return false;
		}
		
	}
	private int i = 1;//防止任务重复
	public void updateTimeVisitCount() {
		if(i==1){
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){		
				public void run() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
					System.out.println(sdf.format(new Date())+"定时任务开启！++++++++++=====》开始执行访问量更新操作！");
					logger.debug(sdf.format(new Date())+"定时任务开启！++++++++++=========================>>>>>>>开始执行访问量更新操作！");
					//获取缓存中的访问量
					Long visitCount = (Long) customUSERMemcachedClient.get(CacheKey.getVisitCount());
					if(null != visitCount){
						int res = newAd.updateVisitCount(visitCount);
						if(res > 0){
							System.out.println("本次变更访问量visitCount==》"+visitCount);
							logger.debug("数据库更新成功！++++++++++=========================>>>>>>>本次变更访问量visitCount==》"+visitCount);
							Long nowVisitCount = (Long) customUSERMemcachedClient.get(CacheKey.getVisitCount());
							if(null != nowVisitCount){
								if(nowVisitCount >= visitCount){
									logger.debug("Memcached最新初始化变化值为！++++++++++=========================>>>>>>>changeCount==》"+(nowVisitCount-visitCount));
									customUSERMemcachedClient.set(CacheKey.getVisitCount(), 24*60*60,nowVisitCount-visitCount);
								}
							}
						}
					}			
				}
				
			}, 2000, 300000);//设置时间
			i++;
		}	
}
}
