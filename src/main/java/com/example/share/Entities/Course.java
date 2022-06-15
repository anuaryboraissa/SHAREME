package com.example.share.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

@Entity
@Table(name = "course")
public class Course {
	
	@NotBlank(message = "college is not in that format..")
	@Size(min = 5)
	@Column(name="course_name",nullable=false,unique = true,length = 20)
	private String course_name;
	@Size(min = 6)
	@NotBlank(message = "college is not in that format..")
	@Column(name="course_code",nullable=false,unique = true,length = 7)
	private String code;
	@NotEmpty
	@Column(name="course_instru",nullable = false)
	private String instructor;
	@NotNull
	@Column(name="course_Depart")
	private String department;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id")
	private long id;
	@Min(1)
	@Max(4)
	@Column(name="course_year")
	private int year;
	@Min(1)
	@Max(2)
	@Column(name="course_semister")
	private int semister;
	
	@Transient
	private List<MultipartFile> files=new ArrayList<MultipartFile>();
	
	@ManyToMany
	@JoinTable(
			name="course_prog",
			joinColumns= @JoinColumn(
					name="Course_Id",referencedColumnName="course_id"),
			inverseJoinColumns=@JoinColumn(
					name="Prog_Id",referencedColumnName="progr_id")
			  )
	private Collection<Programme> programme;
	
	@ManyToMany(mappedBy="courses")
	private Collection<Student> student;
	
	public Course() {
		super();
	}


	public Course(@NotBlank(message = "college is not in that format..") @Size(min = 8) String course_name,
			@Size(min = 8) @NotBlank(message = "college is not in that format..") String code,
			@NotEmpty String instructor, String department, @Min(1) @Max(4) int year, @Min(1) @Max(2) int semister) {
		super();
		this.course_name = course_name;
		this.code = code;
		this.instructor = instructor;
		this.department = department;
		this.year = year;
		this.semister = semister;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemister() {
		return semister;
	}

	public void setSemister(int semister) {
		this.semister = semister;
	}

	public Collection<Programme> getProgramme() {
		return programme;
	}

	public void setProgramme(Collection<Programme> programme) {
		this.programme = programme;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public Collection<Student> getStudent() {
		return student;
	}

	public void setStudent(Collection<Student> student) {
		this.student = student;
	}
	
}
