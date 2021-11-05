package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.dao.DormBuildDao;
import com.xs.dormTest.dao.DormBuildDaoImpl;

public class DormBuildServiceImpl implements DormBuildService {

	private DormBuildDao dormBuildDao = new DormBuildDaoImpl( ) ;
	/**
	 * 根据名字查询信息并返回
	 */
	@Override
	public DormBuild findByName(Integer name) {
		// TODO Auto-generated method stub
		return dormBuildDao.findByName(name);
	}
	/**
	 * 保存信息到数据库
	 */
	@Override
	public void save(DormBuild build) {
		// TODO Auto-generated method stub
		dormBuildDao.save(build) ;
		
	}
	/**
	 * 将查询到的信息保存到列表中
	 */
	@Override
	public List<DormBuild> find() {
		// TODO Auto-generated method stub
		return dormBuildDao.find();
	}
	/**
	 * 点击右侧搜索按钮实现查询某个宿舍楼信息
	 */
	@Override
	public DormBuild findById(Integer id) {
		// TODO Auto-generated method stub
		return dormBuildDao.findById(id);
	}
	/**
	 * 更新宿舍楼信息
	 */
	@Override
	public void update(DormBuild dormBuild) {
		// TODO Auto-generated method stub
		dormBuildDao.update(dormBuild) ;
	}
	/**
	 * 获取所有学生信息
	 */
	@Override
	public List<DormBuild> findAll() {
		// TODO Auto-generated method stub
		return dormBuildDao.findAll();
	}
	/**
	 * 获取指定宿舍楼的学生信息
	 */
	@Override
	public List<DormBuild> findByManager(Integer dormBuildId) {
		// TODO Auto-generated method stub
		return dormBuildDao.findByManager(dormBuildId);
	}

}
