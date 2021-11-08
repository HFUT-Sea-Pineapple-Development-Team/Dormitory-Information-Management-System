package com.xs.dormTest.dao;

import java.util.List;

import com.xs.dormTest.bean.Hygiene;

public interface HygieneDao {

	List<Hygiene> findRoomHygiene(String sql);

	Hygiene findByRoomId(Integer buildId, Integer roomId);

	void saveHygiene(Hygiene hygieneUpdate);


}
