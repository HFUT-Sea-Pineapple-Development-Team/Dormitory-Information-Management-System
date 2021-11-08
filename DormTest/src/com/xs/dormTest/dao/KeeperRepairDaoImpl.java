package com.xs.dormTest.dao;

import com.xs.dormTest.bean.RepairInfo;
import com.xs.dormTest.bean.Room;
import com.xs.dormTest.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeeperRepairDaoImpl implements KeeperRepairDao{
    @Override
    public List<RepairInfo> findByBuildId(int dormBuildId) {
        Connection connection = ConnectionFactory.getConnection();
        try { 
            String sql = "select repair_info.*,room_info.room_id roomName from repair_info left join room_info on room_info.id = repair_info.room_id where build_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, dormBuildId);
            ResultSet rs = preparedStatement.executeQuery();
            List<RepairInfo> repairInfos = new ArrayList<RepairInfo>();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            while (rs.next()) {
                RepairInfo repairInfo = new RepairInfo() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                repairInfo.setId(rs.getInt("id"));
                repairInfo.setName(rs.getString("name"));
                repairInfo.setReport_time(rs.getDate("report_time"));
                repairInfo.setReason(rs.getString("reason"));
                repairInfo.setRoom_id(rs.getInt("room_id"));
                repairInfo.setTel(rs.getString("tel"));
                repairInfo.setIdea(rs.getString("idea"));

                Room room = new Room();
				room.setRoom_id(rs.getInt("roomName"));
				repairInfo.setRoom(room);
				
                repairInfos.add(repairInfo) ;
            }
            return repairInfos ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveReport(String is_finished, String repair_report, int id, Date repair_time) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update repair_info set is_finished = ? , repair_report = ? , repair_time = ? where id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, is_finished);
            preparedStatement.setObject(2, repair_report);
            preparedStatement.setObject(3, repair_time);
            preparedStatement.setObject(4, id);

            preparedStatement.executeUpdate() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public RepairInfo findById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select repair_time , is_satisfied ,is_finished , repair_report , idea from repair_info where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            RepairInfo repairInfo = new RepairInfo() ;
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据

            //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
            //就get什么类型
            if (rs.next()){
                repairInfo.setRepair_time(rs.getDate("repair_time"));
                repairInfo.setIs_satisfied(rs.getString("is_satisfied"));
                repairInfo.setIs_finished(rs.getString("is_finished"));
                repairInfo.setRepair_report(rs.getString("repair_report"));
                repairInfo.setIdea(rs.getString("idea"));
            }


            return repairInfo ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RepairInfo> findByRoomId(int room_id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select * from repair_info where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, room_id);
            ResultSet rs = preparedStatement.executeQuery();

            List<RepairInfo> repairInfos = new ArrayList<RepairInfo>();
            //因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
            while (rs.next()) {
                RepairInfo repairInfo = new RepairInfo() ;
                //每一行的数据封装在一个实体Bean中，根据字段名获取字段值，注意该字段是什么类型
                //就get什么类型
                repairInfo.setId(rs.getInt("id"));
                repairInfo.setName(rs.getString("name"));
                repairInfo.setReport_time(rs.getDate("report_time"));
                repairInfo.setReason(rs.getString("reason"));
                repairInfo.setRoom_id(rs.getInt("room_id"));
                repairInfo.setTel(rs.getString("tel"));
                repairInfo.setIdea(rs.getString("idea"));

                repairInfos.add(repairInfo) ;
            }
            return repairInfos ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
