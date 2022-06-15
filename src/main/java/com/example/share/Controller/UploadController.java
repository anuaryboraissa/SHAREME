package com.example.share.Controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.share.Entities.Course;
import com.example.share.Entities.Files;

import com.example.share.Entities.Student;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.FilesRepostry;

import com.example.share.Repositories.StudentRepostry;
import com.example.share.Services.Implement.FileServiceImple2;
import com.example.share.Services.Implement.StudentsServices;

@Controller
public class UploadController {
	@Autowired
	private javax.servlet.ServletContext context;
	@Autowired
	private StudentRepostry stdrepol;
	@Autowired
	private FileServiceImple2 fileservice;
	
	@Autowired
	private FilesRepostry fileRepo;
	@Autowired
	private StudentsServices service;
	@Autowired
	private CourseRepostry Student;
	@RequestMapping("/resource")
	public String viewRegister(Authentication auth,HttpServletRequest request,Model m) {
		  Principal userPrincipal = request.getUserPrincipal();
		  Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		  Collection<Course> courses=Student.findCoursetById(mystd.getId());
		  Collection<Files> Publfiles=fileservice.notOwnerFiles(3,mystd.getId());
		  Collection<Files> taggedFiles=fileservice.taggedFiles(mystd.getId());
		  m.addAttribute("taggedFiles",taggedFiles);
		  Collection<Student> students=service.selectAll();
		  m.addAttribute("allstd",students);
		  Collection<Files> Prilfiles=fileRepo.findFilePermById(5);
		  Collection<Files> pdffiles=fileRepo.findTotalFilesByType("pdf",mystd.getId());
		  Collection<Files> docsfiles=fileRepo.findTotalFilesByType("docx",mystd.getId());
		  Collection<Files> taggedPdf=fileservice.taggedFiles("pdf", mystd.getId());
		  m.addAttribute("tpdf",(taggedPdf.size()+pdffiles.size()));
		  Collection<Files> taggeddocx=fileservice.taggedFiles("docx", mystd.getId());
		  m.addAttribute("tdocs",(docsfiles.size()+taggeddocx.size()));
		  Collection<Files> allfiles=(Collection<Files>) fileservice.allFiles();
		  Collection<Files> otherfiles=fileservice.otherFiles("pdf","docx",mystd.getId());
		  Collection<Files> otherTaggedfiles=fileservice.otherFilesTagged("docx", "pdf", mystd.getId());
		  m.addAttribute("tothers",(otherfiles.size()+otherTaggedfiles.size()));
		  m.addAttribute("tfiles",(otherfiles.size()+otherTaggedfiles.size()+docsfiles.size()+taggeddocx.size()+taggedPdf.size()+pdffiles.size()));
		  Collection<Student> newFriends=service.selectNewFriends(userPrincipal.getName(),0,mystd.getId());
		  System.out.println(Publfiles);
		  System.out.println(otherfiles);
		  System.out.println(pdffiles);
		  System.out.println(docsfiles);
		  System.out.println(allfiles);
		  System.out.println(courses);
		  Course mycourse=new Course();
		  Student student=new Student();
		  Files files=new Files();
		  m.addAttribute("files",files);
		  m.addAttribute("taggedPdf",taggedPdf);
		  m.addAttribute("otherTaggedfiles",otherTaggedfiles);
		  m.addAttribute("taggeddocx",taggeddocx);
		  m.addAttribute("newFriends",newFriends);
		  m.addAttribute("mycourse",mycourse);
		  m.addAttribute("student",student);
		  m.addAttribute("Publfiles",Publfiles);
		  m.addAttribute("otherfiles",otherfiles);
		  m.addAttribute("pdffiles",pdffiles);
		  m.addAttribute("allfiles",allfiles);
		  m.addAttribute("docsfiles",docsfiles);
		  m.addAttribute("Prilfiles",Prilfiles);
		  m.addAttribute("courses",courses);
		  m.addAttribute("userimage", mystd.getPhotosImagePath());
		  m.addAttribute("first", mystd.getFirst());
		  m.addAttribute("last", mystd.getLast());
		return "resource";
	}
	@GetMapping(value = "/morepub")
	public String moreReqDetails(@RequestParam(value ="pubmore") long id,RedirectAttributes redirect,
			Authentication auth,HttpServletRequest request) {
		 System.out.println("file id "+id);
           Files myfil=fileRepo.getById(id);
		 System.out.println("file name "+myfil.getFile_name());
		 System.out.println("file course "+myfil.getCourse().getCourse_name());
		 redirect.addFlashAttribute("iddd", "1");
		 redirect.addFlashAttribute("cos",myfil.getCourse().getCourse_name());
		 redirect.addFlashAttribute("desc",myfil.getDescription());
		 redirect.addFlashAttribute("fname", myfil.getFile_name());
          return "redirect:/resource";
		
	}
	@ModelAttribute
	public Course getFiles() {
		return new Course();
	}
	
	@PostMapping("/savefile")
	public String saveFiles(@ModelAttribute Course course,RedirectAttributes redirect,
			@RequestParam("public") long pid,@RequestParam("cid") long cid,@RequestParam("desc") String desc,
			Authentication auth,HttpServletRequest request) {
		  Principal userPrincipal = request.getUserPrincipal();
		  Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Files files=fileservice.saveCourseFiles(course,pid,cid,mystd,course.getStudent(),desc);
		if(files!=null) {
			redirect.addFlashAttribute("message", "files save Successfully..");
		}
		else {
		redirect.addFlashAttribute("perror", "file Not saved..");}
		return "redirect:/resource";
		
	}
	 @org.springframework.web.bind.annotation.GetMapping(value="/downloadfile/{name}/{ModifiedFileName}")
	 public void downloadFile(@org.springframework.web.bind.annotation.PathVariable(name="name") String fileName,@org.springframework.web.bind.annotation.PathVariable(name="ModifiedFileName") 
	 String modified,javax.servlet.http.HttpServletResponse response) throws FileNotFoundException {
		 String fullpath=context.getRealPath("/images/"+File.separator+modified);
		 File file=new File(fullpath);
		 final int BUFFER_SIZE=4096;
		 if(file.exists()){
			 System.out.println("downloaded");
			 try {
				 
				 FileInputStream fin=new FileInputStream(file);
				 String mimitype=context.getMimeType(fullpath);
				 response.setContentType(mimitype);
				 response.setHeader("content-disposition", "attachment;filename="+fileName);
				 OutputStream outputstream=response.getOutputStream();
				 byte[] buffer=new  byte[BUFFER_SIZE];
				 int byteread=-1;
				 while((byteread=fin.read(buffer))!=-1) {
					 outputstream.write(buffer,0,byteread);
				 }
				 fin.close();
				 outputstream.close();
			 }
			 catch(Exception a) {
				 throw new FileNotFoundException("Error while downloading file...");
			 }
		 }
	 }
}
