package com.example.share.Entities;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Requests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="request_id")
	private long id;
	@Column(name="request_status")
	private int status;
	@ManyToOne()
	@JoinColumn(name="stdfrom_id", referencedColumnName = "st_id")    
	private Student studentfrom;
	@ManyToOne()
	@JoinColumn(name="stdto_id", referencedColumnName = "st_id")    
	private Student studentTo;
	
	public Requests() {
		super();
	}
	
	public Requests(int status, Student studentfrom, Student studentTo) {
		super();
		this.status = status;
		this.studentfrom = studentfrom;
		this.studentTo = studentTo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Student getStudentfrom() {
		return studentfrom;
	}
	public void setStudentfrom(Student studentfrom) {
		this.studentfrom = studentfrom;
	}
	public Student getStudentTo() {
		return studentTo;
	}
	public void setStudentTo(Student std2) {
		this.studentTo = std2;
	}
	
}
