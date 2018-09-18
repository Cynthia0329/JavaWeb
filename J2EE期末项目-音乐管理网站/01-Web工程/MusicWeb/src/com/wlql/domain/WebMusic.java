package com.wlql.domain;
/**
 * 歌曲对象实体
 * @author lenovo
 * 上午12:40:21
 */
public class WebMusic {

	private String music_id;//歌曲主键
	private String music_name;//歌曲名
	private String music_albm_id;//专辑id
	private String music_albm_name;//专辑名称
	private Integer music_shichang;//歌曲时长
	
	private String music_filepath;//歌曲文件路径
	private String music_geci;//歌词文件路径
	public String getMusic_id() {
		return music_id;
	}
	public void setMusic_id(String music_id) {
		this.music_id = music_id;
	}
	public String getMusic_albm_id() {
		return music_albm_id;
	}
	public void setMusic_albm_id(String music_albm_id) {
		this.music_albm_id = music_albm_id;
	}
	public Integer getMusic_shichang() {
		return music_shichang;
	}
	public void setMusic_shichang(Integer music_shichang) {
		this.music_shichang = music_shichang;
	}
	public String getMusic_filepath() {
		return music_filepath;
	}
	public void setMusic_filepath(String music_filepath) {
		this.music_filepath = music_filepath;
	}
	public String getMusic_geci() {
		return music_geci;
	}
	public void setMusic_geci(String music_geci) {
		this.music_geci = music_geci;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	public String getMusic_albm_name() {
		return music_albm_name;
	}
	public void setMusic_albm_name(String music_albm_name) {
		this.music_albm_name = music_albm_name;
	}
	
	
	
	
	
}
