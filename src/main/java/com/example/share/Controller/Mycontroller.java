package com.example.share.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.share.Entities.Archieve;
import com.example.share.Entities.College;
import com.example.share.Entities.Course;
import com.example.share.Entities.Delete;
import com.example.share.Entities.Files;
import com.example.share.Entities.Groups;
import com.example.share.Entities.ImageGallery;
import com.example.share.Entities.Messages;
import com.example.share.Entities.Student;
import com.example.share.Entities.University;
import com.example.share.Repositories.CollegeRepostry;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.FilesRepostry;
import com.example.share.Repositories.MessageRepo;
import com.example.share.Repositories.StudentRepostry;
import com.example.share.Repositories.UniversityRepostry;
import com.example.share.Services.Implement.FileServiceImple2;
import com.example.share.Services.Implement.ImageGalleryService;
import com.example.share.Services.Implement.StudentsServices;

@Controller
public class Mycontroller {
	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ImageGalleryService imageGalleryService;
	@Autowired
	private StudentRepostry stdrepol;
	@Autowired
	private UniversityRepostry univerrepo;
	@Autowired
	private CollegeRepostry collrepo;
	@Autowired
	
	private FilesRepostry filerepo;
	@Autowired
	private MessageRepo msgRepo;
	@Autowired
	private StudentsServices services;
	@Autowired
	private FileServiceImple2 service;
	@Autowired
	private CourseRepostry Student;
	@GetMapping("/")
	public String viewHome(Authentication auth,HttpServletRequest request,Model m,String friend) {
		 Principal userPrincipal = request.getUserPrincipal();
			
		  Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		  
		  if (auth != null) {
				System.out.println((userPrincipal != null ? userPrincipal.getName() : null));
		    }
			Collection<Files> pubfiles=service.getFileHistory(3, mystd.getId());
			Collection<Files> prifiles=service.getFileHistory(4, mystd.getId());
			Collection<Files> taggedfiles=service.getFileHistory(5, mystd.getId());
			  Collection<Student> newFriends=services.selectNewFriends(userPrincipal.getName(), 0,mystd.getId());
			  Collection<Messages> allmsgs=services.getAllSentMsgs(mystd.getId());
			  for (Messages messages : allmsgs) {
				  System.out.println("to std "+messages.getId());
				for (Student stdd : messages.getStdto()) {
					System.out.println("to std "+stdd.getFirst());
				}
				for (Groups stdd : messages.getGroups()) {
					System.out.println("to std "+stdd.getGrp_name());
				}
			}
			  Collection<Groups> stdgroups=services.getStudentGroups(mystd.getId());
			  Groups grp=new Groups();
			  m.addAttribute("grp",grp);
			  m.addAttribute("groups",stdgroups);
			 m.addAttribute("totalmsgs", allmsgs.size());
			 m.addAttribute("totalfr", newFriends.size());
			 Collection<Student> serchedFr=stdrepol.searchByKey(userPrincipal.getName(),0,mystd.getId(),friend);
				if (friend==null) {
					m.addAttribute("newFriends",newFriends);
				} else {
					m.addAttribute("friend",friend);
					m.addAttribute("newFriends",serchedFr);
				}
			 m.addAttribute("totalgrp", stdgroups.size());
			 m.addAttribute("totalfl", (taggedfiles.size()+prifiles.size()+pubfiles.size()));
			 System.out.println("home files "+(taggedfiles.size()+prifiles.size()+pubfiles.size()));
			 System.out.println("friends "+newFriends.size());
			 System.out.println("groups "+stdgroups.size());
			 System.out.println("msgs "+allmsgs.size());
		 Collection<Course>  courses=Student.findCoursetById(mystd.getId());
		 Collection<University> uni=univerrepo.findAll();
		 Collection<College> coll=collrepo.findAll();
		 for (University university : uni) {
			for (College college : coll) {
				 m.addAttribute("unii",university);
				 m.addAttribute("coll",college);
			}
		}
		  System.out.println(courses);
		  System.out.println(mystd.getId());
		  System.out.println(mystd.getPhotos());
		  System.out.println(mystd.getPhotosImagePath());
		  m.addAttribute("userimage", mystd.getPhotosImagePath());
		  m.addAttribute("first", mystd.getFirst());
		  m.addAttribute("last", mystd.getLast());
		  m.addAttribute("courses",courses);
		 
		  m.addAttribute("logid", mystd.getId());
		  Collection<Student> stds=services.exceptLogger(mystd.getId()); 
		  m.addAttribute("stds",stds);
		  m.addAttribute("msgss",allmsgs);
		return "index";
	}
	@PostMapping("/deletemsgg")
	public String deleteMsg(@RequestParam("msgid") long msgid,Authentication auth,HttpServletRequest request) {
		 Principal userPrincipal = request.getUserPrincipal();
		 Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Messages msg=msgRepo.getById(msgid);
		Delete deleteMsg=services.deletemsgByid(mystd,msg,1);
		if(deleteMsg!=null) {
			System.out.println("message deleted");
		}
		else {
			System.out.println("fail to delete");
		}
		return "redirect:/";
	}
	@RequestMapping("/home")
	public String viewHome() {
		return "index1";
	}
	@RequestMapping("/history")
	public String test(Authentication auth,HttpServletRequest request,Model m,String keyword,String keyword2,String tagfile) {
		Principal userPrincipal = request.getUserPrincipal();
		ImageGallery imgs=new ImageGallery();
		m.addAttribute("imagee",imgs); 
		 Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		 Collection<Files> pubFiles=filerepo.searchHistryFilesByKey(3,mystd.getId(), keyword);
		  Collection<Files> tagFiles=filerepo.searchHistryFilesByKey(5,  mystd.getId(), tagfile);
		  Collection<Files> prifile=filerepo.searchHistryFilesByKey(4, mystd.getId(), keyword2);
	
		
		  
		Collection<Files> pubfiles=service.getFileHistory(3, mystd.getId());
		Collection<Files> prifiles=service.getFileHistory(4, mystd.getId());
		Collection<Files> taggedfiles=service.getFileHistory(5, mystd.getId());
		
		m.addAttribute("userimage", mystd.getPhotosImagePath());
		 m.addAttribute("first", mystd.getFirst());
		 m.addAttribute("last", mystd.getLast());
		 m.addAttribute("logid", mystd.getId());
		  if(keyword==null) {
		 m.addAttribute("public", pubfiles);
		 m.addAttribute("pubsize", pubfiles.size());
		  }
		  else {
			  m.addAttribute("keyword",keyword); 
			  m.addAttribute("public",pubFiles);
			  m.addAttribute("pubsize", pubFiles.size());
		  }
		
		
		  if(keyword2==null) {
			  m.addAttribute("private", prifiles);
				m.addAttribute("prisize", prifiles.size());
				  }
				  else {
					  m.addAttribute("keyword2",keyword2); 
					  m.addAttribute("private",prifile); 
						m.addAttribute("prisize", prifile.size());
				  }
		
	
		  if(tagfile==null) {
			  m.addAttribute("tagged", taggedfiles);
			  m.addAttribute("taggsize", taggedfiles.size());
				  }
				  else {
					
					  m.addAttribute("tagfile",tagfile); 
					  m.addAttribute("tagged",tagFiles); 
					  m.addAttribute("taggsize", tagFiles.size());
				  }
	     
		
		Collection<Messages> msgs=services.getAllGrpsAchieved(mystd.getId(),1);
		Collection<Messages> msgsbystd=services.getStudentsArchieved(mystd.getId(),1);
		for (Messages messages : msgsbystd) {
			for (Archieve achieve : messages.getMsgachieved()) {
				System.out.println("std achieve is "+achieve.getStdmsgs().getFirst());
			}
			for (Student stdd : messages.getStdfrom()) {
				 m.addAttribute("stdd",stdd);
			}
		}
		Collection<Groups> gmsgsarchieve=services.groupArchieved();
	    
		for (Messages msg : msgs) {
			for (Groups groups : msg.getGroups()) {
				System.out.println("grp achieve is "+groups.getGrp_name());	
			}
		}
	    
		System.out.println("groups  "+gmsgsarchieve);
		m.addAttribute("achieved",msgs);
		m.addAttribute("sachieved",msgsbystd);;
		m.addAttribute("asiz",(msgs.size()+msgsbystd.size()));
	
		
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		String formatteddate=dateFormat.format(date);
		LocalDateTime localDateTime=LocalDateTime.now();
		LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
	
		System.out.println("time is "+formatteddate);	
		System.out.println("time is "+localDateTime);	
		System.out.println("time is "+localTime.getHour()+":"+localTime.getMinute());	
		List<ImageGallery> images = imageGalleryService.getAllActiveImages();
		m.addAttribute("imgs", images);

		return "history";
	}
	@GetMapping("/image/display/{id}")
	void showImage(@PathVariable("id") Long id,ImageGallery imageGallery,HttpServletResponse response)
			throws ServletException, IOException {
		imageGallery = imageGalleryService.getStudentPhotoById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(imageGallery.getImage());
		response.getOutputStream().close();
	}
	@GetMapping("/file/display/{id}")
	void previewFile(@PathVariable("id") Long id,Files file,HttpServletResponse response)
			throws ServletException, IOException {
		file=filerepo.getById(id);
		response.setContentType("application/pdf,application/docx,image/jpg,image/png, image/gif");
		response.getOutputStream().write(file.getFile_name().getBytes());
		response.getOutputStream().close();
	}
	@GetMapping("/gimage/display/{id}")
	void showGroupImage(@PathVariable("id") Long id,ImageGallery imageGallery,Groups grps,HttpServletResponse response)
			throws ServletException, IOException {
		grps=services.findGroup(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(grps.getGrp_icon());
		response.getOutputStream().close();
	}
	
	 @PostMapping("/image")
	 public String saveImage(@RequestParam("image") MultipartFile file,HttpServletRequest request,Model model) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			System.out.print("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! incorrect");
				System.out.print("file failured");
			}
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					System.out.print("folder created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			ImageGallery imageGallery = new ImageGallery();
			imageGallery.setImage(imageData);
			imageGalleryService.saveImage(imageGallery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "redirect:/history";
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
