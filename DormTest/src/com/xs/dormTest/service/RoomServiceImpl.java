package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.Hygiene;
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
	public List<Room> findByBuildId(Integer buildToSelect, String keyword) {
		// 进行拼接
		StringBuffer sql = new StringBuffer();
		// 不管怎么样，都是从寝室信息表中查询信息
		sql.append("select * from room_info where ");
		
		//先确定寝室楼号
		sql.append(" build_num = '"+buildToSelect+"'");
		//当keyword不为空时，说明为搜索框中有值时进行查询，需要进行sql拼接
		if (keyword != null && !keyword.equals("")) {
			//根据寝室号查询
			sql.append(" and room_id = '"+keyword.trim()+"'");
		}
		
		List<Room> rooms = roomDao.findByBuildId(sql.toString());
		return rooms;
	}
	@Override
	public Room findByBuildAndRoom(Integer buildId, Integer roomId) {
		// TODO Auto-generated method stub
		return roomDao.findByBuildAndRoom(buildId,roomId);
	}
	@Override
	public void saveRoom(Room roomsave) {
		roomDao.saveRoom(roomsave);
		
	}
	@Override
	public void deleteRoom(Room roomExist) {
		roomDao.deleteRoom(roomExist);
		
	}

}
