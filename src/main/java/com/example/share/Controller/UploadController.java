package com.example.share.Controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.share.Entities.Course;
import com.example.share.Entities.Files;
import com.example.share.Entities.Permission;
import com.example.share.Entities.Student;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.FilesRepostry;
import com.example.share.Repositories.PermissionRepostry;
import com.example.share.Repositories.StudentRepostry;
import com.example.share.Services.Implement.FileServiceImple2;
import com.example.share.Services.Implement.StudentsServices;

@Controller
public class UploadController {
	@Autowired
	private StudentRepostry stdrepol;
	@Autowired
	private FileServiceImple2 fileservice;
	@Autowired
	private FilesRepostry fileRepo;
	@Autowired
	private StudentsServices service;
	@Autowired
	private PermissionRepostry permRepo;
	@Autowired
	private CourseRepostry Student;
	@RequestMapping("/resource")
	public String viewRegister(Authentication auth,HttpServletRequest request,Model m) {
		  Principal userPrincipal = request.getUserPrincipal();
		  Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		  Collection<Course> courses=Student.findCoursetById(mystd.getId());
		  Collection<Files> Publfiles=fileservice.notOwnerFiles(3,mystd.getId());
		  Collection<Files> Prilfiles=fileRepo.findFilePermById(5);
		  Collection<Files> pdffiles=fileRepo.findTotalFilesByType("pdf");
		  Collection<Files> docsfiles=fileRepo.findTotalFilesByType("docx");
		  Collection<Files> allfiles=(Collection<Files>) fileservice.allFiles();
		  Collection<Files> otherfiles=fileservice.otherFiles("pdf","docx");
		  Collection<Student> newFriends=service.selectNewFriends(userPrincipal.getName(),0,mystd.getId());
		  System.out.println(Publfiles);
		  System.out.println(otherfiles);
		  System.out.println(pdffiles);
		  System.out.println(docsfiles);
		  System.out.println(allfiles);
		  System.out.println(courses);
		  Course mycourse=new Course();
		  m.addAttribute("newFriends",newFriends);
		  m.addAttribute("mycourse",mycourse);
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
	@ModelAttribute
	public Course getFiles() {
		return new Course();
	}
	@PostMapping("/savefile")
	public String saveFiles(@ModelAttribute Course course,RedirectAttributes redirect,
			@RequestParam("public") long pid,@RequestParam("cid") long cid) {
		Files files=fileservice.saveCourseFiles(course,pid,cid);
		if(files!=null) {
			redirect.addFlashAttribute("message", "files save Successfully..");
		}
		else {
		redirect.addFlashAttribute("perror", "file Not saved..");}
		return "redirect:/resource";
		
	}
}
