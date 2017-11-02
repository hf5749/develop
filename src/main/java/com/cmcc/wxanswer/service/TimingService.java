package com.cmcc.wxanswer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;



/**
 * 定时更新
 */
@Service
public class TimingService  implements ApplicationListener<ContextRefreshedEvent>{
	final Logger logger = LoggerFactory.getLogger(TimingService.class);
	@Autowired
	private ActivityService as;
	    @Override
	    public void onApplicationEvent(ContextRefreshedEvent event)
	    {
	        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
	        { 
	            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。 
	        	try {
	        		//System.out.println("定时器");
					as.updateTimeVisitCount();//定时更新访问量
				} catch (Exception e) {
					System.out.println("定时任务失败");
					e.printStackTrace();
					logger.debug("定时任务timmingAdver:" + e);
				}
	        } 
	    }

}
