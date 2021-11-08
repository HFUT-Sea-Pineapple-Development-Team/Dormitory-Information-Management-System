package com.xs.dormTest.service;

import com.xs.dormTest.bean.Notice;

import java.sql.Date;
import java.util.List;

public interface NoticeService {
    void insertNotice(String title, String tel, String content, Date time, String stu_code);

    List<Notice> findByStuId(String stu_code);

    Notice findById(int id);

    void deleteNotice(int id);

    List<Notice> findByBuildId(int dormBuildId);

    void updateCounts(int id);

    List<Notice> findByDate(Date date);

    List<Notice> findByTime(Date date);
}
