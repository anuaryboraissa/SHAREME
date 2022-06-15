package com.example.share.Controller.DTOS;

import java.util.Collection;

import com.example.share.Entities.Messages;
import com.example.share.Entities.Roles;
import com.example.share.Entities.Student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class GroupDTO {
	private String grp_name;
	private String grp_icon;
	private String grp_desc;
	private int capacity;
	private Collection<Student> student;
	private Collection<Student> grp_admin;
	private Collection<Messages> messages;
	private Collection<Roles> roles;
	
	public GroupDTO(String grp_name, String grp_icon, String grp_desc, int capacity, Collection<Student> student,
			Collection<Messages> messages) {
		super();
		this.grp_name = grp_name;
		this.grp_icon = grp_icon;
		this.grp_desc = grp_desc;
		this.capacity = capacity;
		this.student = student;
		this.messages = messages;
	}
public Collection<Student> getGrp_admin() {
		return grp_admin;
	}


	public void setGrp_admin(Collection<Student> grp_admin) {
		this.grp_admin = grp_admin;
	}


	public GroupDTO() {
		super();
	}
	
    public Collection<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}
	public String getGrp_name() {
		return grp_name;
	}
	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public String getGrp_icon() {
		return grp_icon;
	}
	public void setGrp_icon(String grp_icon) {
		this.grp_icon = grp_icon;
	}
	public String getGrp_desc() {
		return grp_desc;
	}
	public void setGrp_desc(String grp_desc) {
		this.grp_desc = grp_desc;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Collection<Student> getStudent() {
		return student;
	}
	public void setStudent(Collection<Student> student) {
		this.student = student;
	}
	public Collection<Messages> getMessages() {
		return messages;
	}
	public void setMessages(Collection<Messages> messages) {
		this.messages = messages;
	}
	
}
