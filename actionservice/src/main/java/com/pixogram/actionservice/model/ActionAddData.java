package com.pixogram.actionservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActionAddData {
	private Integer mediaId;
	private Boolean status;
	private Integer userId;
}
