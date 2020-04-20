package com.example.demo.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("学生类")
public class Student {
	
	@ApiModelProperty("姓名")
	private String name ;
	
	@ApiModelProperty("年龄")
	private int age;
	
	@ApiModelProperty("时间")
	private  Date time ;
	
	private List<String> list;
	
	private Map<String ,String> map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
}
