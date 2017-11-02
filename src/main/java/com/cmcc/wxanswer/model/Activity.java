package com.cmcc.wxanswer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmcc.wxanswer.util.Date1Util;


@Entity
@Table(name = "EDU_WXANSWER_ACTIVITY")
public class Activity implements Serializable{

	/**
	 * @author HF
	 */
	private static final long serialVersionUID = 4946530924470147521L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long Id; //活动Id
	@Column(name = "ACTIVITYNAME")//活动名称
	private String activityName;
	@Column(name = "STARTTIME") //活动开始时间
	private Date startTime;
	@Column(name = "ENDTIME")
	private Date endTime;   //活动结束时间
	@Column(name = "CREATETIME")	
	private Date createTime;  //创建时间
	@Column(name = "NOWTIME")
	private Date nowTime;   //活动进行时间
	@Column(name = "COUNT")
	private Long count;   //每日限制领取数量
	@Column(name = "CMCCCOUNT")
	private Long cmccCount;   //每日移动限制领取数量
	@Column(name = "CUCCCOUNT")
	private Long cUccCount;   //每日联通限制领取数量
	@Column(name = "CTCCCOUNT")
	private Long ctccCount;   //每日电信限制领取数量
	@Column(name="ACTIVITYRULE")
	private String activityRule;  //活动规则
	@Column(name="OVERANSWERCOUNT")
	private Long overAnswerCount;//完成答题的人数
	@Column(name="VISITCOUNT")
	private Long visitCount;//访问人数
	
	public String getStartDate() {
		return Date1Util.getDate1(startTime);
	}
	public String getEndtDate() {
		return Date1Util.getDate1(endTime);
	}
	
	public Long getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
	public Long getOverAnswerCount() {
		return overAnswerCount;
	}
	public void setOverAnswerCount(Long overAnswerCount) {
		this.overAnswerCount = overAnswerCount;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getNowTime() {
		return nowTime;
	}
	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getActivityRule() {
		return activityRule;
	}
	public void setActivityRule(String activityRule) {
		this.activityRule = activityRule;
	}
	public Long getCmccCount() {
		return cmccCount;
	}
	public void setCmccCount(Long cmccCount) {
		this.cmccCount = cmccCount;
	}
	public Long getcUccCount() {
		return cUccCount;
	}
	public void setcUccCount(Long cUccCount) {
		this.cUccCount = cUccCount;
	}
	public Long getCtccCount() {
		return ctccCount;
	}
	public void setCtccCount(Long ctccCount) {
		this.ctccCount = ctccCount;
	}
	
	
	
}
