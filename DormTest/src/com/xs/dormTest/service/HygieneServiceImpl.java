package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.Hygiene;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.dao.HygieneDao;
import com.xs.dormTest.dao.HygieneDaoImpl;

public class HygieneServiceImpl implements HygieneService {

	private HygieneDao hygieneDao = new HygieneDaoImpl();
	
	@Override
	public List<Hygiene> findRoomHygiene(Integer buildId, String keyword) {

		// 进行拼接
		StringBuffer sql = new StringBuffer();
		// 不管怎么样，都是从卫生表中查询信息
		sql.append("select hygiene_info.*,room_info.room_id roomid from hygiene_info left join room_info on room_info.id = hygiene_info.room_id where ");
		
		//先确定寝室楼号
		sql.append(" build_id = '"+buildId+"'");
		//当keyword不为空时，说明为搜索框中有值时进行查询，需要进行sql拼接
		if (keyword != null && !keyword.equals("")) {
			//根据寝室号查询
			sql.append(" and room_info.room_id = '"+keyword.trim()+"'");
		}
		
		List<Hygiene> hygienes = hygieneDao.findRoomHygiene(sql.toString());
		return hygienes;
	}

	@Override
	public Hygiene findByRoomId(Integer buildId, Integer roomId) {
		return hygieneDao.findByRoomId(buildId, roomId);
	}

	@Override
	public void saveHygiene(Hygiene hygieneUpdate) {
		hygieneDao.saveHygiene(hygieneUpdate);
	}

}
