package com.zhm.jqgrid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhm.jqgrid.dao.AddressBookDao;
import com.zhm.jqgrid.dao.DeptDao;
import com.zhm.jqgrid.model.AddressBook;
import com.zhm.jqgrid.model.Department;
import com.zhm.jqgrid.service.AddressBookService;
@Service("addressBookServiceImpl")
public class AddressBookServiceImpl implements AddressBookService {
	@Autowired
	private AddressBookDao addressBookDao;
	@Autowired
	private DeptDao deptDao;
	public Integer findNumsByCond(String cond) {
		// TODO Auto-generated method stub
		return addressBookDao.findNumsByCond(cond);
	}

	public List<AddressBook> findByCond(int page, int rows, String sidx,
			String sord, String cond) {
		// TODO Auto-generated method stub
		List<AddressBook> results = addressBookDao.findByCond(page,rows,sidx,sord,cond);
		for(AddressBook tmp:results)
		{
			Department dept = deptDao.findById(tmp.getDeptid());
			tmp.setDeptid(dept.getName());
		}
		return results;
	}

	public void insertData(AddressBook book) {
		// TODO Auto-generated method stub
		addressBookDao.insertData(book);
	}

	public AddressBook findById(Integer id) {
		// TODO Auto-generated method stub
		return addressBookDao.findByCond(1, 1, "id", "asc", "and id="+id).get(0);
	}

	public void updateData(AddressBook dbData) {
		// TODO Auto-generated method stub
		addressBookDao.updateData(dbData);
	}

	public void delById(Integer id) {
		// TODO Auto-generated method stub
		addressBookDao.delByid(id);
	}
	
}
