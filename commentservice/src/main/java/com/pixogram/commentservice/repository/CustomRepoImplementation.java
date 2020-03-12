package com.pixogram.commentservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pixogram.commentservice.model.CommentsCountModel;

@Repository
public class CustomRepoImplementation implements CustomRepo {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public CommentsCountModel findCountById(Integer mediaid) {
		// TODO Auto-generated method stub
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Comments c WHERE mediaId=:mediaid",Long.class);
		query.setParameter("mediaid", mediaid);
		Long count = (long) query.getSingleResult();
		Integer a = Math.toIntExact(count);
		CommentsCountModel data = new CommentsCountModel(a);
		
		return data;
	}

}
