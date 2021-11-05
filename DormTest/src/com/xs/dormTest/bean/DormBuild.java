package com.xs.dormTest.bean;

public class DormBuild {
	private int id;
	private int dormBuildName;
	private String detail;
	private int sex; 
	public DormBuild() {
		super();
	}
	public DormBuild(int id, int dormBuildName, String detail, int sex) {
		super();
		this.id = id;
		this.dormBuildName = dormBuildName;
		this.detail = detail;
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDormBuildName() {
		return dormBuildName;
	}
	public void setDormBuildName(int dormBuildName) {
		this.dormBuildName = dormBuildName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "DormBuild [id=" + id + ", dormBuildName=" + dormBuildName + ", detail=" + detail + ", sex=" + sex + "]";
	}
	
	
}
