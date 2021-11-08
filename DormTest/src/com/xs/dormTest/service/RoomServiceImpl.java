package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.Room;
import com.xs.dormTest.dao.RoomDao;
import com.xs.dormTest.dao.RoomDaoImpl;

public class RoomServiceImpl implements RoomService {
	private RoomDao roomDao = new RoomDaoImpl();
	@Override
	public List<Room> findByAll() {
		// TODO Auto-generated method stub
		return roomDao.findByAll();
	}
	@Override
	public List<Room> findByBuildId(Integer buildToSelect) {
		// TODO Auto-generated method stub
		return roomDao.findByBuildId(buildToSelect);
	}

}
