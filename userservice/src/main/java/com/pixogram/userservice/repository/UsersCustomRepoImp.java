package com.pixogram.userservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pixogram.userservice.exception.UserNotFoundException;
import com.pixogram.userservice.model.Userid;

@Repository
@Transactional
public class UsersCustomRepoImp implements IUsersCustomRepo {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Userid getUserId(String username) {
		// TODO Auto-generated method stub
		try {
			logger.info(username);
		TypedQuery<Userid> query = entityManager.createQuery("SELECT u.id,u.firstName,u.lastName,u.profile FROM Users u WHERE userName = :username",Userid.class);
		query.setParameter("username", username);
		logger.info("Query created and trying to execute it..");
		List<Userid> data = query.getResultList();
		logger.info("LIST VALUES : "+data);
		Userid user=data.get(0);
		logger.info("value :"+user);
		return user;
		}
		catch(Exception e) {
			throw new UserNotFoundException("id not found in repository");
		}
	}

	@Override
	public List<String> getUsernames() {
		// TODO Auto-generated method stub
		try {
		TypedQuery<String> query = entityManager.createQuery("select u.userName from Users u",String.class);
		List<String> names = query.getResultList();
		return names;
	}catch(Exception e) {
		throw new UserNotFoundException("Repository exception"+e);
	}

}

	@Override
	public String getUsernameById(Integer id) {
		// TODO Auto-generated method stub
		TypedQuery<String> query = this.entityManager.createQuery("select u.userName from Users u where u.id=:userId",String.class);
		query.setParameter("userId", id);
		String result = query.getSingleResult();
		return result;
	}
}
