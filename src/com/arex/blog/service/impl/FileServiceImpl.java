package com.arex.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.FileDTO;
import com.arex.blog.service.FileService;
import com.arex.blog.utils.FileUtils;

@Component(value="fileServiceImpl")
public class FileServiceImpl implements FileService {

	public FileDTO uploadFile(FileDTO fileDTO) {
		
		File upload = fileDTO.getUpload();
		String uploadContentType = fileDTO.getUploadContentType();
		String uploadFileName = fileDTO.getUploadFileName();
		
		//获取文件扩展名
		String expandName = FileUtils.getExtendNameByContentType(uploadContentType);
		
		//保存图片
		String fileName = UUID.randomUUID().toString() + expandName;
		//设置fileURL
		String serverHost = ServletActionContext.getRequest().getLocalAddr();
		fileDTO.setFileURL("http://" + serverHost + ":8080/Blog/img/uploadImage/" + fileName);
		fileDTO.setFileName(fileName);
		String uploadPath = ServletActionContext.getServletContext().getRealPath("/img/uploadImage");
		
//		System.out.println(ServletActionContext.getRequest().getLocalAddr());
//		System.out.println(ServletActionContext.getRequest().getRemoteHost());
//		System.out.println(fileDTO.getFileURL());
		
		
		File saveImageFile = new File(uploadPath, fileName);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(upload);
			fos = new FileOutputStream(saveImageFile);
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length=fis.read(buffer)) > 0 ) {
				fos.write(buffer, 0, length);
			}
			fos.flush();
			fileDTO.setUploadSuccessFlags(true);
			fileDTO.setAbsolutePathOnServer(saveImageFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			fileDTO.setUploadSuccessFlags(false);
			e.printStackTrace();
		} catch (IOException e) {
			fileDTO.setUploadSuccessFlags(false);
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch(IOException e) {
				fileDTO.setUploadSuccessFlags(false);
				e.printStackTrace();
			}
		}
	
		return fileDTO;
	}
}
