package com.cmcc.wxanswer.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cmcc.wxanswer.common.BaseDao;
import com.cmcc.wxanswer.model.Problem;
import com.cmcc.wxanswer.model.User;



@Repository
public interface ProblemDao extends JpaRepository<Problem, Long>,JpaSpecificationExecutor<Problem>,BaseDao<Problem, Long>{
	
}
