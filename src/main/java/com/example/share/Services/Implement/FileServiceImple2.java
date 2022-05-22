package com.example.share.Services.Implement;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.share.Entities.Course;
import com.example.share.Entities.Files;
import com.example.share.Entities.Permission;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.FilesRepostry;
import com.example.share.Repositories.PermissionRepostry;

@Service
public class FileServiceImple2 {
	@Autowired
	private FileServeceImpl filepath;
	@Autowired
	private CourseRepostry crepo;
	@Autowired
	private PermissionRepostry permRepo;
	private Collection<Permission> permission;
	
	public Collection<Permission> getPermission() {
		return permission;
	}
	@Autowired
	private FilesRepostry filerepo;
  public Files saveCourseFiles(Course course,long pid,long cid) {
	Course cours=crepo.findCoursById(cid);
	Files myfiles = null;
	 Collection<Permission> pubFil=permRepo.findPermById(pid);
	  if(course !=null) {
		  for (MultipartFile file :course.getFiles()) {
				String filename=file.getOriginalFilename();
				String modifiedFilename=FilenameUtils.getBaseName(filename)+" "+
				System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
				File storefile=filepath.getFilePath(modifiedFilename,"images");
				if(storefile!=null) {
					try {
						FileUtils.writeByteArrayToFile(storefile, file.getBytes());
					}
						catch(Exception a) {
							a.getMessage();
						}
				}
				Files filess=new Files();
				filess.setFile_name(filename);
				filess.setModifiedFileName(modifiedFilename);
				filess.setType(FilenameUtils.getExtension(filename));
				filess.setCourse(cours);
				long fileSize=file.getSize();
				filess.setSize(readableFileSize(fileSize));
				filess.setPermission(pubFil);
				myfiles=filerepo.save(filess);
		  }
	  }
	return myfiles;
	  
  }
  //make file size readable
  public String readableFileSize(long size) {
      if (size <= 0) {
          return "0";
      }
      final String[] units = new String[] {"B", "KB", "MB", "GB", "TB"};
      int digitGroups = (int)(Math.log10(size) / Math.log10(1024));
      return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) 
        + " " + units[digitGroups];
  }
  public Collection<Files> allFiles() {
	  return filerepo.findAll();
  }
  
  public Collection<Files> otherFiles(String type1,String type2) {
	  return filerepo.findTotalOthersByType(type1,type2);
  }
  public Collection<Files> notOwnerFiles(long permid,long owid) {
	  return filerepo.findNotOwnerFileById(permid, owid);
  }
}
