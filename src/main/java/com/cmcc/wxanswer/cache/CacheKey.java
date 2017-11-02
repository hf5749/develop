package com.cmcc.wxanswer.cache;

/**
 * @HF
 */
public class CacheKey {
	//单个题目缓存
    public static String getProblemKey(Long problemId) {
        return "WX_ANSWER_PROBLEM_" + problemId;
    } 
    //获取题目列表
	public static String getProblemListKey() {
		return "WX_ANSWER_PROBLEM_LIST";
	}
	//获取活动详情
	public static String getActivityKey() {
		return "WX_ANSWER_ACTIVITY";
	}
	//获取IP省份
	public static String getAddressKey(String openId) {
		return "WX_ANSWER_ADDRESS_"+openId;
	}
	//项目访问量缓存
	public static String getVisitCount() {
		return "WX_ANSWER_VISITCOUNT";
	}
		
}
