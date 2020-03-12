package com.pixogram.newsfeedservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "newsfeed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Newsfeed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer userId;

	@Column
	private String feed;

	@Column
	@CreationTimestamp
	private LocalDateTime createdOn;

}
