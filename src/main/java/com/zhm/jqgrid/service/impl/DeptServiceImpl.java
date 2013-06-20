package com.zhm.jqgrid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhm.jqgrid.dao.DeptDao;
import com.zhm.jqgrid.model.Department;
import com.zhm.jqgrid.service.DeptService;
@Service("deptService")
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao deptDao;

	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return deptDao.findAll();
	}
	
}
