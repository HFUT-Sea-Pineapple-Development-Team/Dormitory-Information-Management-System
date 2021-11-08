package com.xs.dormTest.service;

import java.sql.SQLException;
import java.util.List;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.bean.UserAndRoom;
import com.xs.dormTest.dao.DormBuildDao;
import com.xs.dormTest.dao.DormBuildDaoImpl;
import com.xs.dormTest.dao.UserDao;
import com.xs.dormTest.dao.UserDaoImpl;
import com.xs.dormTest.util.PageModel;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User findByNameAndPass(String stu_code, String password) {
		
		return userDao.findByNameAndPass(stu_code,password);
	}
	@Override
	public User findByStuCode(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByStuCode(userName);
	}
	@Override
	public void saveStudent(User user) {
		// TODO Auto-generated method stub
		userDao.saveStudent(user);
	}
	@Override
	public List<User> findStudent(String buildToSelect, String searchType, String keyword, User user, PageModel pageModel) {
		// 进行拼接
		StringBuffer sql = new StringBuffer();
		// 不管当前用户角色怎么样，查询的都是学生，所有role_id=2
		sql.append("select user.*,dormbuild.dormBuildName buildName,room_info.room_id roomName from user left join dormbuild on dormbuild.id = user.dormBuildId left join room_info on room_info.id = user.roomId where roleId = 2 ");
		if (keyword != null && !keyword.equals("") && "name".equals(searchType)) {
			//根据姓名查询
			sql.append(" and user.name like '%"+keyword.trim()+"%'");
		}else if (keyword  != null && !keyword.equals("") && "stuCode".equals(searchType)) {
			//根据学号查询
			sql.append(" and user.stu_code = '"+keyword.trim()+"'");
		}else if (keyword  != null && !keyword.equals("") && "dorm".equals(searchType)) {
			//根据寝室号查询
			sql.append(" and room_info.room_id = '"+keyword.trim()+"'");
		}else if (keyword  != null && !keyword.equals("") && "sex".equals(searchType)) {
			//根据性别查询
			if (keyword.equals("男"))
				keyword = "1";
			else if (keyword.equals("女"))
				keyword = "0";
			sql.append(" and user.sex = '"+keyword.trim()+"'");
		}

		
		//若当前用户为宿舍管理员，则查询其管理的宿舍楼id
		if (user.getRoleId().equals(1)) {
			sql.append(" and user.dormBuildId = ");
			sql.append(user.getDormBuildId());
		}else {
			//若当前用户为超级管理员，可以进行选择
			if (buildToSelect != null && !buildToSelect.equals("")) {
				//根据宿舍楼id查询
				sql.append(" and user.dormBuildId = '"+buildToSelect+"'");
			} 
		}
		sql.append(" limit " + pageModel.getStartNum() + " , " + pageModel.getPageSize());
		
		List<User> students = userDao.findStudent(sql.toString());
		return students;
	}
	
	@Override
	public Integer findTotalNum(String buildToSelect, String searchType, String keyword, User user) {
		// 进行拼接
		StringBuffer sql = new StringBuffer();
		// 不管当前用户角色怎么样，查询的都是学生，所有role_id=2
		sql.append("select count(*) from user left join dormbuild on dormbuild.id = user.dormBuildId where roleId = 2 ");
		
		if (keyword != null && !keyword.equals("") && "name".equals(searchType)) {
			//根据姓名查询
			sql.append(" and user.name like '%"+keyword.trim()+"%'");
		}else if (keyword  != null && !keyword.equals("") && "stuCode".equals(searchType)) {
			//根据学号查询
			sql.append(" and user.stu_code = '"+keyword.trim()+"'");
		}else if (keyword  != null && !keyword.equals("") && "dorm".equals(searchType)) {
			//根据寝室号查询
			sql.append(" and user.roomId = '"+keyword.trim()+"'");
		}else if (keyword  != null && !keyword.equals("") && "sex".equals(searchType)) {
			//根据性别查询
			if (keyword.equals("男"))
				keyword = "1";
			else if (keyword.equals("女"))
				keyword = "0";
			sql.append(" and user.sex = '"+keyword.trim()+"'");
		}

		
		//若当前用户为宿舍管理员，则查询其管理的宿舍楼id
		if (user.getRoleId().equals(1)) {
			sql.append(" and user.dormBuildId = ");
			sql.append(user.getDormBuildId());
		}else {
			//若当前用户为超级管理员，可以进行选择
			if (buildToSelect != null && !buildToSelect.equals("")) {
				//根据宿舍楼id查询
				sql.append(" and user.dormBuildId = '"+buildToSelect+"'");
			} 
		}
		
		return userDao.findTotalNum(sql.toString());
	}
	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}
	@Override
	public void updateStudent(User studentUpdate) {
		userDao.updateStudent(studentUpdate);
	}
	@Override
	public void deleteStudent(User userDelete) {
		userDao.deleteStudent(userDelete);
	}
	@Override
	public UserAndRoom findByRoomId(Integer studentId) {
		return userDao.findByRoomId(studentId);
	}
	@Override
	public List<User> findByBuildAndRoom(Integer BuildId, Integer RoomId) {
		return userDao.findByBuildAndRoom(BuildId, RoomId);
	}
	@Override
	public List<User> findNotRoom(Integer build_id) {
		return userDao.findNotRoom(build_id);
	}
	@Override
	public void addUser(User userAdd) {
		userDao.addUser(userAdd);	
	}
	@Override
	public void updateRoom(Integer id) {
		userDao.updateRoom(id);
	}
	@Override
	public List<User> findManager() {
		return userDao.findManager();
	}
	@Override
	public void saveManager(User manager) {
		userDao.saveManager(manager);
	}
	@Override
	public void updateManager(User manager) {
		userDao.updateManager(manager);
		
	}

	@Override
	public void insertMuch(List<User> stus) throws SQLException {
		userDao.insertMuch(stus) ;
	}

}
