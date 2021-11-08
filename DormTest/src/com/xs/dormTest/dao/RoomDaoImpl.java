package com.xs.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.Room;
import com.xs.dormTest.util.ConnectionFactory;

public class RoomDaoImpl implements RoomDao {

	/**
	 * 查询所有宿舍信息
	 */
	@Override
	public List<Room> findByAll() {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from room_info ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			List<Room> rooms = new ArrayList<Room>();
			while (rs.next()) {
				Room room = new Room() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				room.setRoom_id(rs.getInt("room_id"));
				room.setBuild_num(rs.getInt("build_num")) ;
				room.setPerson_num(rs.getInt("person_num"));
				
				rooms.add(room) ;
			}
			return rooms;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询当前寝室楼的所有寝室信息
	 */
	@Override
	public List<Room> findByBuildId(Integer buildToSelect) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from room_info where build_num = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, buildToSelect);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			List<Room> rooms = new ArrayList<Room>();
			while (rs.next()) {
				Room room = new Room() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				room.setRoom_id(rs.getInt("room_id"));
				room.setBuild_num(rs.getInt("build_num")) ;
				room.setPerson_num(rs.getInt("person_num"));
				
				rooms.add(room) ;
			}
			return rooms;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
