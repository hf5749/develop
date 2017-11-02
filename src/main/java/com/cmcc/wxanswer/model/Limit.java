package com.cmcc.wxanswer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EDU_WXANSWER_LIMIT")
public class Limit implements Serializable{

	/**
	 * @author HF
	 */
	private static final long serialVersionUID = -7610169790256360244L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id; //id
    @Column(name="OPENID")
	private String openId;//微信号
    @Column(name="PHONE")
    private String Phone; //手机号
    @Column(name="TIME")
    private Date time;  //流量领取时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
