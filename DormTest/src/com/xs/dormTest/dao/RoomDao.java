package com.xs.dormTest.dao;

import java.util.List;

import com.xs.dormTest.bean.Room;

public interface RoomDao {

	List<Room> findByAll();

	List<Room> findByBuildId(Integer buildToSelect);


}
