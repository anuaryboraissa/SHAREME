package com.example.share.Controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.share.Controller.DTOS.CollegeDTO;
import com.example.share.Entities.College;
import com.example.share.Entities.Course;
import com.example.share.Entities.Intake;
import com.example.share.Entities.Programme;
import com.example.share.Entities.University;
import com.example.share.Repositories.CollegeRepostry;
import com.example.share.Repositories.ProgrammeRepostry;
import com.example.share.Repositories.UniversityRepostry;
import com.example.share.Services.Implement.CollegeImpl;

@Controller
public class HomeController {
	@Autowired
	private UniversityRepostry unirepo;
	@Autowired
	private CollegeRepostry collrepo;
	@Autowired
	private ProgrammeRepostry prorepo;
	
	@Autowired
	private CollegeImpl service;
	@RequestMapping("/home")
	public String viewHome() {
		return "index1";
	}
	@RequestMapping("/adminRegister")
	public String viewAdmin(Model m) {
		Collection<University> listuni=unirepo.findAll();
		Collection<College> listcoll=collrepo.findAll();
		Collection<Programme> listProg=prorepo.findProgrammeById(22);
		College college=new College();
		University university=new University();
		Intake intake=new Intake();
		Course course=new Course();
		
		m.addAttribute("universities", listuni);
		m.addAttribute("colleges", listcoll);
		m.addAttribute("listProg", listProg);
		
		Programme programme=new Programme();
		m.addAttribute("college",college);
		m.addAttribute("university",university);
		m.addAttribute("intake",intake);
		m.addAttribute("course",course);
		m.addAttribute("programme",programme);
		return "Adminsignup";
	}
	
	@ModelAttribute
	public University chageUn() {
		return new University();
	}
	@ModelAttribute
	public Programme chagePr() {
		return new Programme();
	}
	@ModelAttribute
	public Intake chageIn() {
		return new Intake();
	}
	@ModelAttribute
	public Course chageCs() {
		return new Course();
	}
	@PostMapping("/savepr")
	public String saveProgrammesss(@Valid @ModelAttribute
			Programme programme,BindingResult bindingResult,RedirectAttributes redirect
			) {
		if(bindingResult.hasErrors()) {
			
			return "Adminsignup";
		}
		Programme mypro=service.saveProgramme(programme);
		if(mypro!=null) {
			redirect.addFlashAttribute("message", "Programme save Successfully..");
		}
		redirect.addFlashAttribute("perror", "Programme Not saved..");
		return "redirect:/adminRegister";
		}	

	@PostMapping("/saveuni")
	public String saveUni(@Valid @ModelAttribute
			University university,BindingResult result,RedirectAttributes redirect) {
		if(result.hasErrors()) {
			return "Adminsignup";
		}
		University myuni=service.saveUniversity(university);
		if(myuni!=null) {
			redirect.addFlashAttribute("message", "university save Successfully..");
		}
		return "redirect:/adminRegister";
	}
	@ModelAttribute("colleg")
	public CollegeDTO getCollegeDetails() {
		return new CollegeDTO();
	}
	
	@PostMapping("/savecoll")
	public String savecollege(@Valid @ModelAttribute("colleg")
			CollegeDTO college,BindingResult bindingResult,RedirectAttributes redirect) {
		if(bindingResult.hasErrors()) {
			return "Adminsignup";
		}
		College mycoll=service.saveCollege(college);
		if(mycoll!=null) {
			redirect.addFlashAttribute("message", "college save Successfully..");
		}
		return "redirect:/adminRegister";
	}
	@PostMapping("/saveint")
	public String saveIntake(@Valid @ModelAttribute
			Intake intake,BindingResult bindingResult,RedirectAttributes redirect) {
		if(bindingResult.hasErrors()) {
			return "Adminsignup";
		}
		Intake myint=service.saveIntake(intake);
		if(myint!=null) {
			redirect.addFlashAttribute("message", "intake save Successfully..");
		}
		return "redirect:/adminRegister";
	}
	@PostMapping("/savecs")
	public String saveCourses(@Valid @ModelAttribute
			Course course,Model m,BindingResult bindingResult,RedirectAttributes redirect) {
		if(bindingResult.hasErrors()) {
			return "Adminsignup";
		}
		Course mycourse=service.saveCourses(course);
		if(mycourse!=null) {
			redirect.addFlashAttribute("message", "course save Successfully..");
		}
		return "redirect:/adminRegister";
	}
}
