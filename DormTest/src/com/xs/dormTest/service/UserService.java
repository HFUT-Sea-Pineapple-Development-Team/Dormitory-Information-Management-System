package com.xs.dormTest.service;

import java.sql.SQLException;
import java.util.List;

import com.xs.dormTest.bean.User;
import com.xs.dormTest.bean.UserAndRoom;
import com.xs.dormTest.util.PageModel;

public interface UserService {

	User findByNameAndPass(String stu_code, String password);

	User findByStuCode(String userName);

	void saveStudent(User user);

	List<User> findStudent(String buildToSelect, String searchType, String keyword, User user, PageModel pageModel);

	Integer findTotalNum(String buildToSelect, String searchType, String keyword, User user);

	User findById(int id);

	void updateStudent(User studentUpdate);

	void deleteStudent(User userDelete);

	UserAndRoom findByRoomId(Integer studentId);

    void insertMuch(List<User> stus) throws SQLException;
}
