package com.example.share.Controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.share.Entities.Requests;
import com.example.share.Entities.Student;
import com.example.share.Services.Implement.StudentsServices;

@Controller
public class FriendsController {
	@Autowired
	private StudentsServices service;
	@GetMapping("/chating")
	public String friendsChat() {
		return "chat";
		
	}
	@PostMapping("/msgreq")
	public String sandMsg(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("msg") long msgid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student student=service.findStudent(msgid);
		Student  student1=service.saveRequests(userPrincipal.getName(),msgid);
		if(student1!=null) {
			System.out.println("success");
				redirect.addFlashAttribute("message", "message Sent to "+student.getFirst());
}
		return "redirect:/friends";
	}
	@GetMapping("/friends")
    public String viewFrinds(Model m,Authentication auth,HttpServletRequest request,
    		RedirectAttributes direct) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		  m.addAttribute("userimage", mystd.getPhotosImagePath());
		  m.addAttribute("first", mystd.getFirst());
		  m.addAttribute("last", mystd.getLast());
		 Student stud=new Student();
		 m.addAttribute("stud", stud);
		 Requests reques=new Requests();
		 m.addAttribute("reques", reques);
		  Collection<Student> listOfStud=service.selectSuggestedRequests(userPrincipal.getName(), 1,0,4,3,mystd.getId());
		  m.addAttribute("listOfStudents", listOfStud);
		  Collection<Student> requests=service.selectRequests(1,mystd.getId());
		  Collection<Student> newFriends=service.selectNewFriends(userPrincipal.getName(), 0,mystd.getId());
		  Collection<Student> blocked=service.selectBlockedFriends(userPrincipal.getName(),3,mystd.getId());
		  Collection<Student> except=service.selectBlockedFriends(userPrincipal.getName(),0,mystd.getId());
		  Collection<Student> whoConfirm=service.whoConfirmRequest(userPrincipal.getName(), 0, mystd.getId());
		  Collection<Student> whoConcel=service.whoConfirmRequest(userPrincipal.getName(), 2, mystd.getId());
		  m.addAttribute("whoConfirm",whoConfirm);
		  if(except.isEmpty()) {
			 m.addAttribute("v1", "1");
			 System.out.println("ni 1");
		  } 
		  else {
			  m.addAttribute("v1",except); 
		  }
		  
		  m.addAttribute("whoConcel",whoConcel);
		  m.addAttribute("newFriends",newFriends);
		  m.addAttribute("requests", requests);
		  m.addAttribute("blocked", blocked);
	      return "friends";
      }
	@PostMapping("/sendReq")
	public String sandRequests(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("stid") long id) {
		Principal userPrincipal = request.getUserPrincipal();
		Student student=service.findStudent(id);
		Student  student1=service.saveRequests(userPrincipal.getName(), id);
		if(student1!=null) {
			System.out.println("success");
				redirect.addFlashAttribute("message", "Request Sent to "+student.getFirst());
}
		return "redirect:/friends";
	}
	@PostMapping("/updateReq")
	public String editRequests(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("fromid") long fromid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),fromid,2);
		if(student!=null) {
			System.out.println("updated..");
			redirect.addFlashAttribute("message", "Request from "+student.getFirst()+" Canceled");
		}
		return "redirect:/friends";
		
	}
	@PostMapping("/Reqconcel")
	public String editRequest(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("conf") long concid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),concid,0);
		if(student!=null) {
			System.out.println("updated..");
			redirect.addFlashAttribute("message", "Now Friends to "+student.getFirst());
		}
		return "redirect:/friends";
		
	}
	@PostMapping("/blockSt")
	public String blockSt(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("block") long blockid) {
		System.out.println(blockid);
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),blockid,3);
		if(student!=null) {
			System.out.println("blocked..");
			redirect.addFlashAttribute("message",student.getFirst()+" is no longer your friend");
		}
		return "redirect:/friends";
		
	}
	@PostMapping("/removeSugg")
	public String removeSuggestion(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("remove") long remid) {
		System.out.println(remid);
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),remid,4);
		if(student!=null) {
			System.out.println("blocked..");
			redirect.addFlashAttribute("message",student.getFirst()+" Doesn't appear any more");
		}
		return "redirect:/friends";
		
	}
}
