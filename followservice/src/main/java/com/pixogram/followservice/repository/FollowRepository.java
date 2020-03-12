package com.pixogram.followservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.followservice.entity.Follow;
import com.pixogram.followservice.model.FollowList;

@Repository
@Transactional
public class FollowRepository{
	@PersistenceContext
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(FollowRepository.class);
	
	public boolean checkFollowing(Integer mineId, Integer otherId) {
		// select * from follow where userId=otherId and followerId=mineId
		TypedQuery<Follow> query = 
				this.entityManager.createQuery("select f from Follow f where f.userId=:otherId and f.followerId=:mineId", Follow.class);
		query.setParameter("mineId", mineId);
		query.setParameter("otherId", otherId);
		return query.getResultList().size() > 0;
	}
	
	//Follow
	public boolean follow(Follow follow) {
		this.entityManager.persist(follow);
		return true;
		
	}
	
	//get Following list
	public List<Integer> getFollowingList(Integer mineId){
		TypedQuery<Integer> query = 
				this.entityManager.createQuery("select f.userId from Follow f where f.followerId=:mineId", Integer.class);
		query.setParameter("mineId", mineId);
		return query.getResultList();
	}
	
	//get followers list
	public List<Integer> getFollowersList(Integer mineId){
		TypedQuery<Integer> query = 
				this.entityManager.createQuery("select f.followerId from Follow f where f.userId=:mineId", Integer.class);
		query.setParameter("mineId", mineId);
		return query.getResultList();
	}
	
	//unfollowing ** deleting 
	public boolean unFollow(Integer mineId, Integer otherId) {	
		Follow follow =this.entityManager.find(Follow.class,new Follow(otherId,mineId));
		logger.info("After getting follow object");
		//logger.info("follow ===>>>",follow.getFollowerId()+" "+follow.getUserId());
		/*
		Query query = this.entityManager.createQuery("Delete from Follow f where f.userId =:otherId and f.followerId =:mineId");
		query.setParameter("otherId",otherId);
		query.setParameter("mineId",mineId);
		
		query.executeUpdate();
		*/
		//Follow follow = new Follow(otherId,mineId);
		this.entityManager.remove(follow);
		logger.info("removed successfully");
		return true;
	}
	
	
	
	

}
