package com.wlql.domain;

import java.util.Date;

/**
 * 歌手,专辑,海报 通用图片实体
 * @author lenovo
 * 上午12:08:16
 */
public class WebFile {
	 private String fileid;
	 private String filename;
	 private String filepath;
	 private Long filesize;
	 private Date fileuploaddate;
	 private String file_pk_id;
	 private String file_pk_lx;
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	public Date getFileuploaddate() {
		return fileuploaddate;
	}
	public void setFileuploaddate(Date fileuploaddate) {
		this.fileuploaddate = fileuploaddate;
	}
	public String getFile_pk_id() {
		return file_pk_id;
	}
	public void setFile_pk_id(String file_pk_id) {
		this.file_pk_id = file_pk_id;
	}
	public String getFile_pk_lx() {
		return file_pk_lx;
	}
	public void setFile_pk_lx(String file_pk_lx) {
		this.file_pk_lx = file_pk_lx;
	}
	 
	 
	

}
