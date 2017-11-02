package com.cmcc.wxanswer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EDU_WXANSWER_ANALYSIS")
public class Analysis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3353700575259399786L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long Id;//数据分析表Id
	@Column(name = "PRIVINCE")
	private String privince;//省份
	@Column(name = "COUNT")
	private Long count;//获奖人数
	@Column(name = "OVERANSWERCOUNT")
	private Long overanswercount;//完成答题人数
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getPrivince() {
		return privince;
	}
	public void setPrivince(String privince) {
		this.privince = privince;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getOveranswercount() {
		return overanswercount;
	}
	public void setOveranswercount(Long overanswercount) {
		this.overanswercount = overanswercount;
	}
	

}
