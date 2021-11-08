package com.xs.dormTest.bean;

public class Hygiene {
	private Integer build_id;
	private Integer room_id;
	private Float grade_18;
	private Float grade_19;
	private Float grade_20;
	private Float grade_21;
	public Hygiene() {
		super();
	}
	public Hygiene(Integer build_id, Integer room_id, Float grade_18, Float grade_19, Float grade_20, Float grade_21) {
		super();
		this.build_id = build_id;
		this.room_id = room_id;
		this.grade_18 = grade_18;
		this.grade_19 = grade_19;
		this.grade_20 = grade_20;
		this.grade_21 = grade_21;
	}
	public Integer getBuild_id() {
		return build_id;
	}
	public void setBuild_id(Integer build_id) {
		this.build_id = build_id;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public Float getGrade_18() {
		return grade_18;
	}
	public void setGrade_18(Float grade_18) {
		this.grade_18 = grade_18;
	}
	public Float getGrade_19() {
		return grade_19;
	}
	public void setGrade_19(Float grade_19) {
		this.grade_19 = grade_19;
	}
	public Float getGrade_20() {
		return grade_20;
	}
	public void setGrade_20(Float grade_20) {
		this.grade_20 = grade_20;
	}
	public Float getGrade_21() {
		return grade_21;
	}
	public void setGrade_21(Float grade_21) {
		this.grade_21 = grade_21;
	}
	@Override
	public String toString() {
		return "Hygiene [build_id=" + build_id + ", room_id=" + room_id + ", grade_18=" + grade_18 + ", grade_19="
				+ grade_19 + ", grade_20=" + grade_20 + ", grade_21=" + grade_21 + "]";
	}
	
	
}
