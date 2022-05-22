package com.example.share.Services.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.share.Controller.DTOS.CollegeDTO;
import com.example.share.Entities.College;
import com.example.share.Entities.Course;
import com.example.share.Entities.Intake;
import com.example.share.Entities.Permission;
import com.example.share.Entities.Programme;
import com.example.share.Entities.University;
import com.example.share.Repositories.CollegeRepostry;
import com.example.share.Repositories.CourseRepostry;
import com.example.share.Repositories.IntakeRepostry;
import com.example.share.Repositories.PermissionRepostry;
import com.example.share.Repositories.ProgrammeRepostry;
import com.example.share.Repositories.UniversityRepostry;

@Service
public class CollegeImpl{
	@Autowired
	private PermissionRepostry permissionRepostry;
    @Autowired
    private CollegeRepostry collrepo;
    @Autowired
    private CourseRepostry courseRepo;
    @Autowired
    private UniversityRepostry unirepo;
    
    @Autowired
    private ProgrammeRepostry progRepo;
    @Autowired
    private IntakeRepostry intRepo;
    
	public University saveUniversity(University uni) {
		return unirepo.save(uni);
	}
	
	public Permission savePermission(Permission perm) {
		return permissionRepostry.save(perm);
	}
//with dto
	public College saveCollege(CollegeDTO coll) {
		College college=new College(coll.getColl_name(), coll.getUniversity());
		return collrepo.save(college);
	}
	//without dto
	public College saveCollegeWithout(College coll) {
		College college=new College(coll.getColl_name(), coll.getUniversity());
		return collrepo.save(college);
	}
	
	public Programme saveProgramme(Programme programme) {
		return progRepo.save(programme);
		
	}
	public Intake saveIntake(Intake intake) {
		return intRepo.save(intake);
	}
	public Course saveCourses(Course course) {
		return courseRepo.save(course);
	}
}
