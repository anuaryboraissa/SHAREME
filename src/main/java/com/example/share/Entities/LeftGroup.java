package com.example.share.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "LeftGroup")
public class LeftGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="left_id")
	private long id;
	@ManyToOne()
	@JoinColumn(name="std_id", referencedColumnName = "st_id")    
	private Student studentleft;
	@ManyToOne()
	@JoinColumn(name="grp_id", referencedColumnName = "Group_id")    
	private Groups groupsleft;
	 @ManyToMany
		@JoinTable(
				name="std_left",
				joinColumns= @JoinColumn(
						name="left_id"),
				inverseJoinColumns=@JoinColumn(
						name="st_Id")
				  )
	private Collection<Student> student;
	
	 
	public Student getStudentleft() {
		return studentleft;
	}
	public void setStudentleft(Student studentleft) {
		this.studentleft = studentleft;
	}

	public Groups getGroupsleft() {
		return groupsleft;
	}
	public void setGroupsleft(Groups groupsleft) {
		this.groupsleft = groupsleft;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
