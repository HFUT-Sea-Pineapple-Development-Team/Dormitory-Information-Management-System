package com.xs.dormTest.dao;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.RepairInfo;
import com.xs.dormTest.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDaoImpl implements RepairDao{
    @Override
    public List<RepairInfo> findById(int build_id, int room_id) {
        // TODO Auto-generated method stub
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "select id,name,report_time,reason,repair_time,idea from repair_info where build_id = ? and room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, build_id);
            preparedStatement.setObject(2, room_id);
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
                repairInfo.setRepair_time(rs.getDate("repair_time"));
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

    @Override
    public void addRepair(RepairInfo repairInfo) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "insert into repair_info (stu_id, name , build_id , room_id , report_time, reason , tel) " +
                    "values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, repairInfo.getStu_id());
            preparedStatement.setObject(2, repairInfo.getName());
            preparedStatement.setObject(3, repairInfo.getBuild_id());
            preparedStatement.setObject(4, repairInfo.getRoom_id());
            preparedStatement.setObject(5, repairInfo.getReport_time());
            preparedStatement.setObject(6, repairInfo.getReason());
            preparedStatement.setObject(7, repairInfo.getTel());

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void updateIdea(RepairInfo repairInfo) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "update repair_info set idea = ?, is_satisfied = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //索引从1开始
            preparedStatement.setObject(1, repairInfo.getIdea());
            preparedStatement.setObject(2, repairInfo.getIs_satisfied());
            preparedStatement.setObject(3, repairInfo.getId());

            preparedStatement.execute() ;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRepair(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            String sql = "delete from repair_info where id = ?";
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
