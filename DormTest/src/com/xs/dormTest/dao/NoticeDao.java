package com.xs.dormTest.dao;

import com.xs.dormTest.bean.Notice;

import java.sql.Date;
import java.util.List;

public interface NoticeDao {
    void insertNotice(String title, String tel, String content, Date time,String stu_code);

    List<Notice> findByStuId(String stu_code);

    Notice findById(int id);

    void deleteNotice(int id);

    List<Notice> findByBuildId(int dormBuildId);

    void updateCounts(int id);

    List<Notice> findByDate(Date date);

    List<Notice> findByTime(Date date);
}
