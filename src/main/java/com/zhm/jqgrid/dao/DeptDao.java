package com.zhm.jqgrid.dao;

import java.util.List;

import com.zhm.jqgrid.model.Department;

public interface DeptDao {

	List<Department> findAll();

	Department findById(String deptid);
	
}
