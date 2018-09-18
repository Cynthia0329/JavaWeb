package com.wlql.domain;

import java.util.Date;

/**
 * 网站用户实体
 * @author lenovo
 * 下午11:30:50
 */
public class WebUser {
	private String userid;//用户主键
	
	private String username;//用户名
	
	private String password;//用户密码
	
	private String usersex;//用户性别
	
	private String usercard;//用户身份证
	
	private String useraddress;//用户籍贯
	
	private Date  userbirthdate;//用户生日
	
	private String userzt;//用户状态,是否审核通过,审核通过才能通过

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getUsercard() {
		return usercard;
	}

	public void setUsercard(String usercard) {
		this.usercard = usercard;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public Date getUserbirthdate() {
		return userbirthdate;
	}

	public void setUserbirthdate(Date userbirthdate) {
		this.userbirthdate = userbirthdate;
	}

	public String getUserzt() {
		return userzt;
	}

	public void setUserzt(String userzt) {
		this.userzt = userzt;
	}
}
