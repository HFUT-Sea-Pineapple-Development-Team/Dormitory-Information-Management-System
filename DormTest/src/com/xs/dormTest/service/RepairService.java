package com.xs.dormTest.service;

import com.xs.dormTest.bean.RepairInfo;

import java.util.List;

public interface RepairService {
    List<RepairInfo> findById(int build_id , int room_id);

    void addRepair(RepairInfo repairInfo);

    void updateIdea(RepairInfo repairInfo);

    void deleteRepair(int id);

}
