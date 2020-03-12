package com.pixogram.actionservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActionCountAndStatus {
	private Integer likes;
	private Integer unlikes;
	private Boolean status;
}
