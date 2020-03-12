package com.pixogram.actionservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pixogram.actionservice.model.ActionCountAndStatus;
import com.pixogram.actionservice.model.ActionsCountModel;

@Repository
public class CustomRepoImp implements CustomRepo {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public ActionsCountModel getLikes(Integer id) {
		// TODO Auto-generated method stub
		TypedQuery<Long> query = entityManager.createQuery("select count(a) from Actions a where a.mediaId=:id and a.status=true",Long.class);
		query.setParameter("id",id);
		//query.setParameter("true", true);
		TypedQuery<Long> query1 = entityManager.createQuery("select count(a) from Actions a where a.mediaId=:id and a.status=false",Long.class);
		query1.setParameter("id",id);
		//query1.setParameter("false", false);
		
		Long count = (long) query.getSingleResult();
		Long count1 = (long) query1.getSingleResult();
		Integer likes = Math.toIntExact(count);
		Integer unlikes  = Math.toIntExact(count1);
		ActionsCountModel result = new ActionsCountModel(likes, unlikes);
		return result;
	}



	@Override
	public ActionCountAndStatus getCountAndStatus(Integer mediaId,Integer userId) {
		// TODO Auto-generated method stub
		TypedQuery<Long> query = this.entityManager.createQuery("select count(a) from Actions a where a.mediaId=:id and a.status=true",Long.class);
		query.setParameter("id",mediaId);
		//query.setParameter("true", true);
		TypedQuery<Long> query1 = this.entityManager.createQuery("select count(a) from Actions a where a.mediaId=:id and a.status=false",Long.class);
		query1.setParameter("id",mediaId);
		//query1.setParameter("false", false);
		TypedQuery<Boolean> query3 = this.entityManager.createQuery("select a.status from Actions a where a.userId=:userid and a.mediaId=:mediaid",Boolean.class);
		query3.setParameter("userid", userId);
		query3.setParameter("mediaid", mediaId);
		Long count = (long) query.getSingleResult();
		Long count1 = (long) query1.getSingleResult();
		Boolean status;
		try {
			status = query3.getSingleResult();
		}catch(Exception e) {
			status = null;
		}
		Integer likes = Math.toIntExact(count);
		Integer unlikes  = Math.toIntExact(count1);
		ActionCountAndStatus action = new ActionCountAndStatus(likes,unlikes,status);
		return action;
	}
	
	

}
