package com.xs.dormTest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Statement;
import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.bean.UserAndRoom;
import com.xs.dormTest.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByNameAndPass(String stu_code, String password) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from user where stu_code = ? and password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setString(1, stu_code);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRoleId(rs.getInt("roleId"));
				user.setSex(rs.getInt("sex"));
				user.setStu_code(rs.getString("stu_code"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoomId(rs.getInt("roomId"));
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据学号来查询
	 */
	@Override
	public User findByStuCode(String userName) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from user where stu_code = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setString(1, userName);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getInt("sex"));
				user.setStu_code(rs.getString("stu_code"));
				user.setMajor(rs.getString("major"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoomId(rs.getInt("roomId"));
				user.setClassName(rs.getInt("class"));
				user.setTel(rs.getString("tel"));
				user.setLeaveSchool(rs.getInt("leaveSchool"));
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存学生信息(宿舍分配情况)
	 */
	@Override
	public void saveStudent(User user) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "insert into user(name,password,stu_code,sex,major,dormBuildId,roomId,tel,class) value(?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getStu_code());
			preparedStatement.setInt(4, user.getSex());
			preparedStatement.setString(5, user.getMajor());
			preparedStatement.setInt(6, user.getDormBuildId());
			preparedStatement.setInt(7, user.getRoomId());
			preparedStatement.setString(8, user.getTel());
			preparedStatement.setInt(9, user.getClassName());
			
			preparedStatement.executeUpdate();
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 显示学生信息
	 */
	@Override
	public List<User> findStudent(String sql) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			List<User> users = new ArrayList<User>();
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getInt("sex"));
				user.setStu_code(rs.getString("stu_code"));
				user.setMajor(rs.getString("major"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoomId(rs.getInt("roomId"));
				user.setClassName(rs.getInt("class"));
				user.setTel(rs.getString("tel"));
				user.setLeaveSchool(rs.getInt("leaveSchool"));
				
				DormBuild build = new DormBuild();
				build.setDormBuildName(rs.getInt("buildName"));
				user.setBuild(build);
				
				users.add(user) ;
			}
			return users;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer findTotalNum(String sql) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findById(int id) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from user where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getInt("sex"));
				user.setStu_code(rs.getString("stu_code"));
				user.setMajor(rs.getString("major"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoomId(rs.getInt("roomId"));
				user.setClassName(rs.getInt("class"));
				user.setTel(rs.getString("tel"));
				user.setLeaveSchool(rs.getInt("leaveSchool"));
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新学生信息
	 */
	@Override
	public void updateStudent(User studentUpdate) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "Update user set name = ?,password = ?,stu_code = ?,sex = ?,major = ?,dormBuildId = ?,roomId = ?,tel = ?,class = ?,leaveSchool = ? where id = ?";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setString(1, studentUpdate.getName());
			preparedStatement.setString(2, studentUpdate.getPassword());
			preparedStatement.setString(3, studentUpdate.getStu_code());
			preparedStatement.setInt(4, studentUpdate.getSex());
			preparedStatement.setString(5, studentUpdate.getMajor());
			preparedStatement.setInt(6, studentUpdate.getDormBuildId());
			preparedStatement.setInt(7, studentUpdate.getRoomId());
			preparedStatement.setString(8, studentUpdate.getTel());
			preparedStatement.setInt(9, studentUpdate.getClassName());
			preparedStatement.setInt(10, studentUpdate.getLeaveSchool());
			preparedStatement.setInt(11, studentUpdate.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除学生信息
	 */
	@Override
	public void deleteStudent(User userDelete) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "Delete from user where id = ?";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setInt(1, userDelete.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 返回学生房间信息
	 */
	@Override
	public UserAndRoom findByRoomId(Integer studentId) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from userandroom where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, studentId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				UserAndRoom userAndRoom = new UserAndRoom ();
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				userAndRoom.setId(rs.getInt("id"));
				userAndRoom.setIs_good(rs.getInt("is_good"));
				userAndRoom.setRemain_water_charge(rs.getFloat("remain_water_charge"));
				userAndRoom.setRemain_elec_charge(rs.getFloat("remain_elec_charge"));
				
				return userAndRoom;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertMuch(List<User> stus) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		//调用存储过程
		CallableStatement cs = null;
		cs = connection.prepareCall("{call insertAll(?,?,?,?,?,?,?,?)}");
		for (int i = 0; i < stus.size(); i++) {
			String stu_code = stus.get(i).getStu_code();
			String name = stus.get(i).getName();
			int sex = stus.get(i).getSex() ;
			String tel = stus.get(i).getTel();
			int build_id = stus.get(i).getDormBuildId() ;
			int room_id = stus.get(i).getRoomId();
			String major = stus.get(i).getMajor();
			int Class = stus.get(i).getClassName() ;

			try {

//				cs = connection.prepareCall("call insertAll"+"("+ stu_code + name + sex + tel
//						+ build_id + room_id + major + Class +")");
				cs.setObject(1 ,stu_code);
				cs.setObject(2 ,name);
				cs.setObject(3 ,sex);
				cs.setObject(4 ,tel);
				cs.setObject(5 ,build_id);
				cs.setObject(6 ,room_id);
				cs.setObject(7 ,major);
				cs.setObject(8 ,Class);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				cs.execute() ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

}
