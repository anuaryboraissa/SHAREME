package com.example.share.Controller.DTOS;

import java.util.Collection;

import com.example.share.Entities.Groups;
import com.example.share.Entities.Student;

public class MessageDTO {
	 private Collection<Student> stdto;
	 private Collection<Student> stdfrom;
	 private Collection<Groups> groups;
	 
	public Collection<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Groups> groups) {
		this.groups = groups;
	}

	public Collection<Student> getStdto() {
		return stdto;
	}

	public void setStdto(Collection<Student> stdto) {
		this.stdto = stdto;
	}

	public Collection<Student> getStdfrom() {
		return stdfrom;
	}

	public void setStdfrom(Collection<Student> stdfrom) {
		this.stdfrom = stdfrom;
	}

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
