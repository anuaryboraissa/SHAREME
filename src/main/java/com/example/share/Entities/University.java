package com.example.share.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "university")
public class University {
	@NotBlank(message = "university must not be empty")
	@Size(max = 10,min = 5)
	@Column(name="Uni_name",nullable=false)
	private String un_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Uni_id")
	private long id;
	@OneToMany(targetEntity=College.class, mappedBy="university",cascade=CascadeType.ALL, fetch = FetchType.EAGER)    
	private Collection<College> college;
	
	@OneToMany(targetEntity=Programme.class, mappedBy="university",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Programme> programme;
	
	@OneToMany(targetEntity=Intake.class, mappedBy="university",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Intake> intake;
	
	
	public University() {
		super();
	}


	public University(String un_name) {
		super();
		this.un_name = un_name;
	}

	public University(String un_name, long id, Collection<College> college,Collection<Intake> intake) {
		super();
		this.un_name = un_name;
		this.id = id;
		this.college = college;
		this.intake = intake;
	}


	public Collection<Intake> getIntake() {
		return intake;
	}


	public void setIntake(Collection<Intake> intake) {
		this.intake = intake;
	}


	public Collection<Programme> getProgramme() {
		return programme;
	}


	public void setProgramme(Collection<Programme> programme) {
		this.programme = programme;
	}


	public String getUn_name() {
		return un_name;
	}


	public void setUn_name(String un_name) {
		this.un_name = un_name;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Collection<College> getCollege() {
		return college;
	}
	public void setCollege(Collection<College> college) {
		this.college = college;
		college.forEach((colleges) ->{
		colleges.setUniversity(this);
		});
	}
	
}
