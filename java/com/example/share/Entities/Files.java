package com.example.share.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private float size;
	private boolean isPublic;
	private boolean isPrivate;
	private boolean isTagged;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	

	public Files() {
		super();
	}

	public Files( String name, String type, String modifiedFileName, float size, boolean isPublic,
			boolean isPrivate, boolean isTagged) {
		super();
		this.file_name = name;
		this.type = type;
		ModifiedFileName = modifiedFileName;
		this.size = size;
		this.isPublic = isPublic;
		this.isPrivate = isPrivate;
		this.isTagged = isTagged;
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

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isTagged() {
		return isTagged;
	}

	public void setTagged(boolean isTagged) {
		this.isTagged = isTagged;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
}
