package com.pixogram.followservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "follow")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Follow implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer userId;// ~other
	
	@Id
	private Integer followerId;// ~mine
}
