package com.zhm.jqgrid.service;

import java.util.List;

import com.zhm.jqgrid.model.AddressBook;


public interface AddressBookService {
	/**
	 * 根据条件查询数据总数
	 * @param cond sql条件
	 * @return
	 */
	Integer findNumsByCond(String cond);
	/**
	 * 
	 * @param page 页数
	 * @param rows 每页数目
	 * @param sidx 排序字段
	 * @param sord asc or desc
	 * @param cond sql条件
	 * @return
	 */
	List<AddressBook> findByCond(int page, int rows, String sidx, String sord,
			String cond);
	/**
	 * 插入数据
	 * @param book
	 */
	void insertData(AddressBook book);
	
	/**
	 * 根据ID找数据
	 * @param id
	 * @return
	 */
	AddressBook findById(Integer id);
	
	/**
	 * 更新数据
	 * @param dbData
	 */
	void updateData(AddressBook dbData);
	
	/**
	 * 删除数据
	 * @param id
	 */
	void delById(Integer id);

}
