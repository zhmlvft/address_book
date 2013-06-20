package com.zhm.jqgrid.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.zhm.jqgrid.dao.DeptDao;
import com.zhm.jqgrid.model.Department;
@Repository("deptDao")
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Department> findAll() {
		// TODO Auto-generated method stub
		String sql = "select * from department";
		final List<Department> results = Lists.newArrayList();
		jdbcTemplate.query(sql,new RowCallbackHandler (){
			public void processRow(ResultSet arg0) throws SQLException {
				// TODO Auto-generated method stub
				Department tmp = new Department();
				tmp.setId(arg0.getInt(1));
				tmp.setName(arg0.getString(2));
				results.add(tmp);
			}
		});
		return results;
	}

	public Department findById(String deptid) {
		// TODO Auto-generated method stub
		String sql = "select * from department where id=?";
		Object[] params = new Object[1];
		params[0]=deptid;
		final Department result = new Department();
		jdbcTemplate.query(sql,params,new RowCallbackHandler (){
			public void processRow(ResultSet arg0) throws SQLException {
				// TODO Auto-generated method stub
				result.setId(arg0.getInt(1));
				result.setName(arg0.getString(2));
			}
		});
		return result;
	}
	
	
	
}
