package com.cmcc.wxanswer.util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lhy on 2014/8/2.
 */
public class RedisPaginationSupport<T> implements java.io.Serializable{

    private static final long serialVersionUID = -1431267088485328835L;
    private Integer countOnEachPage=10;
    private Long totalCount;
    private List<T> items;
    private Long start;//本次请求的起始记录游标数
    private Long nextStart;//下次请求的起始记录游标数 0表示已经到最后
    private int hasData = 1;//是否有数据，默认有

    public RedisPaginationSupport(Long start, Integer len){
        this.start=start;
        this.countOnEachPage=len;
    }
    public RedisPaginationSupport(Long start){
        this.start=start;
    }
    
	public int getHasData() {
		return hasData;
	}
	public void setHasData(int hasData) {
		this.hasData = hasData;
	}
	public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getNextStart() {
        return nextStart;
    }

    public void setNextStart(Long nextStart) {
        this.nextStart = nextStart;
    }

    public Long getStart(){
         return start>0?start:0; //开始数大于0
    }
    public Long getEnd(){
        return (start>0?start:0)+(countOnEachPage>0?countOnEachPage:1)-1;
    }

    public boolean isValide(Long totalCount){
        if(start<0||start>=totalCount) return false;
        if(countOnEachPage<1) return false;
        return true;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("totalCount", totalCount);
        map.put("nextStart", nextStart);
        map.put("pageData",items);
        map.put("hasData", hasData);
        return map;
    }
}
