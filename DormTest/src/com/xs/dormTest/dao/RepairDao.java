package com.xs.dormTest.dao;

import com.xs.dormTest.bean.RepairInfo;

import java.util.List;

public interface RepairDao {
    List<RepairInfo> findById(int build_id, int room_id);

    void addRepair(RepairInfo repairInfo) ;

    void updateIdea(RepairInfo repairInfo);

    void deleteRepair(int id);
}
