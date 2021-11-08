package com.xs.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	/**
	 * 当楼号和寝室号确定时，获取居住其中的学生
	 */
	@Override
	public List<User> findByBuildAndRoom(Integer buildId, Integer roomId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user where dormBuildId = ? and roomId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, buildId);
			preparedStatement.setInt(2, roomId);
			
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
				
				users.add(user) ;
			}
			return users;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 已知寝室楼号，获取该寝室楼号中所有未分配寝室的学生
	 */
	@Override
	public List<User> findNotRoom(Integer build_id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user where dormBuildId = ? and roomId is null and roleId = 2";
			preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, build_id);
			
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
				
				users.add(user) ;
			}
			return users;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将学生加入对应寝室
	 */
	@Override
	public void addUser(User userAdd) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "Update user set roomId = ? where id = ?";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setInt(1, userAdd.getRoomId());
			preparedStatement.setInt(2, userAdd.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 将学生的宿舍号置为空
	 */
	@Override
	public void updateRoom(Integer id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "Update user set roomId = null where id = ?";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 找到所有的宿舍管理员
	 */
	@Override
	public List<User> findManager() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "select user.*,dormbuild.dormBuildName buildName from user left join dormbuild on dormbuild.id = user.dormBuildId where roleId = 1";
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
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setTel(rs.getString("tel"));
				
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

	/**
	 * 找到目标管理员
	 */
	@Override
	public void saveManager(User manager) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "insert into user(name,password,stu_code,sex,dormBuildId,tel,roleId) value(?,?,?,?,?,?,1)";
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setString(1, manager.getName());
			preparedStatement.setString(2, manager.getPassword());
			preparedStatement.setString(3, manager.getStu_code());
			preparedStatement.setInt(4, manager.getSex());
			preparedStatement.setInt(5, manager.getDormBuildId());
			preparedStatement.setString(6, manager.getTel());
			
			preparedStatement.executeUpdate();
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 更新宿舍管理员信息
	 */
	@Override
	public void updateManager(User manager) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "Update user set name = ?,password = ?,stu_code = ?,sex = ?,dormBuildId = ?,tel = ? where id = ?";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql); 
			
			preparedStatement.setString(1, manager.getName());
			preparedStatement.setString(2, manager.getPassword());
			preparedStatement.setString(3, manager.getStu_code());
			preparedStatement.setInt(4, manager.getSex());
			preparedStatement.setInt(5, manager.getDormBuildId());
			preparedStatement.setString(6, manager.getTel());

			preparedStatement.setInt(7, manager.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
