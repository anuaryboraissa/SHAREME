package com.example.share.Controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.share.Entities.Course;
import com.example.share.Entities.Student;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.StudentRepostry;

@Controller
public class Mycontroller {
	@Autowired
	private StudentRepostry stdrepol;
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
	@RequestMapping("/test")
	public String test() {
		return "tabbb";
	}

}
