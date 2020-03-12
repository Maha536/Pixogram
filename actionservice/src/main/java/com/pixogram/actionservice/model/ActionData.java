package com.pixogram.actionservice.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActionData {
	private Integer id;
	private Integer mediaId;
	private Boolean status;
	private Integer userId;
	
}
