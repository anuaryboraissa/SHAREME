package com.example.share.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "intake")
public class Intake {
	@NotEmpty
	@Column(name="intake_name",nullable=false)
	private String int_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="intake_id")
	private long id;
	@Min(2015)
	@Max(2023)
	@Column(name="intake_year",nullable=false)
	private int year;
	
	@ManyToOne()
	@JoinColumn(name="uni_id", referencedColumnName = "uni_id")    
	private University university;
	

	public Intake() {
		super();
	}

	public Intake(String int_name, int year) {
		super();
		this.int_name = int_name;
		this.year = year;
	}



	public String getInt_name() {
		return int_name;
	}


	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public void setInt_name(String int_name) {
		this.int_name = int_name;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	
}
