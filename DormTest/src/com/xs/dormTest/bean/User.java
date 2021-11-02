package com.xs.dormTest.bean;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Integer roleId;
	private String stu_code;
	private Integer sex;
	public User() {
		super();
	}
	public User(Integer id, String name, String password, Integer roleId, String stu_code, Integer sex) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.roleId = roleId;
		this.stu_code = stu_code;
		this.sex = sex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getStu_code() {
		return stu_code;
	}
	public void setStu_code(String stu_code) {
		this.stu_code = stu_code;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", roleId=" + roleId + ", stu_code="
				+ stu_code + ", sex=" + sex + "]";
	}
	
	
	
	
	
	
}
