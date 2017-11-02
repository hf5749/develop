package com.cmcc.wxanswer.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cmcc.wxanswer.util.Date1Util;

@Entity
@Table(name = "EDU_WXANSWER_SET")
public class ActivitySet implements Serializable{

	private static final long serialVersionUID = -7618635561454377079L;
	/**
	 * @author SZJ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long Id; //活动Id
	@Column(name = "SUM")//总数量
	private int sum;
	@Column(name = "CMCCCOUNT") //移动剩余数量
	private int cmcccount;
	@Column(name = "CUCCCOUNT")
	private int cucccount;   //联通剩余数量
	@Column(name = "CTCCCOUNT")	
	private int ctcccount;  //电信剩余数量
	@Column(name = "DATA")
	private Date data;   //兑换日期
	@Column(name = "OVERPLUS")
	private int overplus;   //剩余总数
	@Transient
	private String showTime;// 用户答案

	public int getOverplus() {
		return overplus;
	}
	public void setOverplus(int overplus) {
		this.overplus = overplus;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCmcccount() {
		return cmcccount;
	}
	public void setCmcccount(int cmcccount) {
		this.cmcccount = cmcccount;
	}
	public int getCucccount() {
		return cucccount;
	}
	public void setCucccount(int cucccount) {
		this.cucccount = cucccount;
	}
	public int getCtcccount() {
		return ctcccount;
	}
	public void setCtcccount(int ctcccount) {
		this.ctcccount = ctcccount;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getShowTime() {
		return Date1Util.getDate2(data);
	}

}

