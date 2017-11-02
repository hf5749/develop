package com.cmcc.wxanswer.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cmcc.wxanswer.common.BaseDao;
import com.cmcc.wxanswer.model.User;



@Repository
public interface UserDao extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>,BaseDao<User, Long>{
	
}
