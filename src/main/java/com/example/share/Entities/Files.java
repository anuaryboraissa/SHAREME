package com.example.share.Entities;

import java.util.Collection;

import javax.persistence.Column;
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
@Table(name = "Resources")
public class Files {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rsc_id")
	private long Id;
	@Column(name="FileName",nullable=false)
    private String file_name;
	@Column(name="FileType")
    private String type;
	@Column(name="ModifiedFileName")
	private String ModifiedFileName;
	@Column(name="File_size")
	private String size;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	 @ManyToMany
		@JoinTable(
				name="File_Permissions",
				joinColumns= @JoinColumn(
						name="file_Id"),
				inverseJoinColumns=@JoinColumn(
						name="Permit_Id")
				  )
	private Collection<Permission> permission;

	public Files() {
		super();
	}

	public Files( String name, String type, String modifiedFileName, String size) {
		super();
		this.file_name = name;
		this.type = type;
		ModifiedFileName = modifiedFileName;
		this.size = size;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModifiedFileName() {
		return ModifiedFileName;
	}

	public void setModifiedFileName(String modifiedFileName) {
		ModifiedFileName = modifiedFileName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Collection<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Collection<Permission> permission) {
		this.permission = permission;
	}
	
	
}
