package com.xs.dormTest.service;

import java.util.List;

import com.xs.dormTest.bean.DormBuild;

public interface DormBuildService {

	DormBuild findByName(Integer name);

	void save(DormBuild build);

	List<DormBuild> find();

	DormBuild findById(Integer id);

	void update(DormBuild dormBuild);

	List<DormBuild> findAll();

	List<DormBuild> findByManager(Integer dormBuildId);




}
