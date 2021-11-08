package com.xs.dormTest.service;

import com.xs.dormTest.bean.Visiter;
import com.xs.dormTest.dao.VisiterDao;
import com.xs.dormTest.dao.VisiterDaoImpl;

import java.util.List;

public class VisiterServiceImpl implements VisiterService{
    private VisiterDao visiterDao = new VisiterDaoImpl() ;
    @Override
    public List<Visiter> findAll() {
        return visiterDao.findAll();
    }

    @Override
    public List<Visiter> findByTime(String date) {
        return visiterDao.findByTime(date) ;
    }

    @Override
    public void addVisiter(Visiter visiter) {
        visiterDao.addVisiter(visiter) ;
    }

    @Override
    public void deleteVisiter(int id) {
        visiterDao.deleteVisiter(id);
     }
}
