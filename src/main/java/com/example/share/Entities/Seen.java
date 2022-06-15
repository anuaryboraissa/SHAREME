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
@Table(name = "seen")
public class Seen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Seen_id")
	private long id;
	@ManyToMany(mappedBy="seen")
	private Collection<Student> student;
	@ManyToMany(mappedBy="seenn")
	private Collection<Messages> msg;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
