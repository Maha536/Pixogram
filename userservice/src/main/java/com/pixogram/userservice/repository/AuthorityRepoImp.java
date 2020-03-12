package com.pixogram.userservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pixogram.userservice.entity.Authorities;

@Repository
@Transactional
public class AuthorityRepoImp implements AuthorityRepository {
	
	@PersistenceContext
	private EntityManager em;
	
		
	@Override
	public void save(Authorities auth) {
		// TODO Auto-generated method stub
		this.em.persist(auth);
	}


	@Override
	public void delete(Authorities auth) {
		// TODO Auto-generated method stub
		this.em.detach(auth);
	}

}
