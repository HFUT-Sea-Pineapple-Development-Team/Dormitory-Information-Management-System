package com.xs.dormTest.service;

import com.xs.dormTest.bean.User;

public interface UserService {

	User findByNameAndPass(String stu_code, String password);

}
