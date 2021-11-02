package com.xs.dormTest.bean;

public class DormBuild {
	private int id;
	private String dormBuildName;
	private String detail;
	public DormBuild() {
		super();
	}
	public DormBuild(int id, String dormBuildName, String detail) {
		super();
		this.id = id;
		this.dormBuildName = dormBuildName;
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDormBuildName() {
		return dormBuildName;
	}
	public void setDormBuildName(String dormBuildName) {
		this.dormBuildName = dormBuildName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "DormBuild [id=" + id + ", dormBuildName=" + dormBuildName + ", detail=" + detail + "]";
	}
	
}
