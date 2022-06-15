package com.example.share.Entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "achieve")
public class Archieve {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Achieve_id")
	private long id;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToMany(mappedBy="achieve")
	private Collection<Student> student;
	@ManyToMany(mappedBy="achievedd")
	private Collection<Messages> msg;
	
	public Collection<Messages> getMsg() {
		return msg;
	}
	public void setMsg(Collection<Messages> msg) {
		this.msg = msg;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Collection<Student> getStudent() {
		return student;
	}
	public void setStudent(Collection<Student> student) {
		this.student = student;
	}
	
}
