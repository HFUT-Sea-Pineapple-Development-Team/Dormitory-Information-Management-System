package com.xs.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.Hygiene;
import com.xs.dormTest.bean.Room;
import com.xs.dormTest.util.ConnectionFactory;

/**
 * 获取指定宿舍楼的卫生情况
 */
public class HygieneDaoImpl implements HygieneDao {

	/**
	 * 根据宿舍楼号和关键字来查询
	 */
	@Override
	public List<Hygiene> findRoomHygiene(String sql) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			List<Hygiene> hygienes = new ArrayList<Hygiene>();
			while (rs.next()) {
				Hygiene hygiene = new Hygiene() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				hygiene.setRoom_id(rs.getInt("room_id"));
				hygiene.setGrade_18(rs.getFloat("grade_18")) ;
				hygiene.setGrade_19(rs.getFloat("grade_19")) ;
				hygiene.setGrade_20(rs.getFloat("grade_20")) ;
				hygiene.setGrade_21(rs.getFloat("grade_21")) ;
				
				Room room = new Room();
				room.setRoom_id(rs.getInt("roomid"));
				hygiene.setRoom(room);
				
				hygienes.add(hygiene);
			}
			return hygienes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据宿舍楼号和房间号来查询
	 */
	@Override
	public Hygiene findByRoomId(Integer buildId, Integer roomId) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select hygiene_info.*,room_info.room_id roomid from hygiene_info left join room_info on room_info.id = hygiene_info.room_id where build_id = ? and hygiene_info.room_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, buildId);
			preparedStatement.setInt(2, roomId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				Hygiene hygiene = new Hygiene() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				hygiene.setBuild_id(rs.getInt("build_id"));
				hygiene.setRoom_id(rs.getInt("room_id"));
				hygiene.setGrade_18(rs.getFloat("grade_18")) ;
				hygiene.setGrade_19(rs.getFloat("grade_19")) ;
				hygiene.setGrade_20(rs.getFloat("grade_20")) ;
				hygiene.setGrade_21(rs.getFloat("grade_21")) ;
				
				Room room = new Room();
				room.setRoom_id(rs.getInt("roomid"));
				hygiene.setRoom(room);
				
				return hygiene;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存录入的卫生数据至数据库中
	 */
	@Override
	public void saveHygiene(Hygiene hygieneUpdate) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "Update hygiene_info set grade_18 = ?, grade_19 = ?, grade_20 = ?, grade_21 = ? where build_id = ? and room_id = ?";
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setFloat(1, hygieneUpdate.getGrade_18());
			preparedStatement.setFloat(2, hygieneUpdate.getGrade_19());
			preparedStatement.setFloat(3, hygieneUpdate.getGrade_20());
			preparedStatement.setFloat(4, hygieneUpdate.getGrade_21());
			preparedStatement.setInt(5, hygieneUpdate.getBuild_id());
			preparedStatement.setInt(6, hygieneUpdate.getRoom_id());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
