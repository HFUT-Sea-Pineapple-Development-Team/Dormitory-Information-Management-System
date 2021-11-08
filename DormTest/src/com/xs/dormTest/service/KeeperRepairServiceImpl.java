package com.xs.dormTest.service;

import com.xs.dormTest.bean.RepairInfo;
import com.xs.dormTest.dao.KeeperRepairDao;
import com.xs.dormTest.dao.KeeperRepairDaoImpl;

import java.sql.Date;
import java.util.List;

public class KeeperRepairServiceImpl implements KeeperRepairService{
    private KeeperRepairDao keeperRepairDao = new KeeperRepairDaoImpl();
    @Override
    public List<RepairInfo> findByBuildId(int dormBuildId) {
        return keeperRepairDao.findByBuildId(dormBuildId);
    }

    @Override
    public void saveReport(String is_finished, String repair_report, int id, Date repair_time) {
        keeperRepairDao.saveReport(is_finished, repair_report,id,repair_time) ;
    }

    @Override
    public RepairInfo findById(int id) {
        return keeperRepairDao.findById(id);
    }

    @Override
    public List<RepairInfo> findByRoomId(int room_id) {
        return keeperRepairDao.findByRoomId(room_id);
    }
}
