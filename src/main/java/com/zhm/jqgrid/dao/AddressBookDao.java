package com.zhm.jqgrid.dao;

import java.util.List;

import com.zhm.jqgrid.model.AddressBook;

public interface AddressBookDao {

	Integer findNumsByCond(String cond);

	List<AddressBook> findByCond(int page, int rows, String sidx, String sord,
			String cond);

	void insertData(AddressBook book);


	void updateData(AddressBook dbData);

	void delByid(Integer id);

}
