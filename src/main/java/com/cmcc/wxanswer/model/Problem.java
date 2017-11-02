package com.cmcc.wxanswer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EDU_WXANSWER_PROBLEM")
public class Problem implements Serializable {
	/**
	 * @author HF
	 */
	private static final long serialVersionUID = -848271835027119961L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;// 题目Id
	@Column(name = "CONTENT")
	private String content;// 题目内容
	@Column(name = "OPTIONS")
	private String option; // 题目对应选项
	@Column(name = "IMAGE")
	private String imageUrl; // 题目对应图片
	@Column(name = "RIGHTANSWER")
	private String rightanswer;// 正确答案
	@Transient
	private List<Option> options;// 选项列表
	@Transient
	private String answer;// 用户答案
	@Transient
	private int isRight;// 是否做对0-对1-错
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRightanswer() {
		return rightanswer;
	}

	public void setRightanswer(String rightanswer) {
		this.rightanswer = rightanswer;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getIsRight() {
		return isRight;
	}

	public void setIsRight(int isRight) {
		this.isRight = isRight;
	}
	

}
