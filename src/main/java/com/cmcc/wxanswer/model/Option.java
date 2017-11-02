package com.cmcc.wxanswer.model;

import java.io.Serializable;

public class Option implements Serializable{

	/**
	 * @author HF
	 */
	private static final long serialVersionUID = -755454199546499850L;
    private String optionName;//选项名称
    private String optionValue;//选项值
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
    

}
