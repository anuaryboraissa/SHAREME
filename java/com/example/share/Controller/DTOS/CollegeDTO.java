package com.example.share.Controller.DTOS;

import com.example.share.Entities.University;

public class CollegeDTO {
	private String coll_name;
	private University university;
	
	public CollegeDTO() {
		super();
	}
	public CollegeDTO(String coll_name, University university) {
		super();
		this.coll_name = coll_name;
		this.university = university;
	}
	public String getColl_name() {
		return coll_name;
	}
	public void setColl_name(String coll_name) {
		this.coll_name = coll_name;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	@Override
	public String toString() {
		return "CollegeDTO [coll_name=" + coll_name + ", university=" + university + "]";
	}
	
	
}
