package com.zhm.jqgrid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhm.jqgrid.model.AddressBook;
import com.zhm.jqgrid.model.GridResultBean;
import com.zhm.jqgrid.service.AddressBookService;
import com.zhm.jqgrid.utils.Page;
import com.zhm.jqgrid.utils.SearchUtils;

@Controller
public class AddressBookController {
	@Autowired
	private AddressBookService addressBookService;
	@RequestMapping(value="/list")
	public String list()
	{
		return "/list";
	}
	@RequestMapping(value="/listJson")
	public @ResponseBody GridResultBean listJson(int page,int rows,String sidx,String sord,String filters)
	{
		/**
		 * page 当前页数
		 * rows 每页显示的条数
		 * sidx 按哪个字段排序
		 * sord 倒序还是顺序
		 * filters 组合搜索传过来的json条件。需要手工转换成sql条件
		 *
		 */
		//filters转换成sql条件
		String cond = SearchUtils.convertSearchCond(filters);
		Integer totalRecords = addressBookService.findNumsByCond(cond);
		Page pg = new Page(page,rows,totalRecords,5);
		List<AddressBook> books = addressBookService.findByCond(page,rows,sidx,sord,cond);
		GridResultBean result = new GridResultBean();
		result.setPage(1);
		result.setRecords(totalRecords);
		result.setTotal(pg.getTotalPages());
		result.setRows(books);
		return result;
		
	}
	@RequestMapping(value="/editData")
	public @ResponseBody String editData(AddressBook book,String id,String oper)
	{
		/**
		 * 默认情况下id在oper=add的时候的值为'_empty',在oper=edit|del的时候值为数据的主键。
		 * 
		 * 但这么做显然不满足springMVC自动把属性封装成对象的特性,因为在新增的时候id是'_empty'。
		 * 而AddressBook中的id是int类型。是转换不过来的。所以需要在前端页面做一下转换设置。
		 * 当id='_empty'的时候把它重新设置为'0'即可。
		 */
		if("add".equals(oper))
		{
			book.setId(null);
			addressBookService.insertData(book);
		}
		else if("edit".equals(oper))
		{
			AddressBook dbData = addressBookService.findById(book.getId());
			dbData.setEmail(book.getEmail());
			dbData.setMobile(book.getMobile());
			dbData.setUsername(book.getUsername());
			dbData.setDeptid(book.getDeptid());
			addressBookService.updateData(dbData);
		}
		else if("del".endsWith(oper))
		{
			addressBookService.delById(book.getId());
		}
		return "";
	}
	/**
	 * 校验手机号是否已经存在
	 * @param mobile
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/checkExists")
	public @ResponseBody String checkExists(String id,String mobile)
	{
		List<AddressBook> book = addressBookService.findByCond(1, 1, "id", "asc", "and mobile='"+mobile.replaceAll("\'", "\'\'")+"' " );
		if(book!=null&&book.size()>0)
		{
			AddressBook tmp = book.get(0);
			if(id.equals(tmp.getId()+""))
			{
				return "1";
			}
			return "2";
		}
		return "1";
	}
	/**
	 * 校验邮箱是否已经存在
	 * @param id
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/checkEmailExists")
	public  @ResponseBody String checkEmailExists(String id,String email)
	{
		List<AddressBook> book = addressBookService.findByCond(1, 1, "id", "asc", "and email='"+email.replaceAll("\'", "\'\'")+"' " );
		if(book!=null&&book.size()>0)
		{
			AddressBook tmp = book.get(0);
			if(id.equals(tmp.getId()+""))
			{
				return "1";
			}
			return "2";
		}
		return "1";
	}
	
}
