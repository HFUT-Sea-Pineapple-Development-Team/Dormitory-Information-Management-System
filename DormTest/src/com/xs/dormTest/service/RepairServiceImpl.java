package com.xs.dormTest.service;

import com.xs.dormTest.bean.RepairInfo;
import com.xs.dormTest.dao.RepairDao;
import com.xs.dormTest.dao.RepairDaoImpl;

import java.util.List;

public class RepairServiceImpl implements RepairService{
    private RepairDao repairDao = new RepairDaoImpl() ;
    @Override
    public List<RepairInfo> findById(int build_id , int room_id) {
        return repairDao.findById( build_id , room_id);
    }

    @Override
    public void addRepair(RepairInfo repairInfo) {
        repairDao.addRepair(repairInfo);
    }

    @Override
    public void updateIdea(RepairInfo repairInfo) {
        repairDao.updateIdea(repairInfo) ;
    }

    @Override
    public void deleteRepair(int id) {
        repairDao.deleteRepair(id) ;
    }
}
