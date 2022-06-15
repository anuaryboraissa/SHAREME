package com.example.share.Controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.share.Entities.Course;
import com.example.share.Entities.Files;
import com.example.share.Entities.Messages;
import com.example.share.Entities.Student;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.FilesRepostry;
import com.example.share.Repositories.StudentRepostry;
import com.example.share.Services.Implement.FileServiceImple2;
import com.example.share.Services.Implement.StudentsServices;

@Controller
public class Mycontroller {
	@Autowired
	private StudentRepostry stdrepol;
	@Autowired
	private FilesRepostry filerepo;
	@Autowired
	private StudentsServices services;
	@Autowired
	private FileServiceImple2 service;
	@Autowired
	private CourseRepostry Student;
	@GetMapping("/")
	public String viewHome(Authentication auth,HttpServletRequest request,Model m) {
		 Principal userPrincipal = request.getUserPrincipal();
		  if (auth != null) {
				System.out.println((userPrincipal != null ? userPrincipal.getName() : null));
		    }
		  Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		 Collection<Course>  courses=Student.findCoursetById(mystd.getId());
		  System.out.println(courses);
		  System.out.println(mystd.getId());
		  System.out.println(mystd.getPhotos());
		  System.out.println(mystd.getPhotosImagePath());
		  m.addAttribute("userimage", mystd.getPhotosImagePath());
		  m.addAttribute("first", mystd.getFirst());
		  m.addAttribute("last", mystd.getLast());
		  m.addAttribute("courses",courses);
		return "index";
	}
	@RequestMapping("/home")
	public String viewHome() {
		return "index1";
	}
	@RequestMapping("/history")
	public String test(Authentication auth,HttpServletRequest request,Model m) {
		Principal userPrincipal = request.getUserPrincipal();
		 Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Files> pubfiles=service.getFileHistory(3, mystd.getId());
		Collection<Files> prifiles=service.getFileHistory(4, mystd.getId());
		Collection<Files> taggedfiles=service.getFileHistory(5, mystd.getId());
		
		m.addAttribute("userimage", mystd.getPhotosImagePath());
		 m.addAttribute("first", mystd.getFirst());
		 m.addAttribute("last", mystd.getLast());
		m.addAttribute("public", pubfiles);
		m.addAttribute("pubsize", pubfiles.size());
		m.addAttribute("private", prifiles);
		m.addAttribute("prisize", prifiles.size());
		m.addAttribute("tagged", taggedfiles);
		m.addAttribute("taggsize", taggedfiles.size());
		
		Collection<Messages> msgs=services.getAllAchievedReceived(mystd.getId(),2,1);
		Collection<Student> stds=services.archieved(mystd.getId(),1,2); 
		m.addAttribute("achieved",msgs);
		m.addAttribute("stds",stds);
		m.addAttribute("asiz",msgs.size());
		for (Messages messages : msgs) {
			System.out.println("sent by "+messages.getMsg());	
			//System.out.println("sent by "+messages.getStdFrom().getFirst());	
		}
		
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		String formatteddate=dateFormat.format(date);
		LocalDateTime localDateTime=LocalDateTime.now();
		LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
	
		System.out.println("time is "+formatteddate);	
		System.out.println("time is "+localDateTime);	
		System.out.println("time is "+localTime.getHour()+":"+localTime.getMinute());	

		return "history";
	}
	@GetMapping("/more")
	public String moreDetails(Authentication auth,HttpServletRequest request,Model m,@RequestParam("more") long id) {
		Files file=filerepo.getById(id);
		m.addAttribute("filCs", file.getCourse().getCourse_name());
		m.addAttribute("idd", "1");
		System.out.println("file name is "+file.getFile_name());
		m.addAttribute("fname",file.getFile_name());
	   Principal userPrincipal = request.getUserPrincipal();
		 Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Files> pubfiles=service.getFileHistory(3, mystd.getId());
		Collection<Files> prifiles=service.getFileHistory(4, mystd.getId());
		Collection<Files> taggedfiles=service.getFileHistory(5, mystd.getId());
		System.out.println(pubfiles);
		m.addAttribute("userimage", mystd.getPhotosImagePath());
		  m.addAttribute("first", mystd.getFirst());
		 m.addAttribute("last", mystd.getLast());
		m.addAttribute("public", pubfiles);
		m.addAttribute("pubsize", pubfiles.size());
		m.addAttribute("private", prifiles);
		m.addAttribute("prisize", prifiles.size());
		m.addAttribute("tagged", taggedfiles);
		m.addAttribute("taggsize", taggedfiles.size());
		return "history";
	}

}
