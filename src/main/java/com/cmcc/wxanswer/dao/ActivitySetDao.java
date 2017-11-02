package com.cmcc.wxanswer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cmcc.wxanswer.common.BaseDao;
import com.cmcc.wxanswer.model.ActivitySet;

@Repository
public interface ActivitySetDao extends JpaRepository<ActivitySet, Long>,JpaSpecificationExecutor<ActivitySet>,BaseDao<ActivitySet, Long>{
	
}