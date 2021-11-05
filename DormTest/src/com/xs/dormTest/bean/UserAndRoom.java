package com.xs.dormTest.bean;

public class UserAndRoom {
	private Integer id;
	private Integer is_good;
	private Float remain_water_charge;
	private Float remain_elec_charge;
	public UserAndRoom() {
		super();
	}
	public UserAndRoom(Integer id, Integer is_good, Float remain_water_charge, Float remain_elec_charge) {
		super();
		this.id = id;
		this.is_good = is_good;
		this.remain_water_charge = remain_water_charge;
		this.remain_elec_charge = remain_elec_charge;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIs_good() {
		return is_good;
	}
	public void setIs_good(Integer is_good) {
		this.is_good = is_good;
	}
	public Float getRemain_water_charge() {
		return remain_water_charge;
	}
	public void setRemain_water_charge(Float remain_water_charge) {
		this.remain_water_charge = remain_water_charge;
	}
	public Float getRemain_elec_charge() {
		return remain_elec_charge;
	}
	public void setRemain_elec_charge(Float remain_elec_charge) {
		this.remain_elec_charge = remain_elec_charge;
	}
	@Override
	public String toString() {
		return "UserAndRoom [id=" + id + ", is_good=" + is_good + ", remain_water_charge=" + remain_water_charge
				+ ", remain_elec_charge=" + remain_elec_charge + "]";
	}
	
	
}
