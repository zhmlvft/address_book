package com.zhm.jqgrid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhm.jqgrid.model.Department;
import com.zhm.jqgrid.service.DeptService;

@Controller
public class DeptManager {
	@Autowired
	private DeptService deptService;
	@RequestMapping(value="/deptManager/listAll/getJson")
	public @ResponseBody List<Department> getJson()
	{
		List<Department> depts = deptService.findAll();
		return depts;
	}
}
