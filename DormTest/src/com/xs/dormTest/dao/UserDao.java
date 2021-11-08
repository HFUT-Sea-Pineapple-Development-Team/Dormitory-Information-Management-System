package com.xs.dormTest.dao;

import java.sql.SQLException;
import java.util.List;

import com.xs.dormTest.bean.User;
import com.xs.dormTest.bean.UserAndRoom;

public interface UserDao {

	User findByNameAndPass(String stu_code, String password);

	User findByStuCode(String userName);

	void saveStudent(User user);

	List<User> findStudent(String sql);

	Integer findTotalNum(String sql);

	User findById(int id);

	void updateStudent(User studentUpdate);

	void deleteStudent(User userDelete);

	UserAndRoom findByRoomId(Integer studentId);
	List<User> findByBuildAndRoom(Integer buildId, Integer roomId);

	List<User> findNotRoom(Integer build_id);

	void addUser(User userAdd);
	void updateRoom(Integer id);
	List<User> findManager();
	void saveManager(User manager);
	void updateManager(User manager);
    void insertMuch(List<User> stus) throws SQLException;
}
