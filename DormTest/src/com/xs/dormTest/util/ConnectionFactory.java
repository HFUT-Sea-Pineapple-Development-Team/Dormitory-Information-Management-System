package com.xs.dormTest.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	//获取数据源 自动读取配置文件
	private static DataSource datasource = new ComboPooledDataSource();
	
	//获取连接
	public static Connection getConnection() {
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
