package com.xs.dormTest.dao;

import com.xs.dormTest.bean.Visiter;
import com.xs.dormTest.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisiterDaoImpl implements VisiterDao{
    @Override
    public List<Visiter> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select * from visiter_info order by time desc";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            List<Visiter> visiters = new ArrayList<>();
            while (rs.next()) {
                Visiter visiter = new Visiter() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                visiter.setId(rs.getInt("id"));
                visiter.setName(rs.getString("name"));
                visiter.setReason(rs.getString("reason"));
                visiter.setTel(rs.getString("tel"));
                visiter.setTime(rs.getTimestamp("time"));

                visiters.add(visiter) ;
            }
            return visiters ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Visiter> findByTime(String date) {
        Connection connection = ConnectionFactory.getConnection();
//        date = date ;
        try {
            String sql = "select * from visiter_info where time like ? '%'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, date);

            ResultSet rs = preparedStatement.executeQuery();
            List<Visiter> visiters = new ArrayList<>();
            while (rs.next()) {
                Visiter visiter = new Visiter() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                visiter.setId(rs.getInt("id"));
                visiter.setName(rs.getString("name"));
                visiter.setReason(rs.getString("reason"));
                visiter.setTel(rs.getString("tel"));
                visiter.setTime(rs.getTimestamp("time"));

                visiters.add(visiter) ;
            }
            return visiters ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addVisiter(Visiter visiter) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "insert into visiter_info (name, tel , reason , time) " +
                    "values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, visiter.getName());
            preparedStatement.setObject(2, visiter.getTel());
            preparedStatement.setObject(3, visiter.getReason());
            preparedStatement.setObject(4, visiter.getTime());

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVisiter(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "delete from visiter_info where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, id);

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
