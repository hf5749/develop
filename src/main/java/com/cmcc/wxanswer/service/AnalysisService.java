package com.cmcc.wxanswer.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.wxanswer.dao.AnalysisDao;
import com.cmcc.wxanswer.model.Analysis;


@Service("AnalysisService")
public class AnalysisService {
	@Autowired
	private AnalysisDao ad;
	
	@Transactional
	public int saveAnalysis(Analysis analysis){
		Analysis save =  ad.save(analysis);
		if(save != null){
			return 1;
		}
		return 0;
	}
	//判断省份是否存在
	public boolean privinceIsExist(String privince){
		List<Analysis> list = null;
		String jpql = "select o from Analysis o where o.privince = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(privince);
	    try {
			list = ad.findAllList(jpql, params);
		} catch (Exception e) {
			return false;
		}
	    if(null != list && !list.isEmpty()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	//更新省份对应已答题人数数目
	public int updateOverAnswerCount(String privince) {
		String jpql = "update EDU_WXANSWER_ANALYSIS o set o.overAnswerCount = o.overAnswerCount+1 where o.privince = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(privince);
		return ad.update(jpql, params);
	}
	//更新省份对应领取流量数目
	public int updateCount(String privince) {
		String jpql = "update EDU_WXANSWER_ANALYSIS o set o.count = o.count+1 where o.privince = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(privince);
		return ad.update(jpql, params);
	}
}
