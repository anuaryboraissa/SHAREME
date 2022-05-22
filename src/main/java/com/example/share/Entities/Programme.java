package com.example.share.Entities;


import java.util.Collection;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.data.relational.core.sql.FalseCondition;

import com.sun.istack.NotNull;

import lombok.NonNull;

@Entity
@Table(name = "programme")
public class Programme {
	@Column(name="progr_name",nullable=false)
	private String prog_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="progr_id")
	private long id;
	@NotNull
	@Min(2)
	@Column(name="progr_years",nullable=false)
	private int years;
	@Column(name="progr_cr")
	private String cr;
	
	@OneToMany(targetEntity=Student.class, mappedBy="programme",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Student> student;
	
	@ManyToOne()
	@JoinColumn(name="college_id", referencedColumnName = "coll_id")    
	private College college;
	
	@ManyToOne()
	@JoinColumn(name="uni_id", referencedColumnName = "uni_id")    
	private University university;
	
	@ManyToMany(mappedBy="programme")
	private Collection<Course> courses;

	public Programme() {
		super();
	}

	public Programme(String name, int years, String cr) {
		super();
		this.prog_name = name;
		this.years = years;
		this.cr = cr;
	}


	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public String getProg_name() {
		return prog_name;
	}

	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public Collection<Student> getStudent() {
		return student;
	}

	public void setStudent(Collection<Student> student) {
		this.student = student;
		student.forEach(students ->
		students.setProgramme(this));
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	
	
}
