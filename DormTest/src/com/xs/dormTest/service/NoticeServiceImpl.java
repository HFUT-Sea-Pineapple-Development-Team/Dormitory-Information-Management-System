package com.xs.dormTest.service;

import com.xs.dormTest.bean.Notice;
import com.xs.dormTest.dao.NoticeDao;
import com.xs.dormTest.dao.NoticeDaoImpl;

import java.sql.Date;
import java.util.List;

public class NoticeServiceImpl implements NoticeService{
    private NoticeDao noticeDao = new NoticeDaoImpl();
    @Override
    public void insertNotice(String title, String tel, String content, Date time, String stu_code) {
        noticeDao.insertNotice(title , tel , content , time,stu_code) ;
    }

    @Override
    public List<Notice> findByStuId(String stu_code) {
        return noticeDao.findByStuId(stu_code);
    }

    @Override
    public Notice findById(int id) {
        return noticeDao.findById(id);
    }

    @Override
    public void deleteNotice(int id) {
        noticeDao.deleteNotice(id) ;
    }

    @Override
    public List<Notice> findByBuildId(int dormBuildId) {
        return noticeDao.findByBuildId(dormBuildId);
    }

    @Override
    public void updateCounts(int id) {
        noticeDao.updateCounts(id) ;
    }

    @Override
    public List<Notice> findByDate(Date date) {
        return noticeDao.findByDate(date);
    }

    @Override
    public List<Notice> findByTime(Date date) {
        return noticeDao.findByTime(date);
    }
}
