package com.cmcc.wxanswer.model;

import java.io.Serializable;
import java.util.List;

public class ProblemVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6208934258881957213L;
	private Long rightCount;// 正确数量
	private Long wrongCount;// 错误数量
	private List<Problem> problems;// 题目列表
	private List<Long> wrongProblems;// 错误题目列表
	public Long getRightCount() {
		return rightCount;
	}
	public void setRightCount(Long rightCount) {
		this.rightCount = rightCount;
	}
	public Long getWrongCount() {
		return wrongCount;
	}
	public void setWrongCount(Long wrongCount) {
		this.wrongCount = wrongCount;
	}
	public List<Problem> getProblems() {
		return problems;
	}
	public void setProblems(List<Problem> problems) {
		this.problems = problems;
	}
	public List<Long> getWrongProblems() {
		return wrongProblems;
	}
	public void setWrongProblems(List<Long> wrongProblems) {
		this.wrongProblems = wrongProblems;
	}
	
}
