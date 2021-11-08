package com.xs.dormTest.dao;

import com.xs.dormTest.bean.Notice;
import com.xs.dormTest.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao{
    @Override
    public void insertNotice(String title, String tel, String content, Date time, String stu_code) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "insert into notice (title, tel , content , time , stu_id) " +
                    "values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, title);
            preparedStatement.setObject(2, tel);
            preparedStatement.setObject(3, content);
            preparedStatement.setObject(4, time);
            preparedStatement.setObject(5, stu_code);

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Notice> findByStuId(String stu_code) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select * from notice where stu_id = ? order by time DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, stu_code);

            ResultSet rs = preparedStatement.executeQuery();
            List<Notice> notices = new ArrayList<Notice>();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            while (rs.next()) {
                Notice notice = new Notice() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                notice.setTime(rs.getDate("time"));
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setId(rs.getInt("id"));
                notice.setReceivers_num(rs.getInt("receivers_num"));

                notices.add(notice) ;
            }
            return notices ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Notice findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select * from notice where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            Notice notice = new Notice();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            if (rs.next()) {
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setId(rs.getInt("id"));
                notice.setTel(rs.getString("tel"));
            }
            return notice ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteNotice(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "delete from notice where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, id);

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Notice> findByBuildId(int dormBuildId) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            //连接多表查询
            String sql = "select notice.id ,title , content , time , notice.tel , user.name from user, notice where " +
                    "user.stu_code = notice.stu_id and dormBuildId = ? order by time desc";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, dormBuildId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Notice> notices = new ArrayList<>();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            while (rs.next()) {
                Notice notice = new Notice() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setId(rs.getInt("notice.id"));
                notice.setTel(rs.getString("tel"));
                notice.setTime(rs.getDate("time"));
                notice.setName(rs.getString("user.name"));

                notices.add(notice) ;
            }
            return notices ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCounts(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update notice set receivers_num = receivers_num + 1 where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, id);

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Notice> findByDate(Date date) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select * from notice where time = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, date);

            ResultSet rs = preparedStatement.executeQuery();
            List<Notice> notices = new ArrayList<Notice>();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            while (rs.next()) {
                Notice notice = new Notice() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                notice.setTime(rs.getDate("time"));
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setId(rs.getInt("id"));
                notice.setReceivers_num(rs.getInt("receivers_num"));

                notices.add(notice) ;
            }
            return notices ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notice> findByTime(Date date) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            //连接多表查询
            String sql = "select notice.id ,title , content , time , notice.tel , user.name from user, notice where " +
                    "user.stu_code = notice.stu_id and time = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, date);
            ResultSet rs = preparedStatement.executeQuery();
            List<Notice> notices = new ArrayList<>();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            while (rs.next()) {
                Notice notice = new Notice() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setId(rs.getInt("notice.id"));
                notice.setTel(rs.getString("tel"));
                notice.setTime(rs.getDate("time"));
                notice.setName(rs.getString("user.name"));

                notices.add(notice) ;
            }
            return notices ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
