package com.example.share.Entities;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "permissions")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="permit_id")
	private long Id;
	@Size(max = 50,min = 5)
    @Column(name="permit_name",nullable=false,length = 50)
	private String P_name;
	@Transient
	private int status=1;
    
    @ManyToMany(mappedBy="permission")
	private Set<Files> files;

    @OneToMany(targetEntity=Student.class, mappedBy="permission",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Student> student;
	public Permission() {
		super();
	}
	public Permission(String name) {
		super();
		this.P_name = name;
	}
	

	public Permission(int status) {
		super();
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Collection<Student> getStudent() {
		return student;
	}
	public void setStudent(Collection<Student> student) {
		this.student = student;
	}
	public Set<Files> getFiles() {
		return files;
	}

	public void setFiles(Set<Files> files) {
		this.files = files;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getP_name() {
		return P_name;
	}

	public void setP_name(String p_name) {
		P_name = p_name;
	}


    
    
}
