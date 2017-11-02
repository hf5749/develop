package com.cmcc.wxanswer.dao;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cmcc.wxanswer.common.BaseJdbcDaoImpl;



@Repository
public class NewActivityDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(NewActivityDao.class);
	public int updateVisitCount(Long changeCount) {
		String jpql = "update EDU_WXANSWER_ACTIVITY o set o.VISITCOUNT = o.VISITCOUNT + ?1";
		try {
			Object[] params = new Object[] {changeCount};
			int res = this.getJdbcTemplate().update(jpql.toString(), params);
			if (res > 0) {
				return res;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return -1;
		}
	}
	
}
