package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.Hygiene;

public interface HygieneService {

	List<Hygiene> findRoomHygiene(Integer buildId, String keyword);

	Hygiene findByRoomId(Integer buildId, Integer roomId);

	void saveHygiene(Hygiene hygieneUpdate);

}
