package com.example.share.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.management.relation.RoleResult;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.share.Controller.DTOS.GroupDTO;
import com.example.share.Controller.DTOS.StudentDTO;
import com.example.share.Entities.College;
import com.example.share.Entities.Course;
import com.example.share.Entities.FileAploadUtil;
import com.example.share.Entities.Groups;
import com.example.share.Entities.Programme;
import com.example.share.Entities.Roles;
import com.example.share.Entities.Student;
import com.example.share.Entities.University;
import com.example.share.Repositories.CollegeRepostry;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.ProgrammeRepostry;
import com.example.share.Repositories.RoleRepo;
import com.example.share.Repositories.StudentRepostry;
import com.example.share.Repositories.UniversityRepostry;
import com.example.share.Services.Implement.StudentsServices;
import com.example.share.Services.Implement.UniversityImpl;

import antlr.StringUtils;
@Controller
@RequestMapping("/signup")
public class RegisterController {
	@Autowired
	private ProgrammeRepostry progrepo;
	@Autowired
	private CollegeRepostry collrepo;
	@Autowired
	private RoleRepo rolerepo;
	@Autowired
	private CourseRepostry courserepo;

	@Autowired
	private StudentRepostry stdrepo;

	@Autowired
	private StudentsServices services;
	@Autowired
	private UniversityRepostry unirepo;
	@Autowired
	private UniversityImpl service;
	@GetMapping
	public String viewRegister(Model m) {
		Collection<College> listcoll=collrepo.findAll();
		Collection<Roles> listroll=rolerepo.findAll();
		List<Student> std=stdrepo.findAll();
		Collection<Programme> listprogramme=progrepo.findProgrammeById(4);
		Collection<University> listuni=unirepo.findAll();
		Collection<Course> listcourse=courserepo.findCourseSemistById(2);
		Collection<Course> listcourseSEM1=courserepo.findCourseSemistById(1);
		m.addAttribute("colleges", listcoll);
		m.addAttribute("listcourseSEM1", listcourseSEM1);
		m.addAttribute("programmes", listprogramme);
		m.addAttribute("listcourses", listcourse);
		m.addAttribute("universities", listuni);
		m.addAttribute("listRoles", listroll);
	
		m.addAttribute("student", std);
		return "auth-sign-up";
	}
	
	@ModelAttribute("uni")
	public StudentDTO obtainStudent() {
		return new StudentDTO();
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@PostMapping("/save")
	public String registerUser(@Valid
			@ModelAttribute("uni") StudentDTO studentdto,BindingResult bindingResult
			,RedirectAttributes attributes, @RequestParam("image") MultipartFile file
			)  {
		if(bindingResult.hasErrors()) {
			System.out.println("imefikaa");
			return "auth-sign-up";
		}
	
			try {
				String filename=org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
				System.out.println(studentdto.getCourses());
				studentdto.setPhotos(filename);
				Student sdt=service.saveUser(studentdto);
				String uploadDir="src/main/resources/static/img1/userphotos/"+sdt.getId();
				FileAploadUtil.saveFile(uploadDir, filename, file);
				System.out.println("hayaa");
			
				attributes.addFlashAttribute("ok", "Registration successfully.");
			} catch (Exception e) {
				e.printStackTrace();
			}	
			finally {
				attributes.addFlashAttribute("error", "failed to save user");
			}
		return "redirect:/signup";
		
	}
	
	/*
	@PostMapping
	public String registerUser(
			Student stud
			) {
		System.out.println(stud.getRoles().toString());
		service.saveUserWithoutDTO(stud);
		return "redirect:/signup?successfull";	
	}
	*/	
	
}
