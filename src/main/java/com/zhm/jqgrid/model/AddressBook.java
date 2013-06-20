package com.zhm.jqgrid.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 通讯簿实体类
 * @author Administrator
 *
 */
public class AddressBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String email;
	private String mobile;
	private String deptid;
	private Timestamp entry_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Timestamp getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Timestamp entry_date) {
		this.entry_date = entry_date;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	
}
