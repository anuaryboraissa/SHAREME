package com.example.share.Services.Implement;

import java.io.File;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.share.Services.FilesService;
@Service
@Transactional
public class FileServeceImpl implements FilesService {
	@Autowired
	  ServletContext context;
	@Override
	public File getFilePath(String modifiedFileName, String path) {
		boolean exist=new File(context.getRealPath("/"+path)+"/").exists();
		if(!exist) {
			new File(context.getRealPath("/"+path)+"/").mkdir();
		}
		String modifiedFilePath=context.getRealPath("/"+path+"/"+File.separator+modifiedFileName);
		File file=new File(modifiedFilePath);
		return file;
	}

}
