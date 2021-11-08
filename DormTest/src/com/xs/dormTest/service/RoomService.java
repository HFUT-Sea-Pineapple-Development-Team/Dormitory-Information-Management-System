package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.Room;

public interface RoomService {

	List<Room> findByAll();

	List<Room> findByBuildId(Integer buildToSelect);

}
