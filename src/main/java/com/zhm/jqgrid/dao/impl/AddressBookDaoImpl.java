package com.zhm.jqgrid.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.zhm.jqgrid.dao.AddressBookDao;
import com.zhm.jqgrid.model.AddressBook;
@Repository("addressBookDao")
public class AddressBookDaoImpl implements AddressBookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer findNumsByCond(String cond) {
		// TODO Auto-generated method stub
		String sql = "select count(id) from address_book where 1=1 "+ cond;
		Long result = jdbcTemplate.queryForLong(sql);
		return result.intValue();
	}

	public List<AddressBook> findByCond(int page, int rows, String sidx,
			String sord, String cond) {
		// TODO Auto-generated method stub
		String sql = "select * from address_book where 1=1 " + cond +" order by " + sidx + " " + sord +" limit ?,?";
		Object[] params = new Object[2];
		params[0]=(page-1)*rows;
		params[1]=rows;
		final List<AddressBook> results = Lists.newArrayList();
		jdbcTemplate.query(sql, params, new RowCallbackHandler (){
			public void processRow(ResultSet arg0) throws SQLException {
				// TODO Auto-generated method stub
				AddressBook tmp = new AddressBook();
				tmp.setId(arg0.getInt(1));
				tmp.setUsername(arg0.getString(2));
				tmp.setEmail(arg0.getString(3));
				tmp.setMobile(arg0.getString(4));
				tmp.setDeptid(arg0.getString(5));
				tmp.setEntry_date(arg0.getTimestamp(6));
				results.add(tmp);
			}
		});
		return results;
	}

	public void insertData(AddressBook book) {
		// TODO Auto-generated method stub
		String sql = "insert into address_book(username,email,mobile,deptid,entry_date) values(?,?,?,?,?)";
		jdbcTemplate.update(sql, book.getUsername(),book.getEmail(),book.getMobile(),book.getDeptid(),new Date());
	}

	public void updateData(AddressBook dbData) {
		// TODO Auto-generated method stub
		String sql = "update address_book set username=?,email=?,mobile=? ,deptid=? where id=?";
		jdbcTemplate.update(sql, dbData.getUsername(),dbData.getEmail(),dbData.getMobile(),dbData.getDeptid(),dbData.getId());
	}

	public void delByid(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from address_book where id=?";
		jdbcTemplate.update(sql, id);
	}
	
}
