package com.xs.dormTest.bean;

public class Room {
	private Integer room_id;
	private Integer build_num;
	private Integer person_num;
	public Room() {
		super();
	}
	public Room(Integer room_id, Integer build_num, Integer person_num) {
		super();
		this.room_id = room_id;
		this.build_num = build_num;
		this.person_num = person_num;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public Integer getBuild_num() {
		return build_num;
	}
	public void setBuild_num(Integer build_num) {
		this.build_num = build_num;
	}
	public Integer getPerson_num() {
		return person_num;
	}
	public void setPerson_num(Integer person_num) {
		this.person_num = person_num;
	}
	@Override
	public String toString() {
		return "Room [room_id=" + room_id + ", build_num=" + build_num + ", person_num=" + person_num + "]";
	}
	
	
}
