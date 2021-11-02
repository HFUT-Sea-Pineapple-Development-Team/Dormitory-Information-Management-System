package com.xs.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.util.ConnectionFactory;

public class DormBuildDaoImpl implements DormBuildDao {
	/**
	 * 查询是否已存在该名称的宿舍楼
	 */
	@Override
	public DormBuild findByName(String name) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from dormbuild where dormBuildName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setString(1, name);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			while (rs.next()) {
				DormBuild build = new DormBuild() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				build.setId(rs.getInt("id"));
				build.setDormBuildName(rs.getString("dormBuildName")) ;
				build.setDetail(rs.getString("detail"));
				
				return build;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 保存宿舍楼信息到数据库中
	 */
	@Override
	public void save(DormBuild build) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "insert into dormbuild(dormBuildName , detail) values (? , ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setString(1, build.getDormBuildName());
			preparedStatement.setString(2, build.getDetail());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 查询宿舍楼信息并展示到List中
	 */
	@Override
	public List<DormBuild> find() {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from dormbuild ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			List<DormBuild> builds = new ArrayList<DormBuild>();
			while (rs.next()) {
				DormBuild build = new DormBuild() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				build.setId(rs.getInt("id"));
				build.setDormBuildName(rs.getString("dormBuildName")) ;
				build.setDetail(rs.getString("detail"));
				
				builds.add(build) ;
			}
			return builds ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 点击右侧按钮根据Id进行搜索宿舍楼信息
	 */
	@Override
	public DormBuild findById(Integer id) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "select * from dormbuild where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			List<DormBuild> builds = new ArrayList<DormBuild>();
			while (rs.next()) {
				DormBuild build = new DormBuild() ;
				//每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
				//就get什么类型
				build.setId(rs.getInt("id"));
				build.setDormBuildName(rs.getString("dormBuildName")) ;
				build.setDetail(rs.getString("detail"));
				
				return build ;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 更新宿舍楼信息
	 */
	@Override
	public void update(DormBuild build) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "update dormbuild set dormBuildName = ? , detail = ? where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//索引从1开始
			preparedStatement.setString(1, build.getDormBuildName());
			preparedStatement.setString(2, build.getDetail());
			preparedStatement.setInt(3, build.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
