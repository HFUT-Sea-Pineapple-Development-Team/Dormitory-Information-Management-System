package com.xs.dormTest.dao;

import com.xs.dormTest.bean.User;

public interface UserDao {

	User findByNameAndPass(String stu_code, String password);

}
