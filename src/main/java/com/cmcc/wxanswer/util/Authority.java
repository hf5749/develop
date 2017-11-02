package com.cmcc.wxanswer.util;

/**
 * 动态权限控制枚举
 * @author zhangyue
 *
 */
public enum Authority{

	OPEN("公开", 0), FRIENDOPEN("好友", 1), PRIVATE("私密", 2);
	// 成员变量
	private String name;
	private int index;
	// 构造方法
	private Authority(String name, int index) {
		this.name = name;
		this.index = index;
	}
	//覆盖方法
	@Override
	public String toString() {
		return this.name;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * 返回权限枚举对象根据原生的权限code和类型
	 * @param code  原生权限数值
	 * @param type  PD-日志表里的权限类型   PA-相册表里的权限类型
	 * @return
	 */
	public static Authority getAuthorityByCode(int code, String type){
		if("PD".equals(type)){
			if(code == 0){
				return Authority.OPEN;//日志
			}else if(code == 1){
				return Authority.FRIENDOPEN;
			}else if(code == 2){
				return Authority.PRIVATE;
			}
		}else if("PA".equals(type)){
			if(code == 0){
				return Authority.OPEN;
			}else if(code == 1){
				return Authority.PRIVATE;
			}else if(code == 2){
				return Authority.FRIENDOPEN;
			}
		}
		return null;
	}
}
