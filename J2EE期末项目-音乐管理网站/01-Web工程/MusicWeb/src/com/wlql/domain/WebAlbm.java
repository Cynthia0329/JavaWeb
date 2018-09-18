package com.wlql.domain;

import java.util.Date;

/**
 * 专辑实体对象
 * @author lenovo
 * 上午12:38:25
 */
public class WebAlbm {
	private String albm_id;//专辑主键
	private String albm_name;//专辑名
	private String singer_id;//歌手id
	private String singer_name;//歌手名
	private Date albm_date;//专辑发行日期
	private String albm_jianjie;//专辑简介
	public String getAlbm_id() {
		return albm_id;
	}
	public void setAlbm_id(String albm_id) {
		this.albm_id = albm_id;
	}
	public String getAlbm_name() {
		return albm_name;
	}
	public void setAlbm_name(String albm_name) {
		this.albm_name = albm_name;
	}
	public String getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(String singer_id) {
		this.singer_id = singer_id;
	}
	public Date getAlbm_date() {
		return albm_date;
	}
	public void setAlbm_date(Date albm_date) {
		this.albm_date = albm_date;
	}
	public String getAlbm_jianjie() {
		return albm_jianjie;
	}
	public void setAlbm_jianjie(String albm_jianjie) {
		this.albm_jianjie = albm_jianjie;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	
	
}
