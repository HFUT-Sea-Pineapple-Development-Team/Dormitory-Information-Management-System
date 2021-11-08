package com.xs.dormTest.service;

import com.xs.dormTest.bean.Visiter;

import java.sql.Date;
import java.util.List;

public interface VisiterService {
    List<Visiter> findAll();

    List<Visiter> findByTime(String date);

    void addVisiter(Visiter visiter);

    void deleteVisiter(int id);
}
