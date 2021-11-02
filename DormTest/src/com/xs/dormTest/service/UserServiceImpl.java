package com.xs.dormTest.service;

import com.xs.dormTest.bean.User;
import com.xs.dormTest.dao.UserDao;
import com.xs.dormTest.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User findByNameAndPass(String stu_code, String password) {
		
		return userDao.findByNameAndPass(stu_code,password);
	}

}
