package com.example.share.Controller.DTOS;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.share.Entities.College;
import com.example.share.Entities.Course;
import com.example.share.Entities.Permission;
import com.example.share.Entities.Programme;
import com.example.share.Entities.Roles;
import com.example.share.Entities.University;

public class StudentDTO {
	private long id;
	private String first;
	private String last;
	private String email;
	private String password;
	private String photos;
	private int age;
	private boolean enabled;  
	private Programme programme;
	 private Collection<Course> courses;
	@Autowired
	private Permission permission;
	Collection<Roles> roles;
	public StudentDTO() {
		super();
	}
	

	public StudentDTO(String first, String last, String email, String password, int age, boolean enabled,
			 Programme programme, Collection<Course> courses,
			Permission permission, Collection<Roles> roles) {
		super();
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
		this.age = age;
		this.enabled = enabled;
		this.programme = programme;
		this.courses = courses;
		this.permission = permission;
		this.roles = roles;
	}

	public StudentDTO(String first, String last, String email, String password, String photos, int age, boolean enabled,
			Programme programme, Collection<Course> courses, Permission permission,
			Collection<Roles> roles) {
		super();
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
		this.photos = photos;
		this.age = age;
		this.enabled = enabled;
		this.programme = programme;
		this.courses = courses;
		this.permission = permission;
		this.roles = roles;
	}




	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public Collection<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

	public Permission getPermission() {
		return new Permission(1);
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "StudentDTO [first=" + first + ", last=" + last + ", email=" + email + ", password=" + password
				+ ", age=" + age + ", enabled=" + enabled
				+ ", programme=" + programme + ", courses=" + courses + ", permission="
				+ permission + "]";
	}
	
	
	
}
