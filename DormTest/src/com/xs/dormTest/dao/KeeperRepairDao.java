package com.xs.dormTest.dao;

import com.xs.dormTest.bean.RepairInfo;

import java.sql.Date;
import java.util.List;

public interface KeeperRepairDao {
     List<RepairInfo> findByBuildId(int dormBuildId) ;

     void saveReport(String is_finished, String repair_report, int id, Date repair_time);

     RepairInfo findById(int id);

     List<RepairInfo> findByRoomId(int room_id);
}
