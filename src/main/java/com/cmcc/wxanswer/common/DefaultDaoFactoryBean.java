package com.cmcc.wxanswer.common;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class DefaultDaoFactoryBean <T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {
		
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		 
    	return new DefaultDaoFactory(entityManager);
    }
	
	
	
}
