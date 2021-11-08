package com.xs.dormTest.bean;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Integer roleId;
	private String stu_code;
	private Integer sex;

	private String major;
	private Integer dormBuildId;
	private Integer roomId;
	private String tel;
	private Integer className;
	private Integer leaveSchool;
	private DormBuild build;


	public User() {
		super();
	}
	public User(Integer id, String name, String password, Integer roleId, String stu_code, Integer sex, String major,
			Integer dormBuildId, Integer roomId, String tel, Integer className, Integer leaveSchool, DormBuild build) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.roleId = roleId;
		this.stu_code = stu_code;
		this.sex = sex;
		this.major = major;
		this.dormBuildId = dormBuildId;
		this.roomId = roomId;
		this.tel = tel;
		this.className = className;
		this.leaveSchool = leaveSchool;
		this.build = build;
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

	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getDormBuildId() {
		return dormBuildId;
	}
	public void setDormBuildId(Integer dormBuildId) {
		this.dormBuildId = dormBuildId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getClassName() {
		return className;
	}
	public void setClassName(Integer className) {
		this.className = className;
	}
	public Integer getLeaveSchool() {
		return leaveSchool;
	}
	public void setLeaveSchool(Integer leaveSchool) {
		this.leaveSchool = leaveSchool;
	}
	public DormBuild getBuild() {
		return build;
	}
	public void setBuild(DormBuild build) {
		this.build = build;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", roleId=" + roleId + ", stu_code="
				+ stu_code + ", sex=" + sex + ", major=" + major + ", dormBuildId=" + dormBuildId + ", roomId=" + roomId
				+ ", tel=" + tel + ", className=" + className + ", leaveSchool=" + leaveSchool + ", build=" + build
				+ "]";
	}
	
}
