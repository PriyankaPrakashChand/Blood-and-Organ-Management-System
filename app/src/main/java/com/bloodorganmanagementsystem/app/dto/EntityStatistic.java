package com.bloodorganmanagementsystem.app.dto;

import lombok.*;

public class EntityStatistic {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	String name;
	Integer value;

	public EntityStatistic(@NonNull String name, @NonNull Integer value) {
		super();
		this.name = name;
		this.value = value;
	}

	public EntityStatistic() {
		super();
		// TODO Auto-generated constructor stub
	}

}
