package com.xs.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.xs.dormTest.bean.User;
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
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
