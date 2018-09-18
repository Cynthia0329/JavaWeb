package com.wlql.serviceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wlql.daoimpl.WebFileDaoImpl;
import com.wlql.domain.Page;
import com.wlql.domain.ServiceToActionMsg;
import com.wlql.domain.WebFile;
import com.wlql.domain.WebUser;
import com.wlql.service.WebFileService;
@Service
public class WebFileServiceImpl implements WebFileService {
    @Autowired
	WebFileDaoImpl webFileDaoImpl;
	
    //分页查询操作
	public ServiceToActionMsg<WebFile> selectAllWebFileByPage(
			Page<WebFile> pager) {
		
		 ServiceToActionMsg<WebFile> serviceToActionMsg = new ServiceToActionMsg<WebFile>(false);
		 try {
			
			pager=webFileDaoImpl.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
	}

	//处理文件上传
	public boolean FileUpload(MultipartFile[] files, String file_pk_id) {
		//循环开始处理文件上传
		 boolean flag=true;
		 String parentpath="D:\\music\\";
		try{
			for(MultipartFile file:files){
				 //得到文件名
				 String filename=file.getOriginalFilename();
				 
				 //得到文件的后缀
				 String hz=filename.substring(filename.lastIndexOf("."));
				 
				 String filepath=parentpath+UUID.randomUUID().toString()+hz;
				//得到文件大小
				 long filesize=file.getSize();
				 //保存文件到磁盘上面
				 File f=new File(filepath);
				 if(!f.exists()){
					 f.mkdirs();
				 }
				 
				 file.transferTo(new File(filepath));
				//得到
				
				 WebFile webFile=new WebFile();
				 webFile.setFile_pk_id(file_pk_id);
				 webFile.setFileid(UUID.randomUUID().toString());
				 webFile.setFilepath(filepath);
				 webFile.setFilename(filename);
				 webFile.setFilesize(filesize);
				 webFile.setFileuploaddate(new Date());
				 
				 webFileDaoImpl.insertWebFile(webFile);
				
			}
			}catch (Exception e) {
				flag=false;
				e.printStackTrace();
			}
		
		return flag;
	}
	
	//删除文件
	public boolean deleteWebFile(String file_pk_id) {
		
		return webFileDaoImpl.deleteWebFile(file_pk_id);
	}

	public WebFile getWebFileByPkId(String music_id) {
		return webFileDaoImpl.getWebFileByPkId(music_id);
	}
	
	
	public List<WebFile> getWebFileListByPkId(String music_id) {
		return webFileDaoImpl.getWebFileListByPkId(music_id);
	}

	public WebFile getWebFileById(String fileid) {
		return webFileDaoImpl.getWebFileById(fileid);
	}


}
