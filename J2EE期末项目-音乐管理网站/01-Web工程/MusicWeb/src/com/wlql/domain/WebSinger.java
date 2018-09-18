package com.wlql.domain;

import java.util.Date;

/**
 * 歌手实体
 * @author lenovo
 * 上午12:05:33
 */
public class WebSinger {
	private String singer_id;//歌手id
	
	private String singer_name;//歌手
	
	private Date singer_birthdate;//出生日期
	
	private String singer_shuxiang;//属相
	
	private String singer_xingzuo;//星座
	
	private String singer_aihao;//爱好
	
	private String singer_jianjie;//简介
	public String getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(String singer_id) {
		this.singer_id = singer_id;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public Date getSinger_birthdate() {
		return singer_birthdate;
	}
	public void setSinger_birthdate(Date singer_birthdate) {
		this.singer_birthdate = singer_birthdate;
	}
	public String getSinger_shuxiang() {
		return singer_shuxiang;
	}
	public void setSinger_shuxiang(String singer_shuxiang) {
		this.singer_shuxiang = singer_shuxiang;
	}
	public String getSinger_xingzuo() {
		return singer_xingzuo;
	}
	public void setSinger_xingzuo(String singer_xingzuo) {
		this.singer_xingzuo = singer_xingzuo;
	}
	public String getSinger_aihao() {
		return singer_aihao;
	}
	public void setSinger_aihao(String singer_aihao) {
		this.singer_aihao = singer_aihao;
	}
	public String getSinger_jianjie() {
		return singer_jianjie;
	}
	public void setSinger_jianjie(String singer_jianjie) {
		this.singer_jianjie = singer_jianjie;
	}
	
	
	
	

}
