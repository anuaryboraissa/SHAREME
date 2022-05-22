package com.example.share.Userdetail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.share.Entities.Roles;
import com.example.share.Entities.Student;

public class UserDetailss implements UserDetails{
	Student student;
	public UserDetailss(Student student) {
		super();
		this.student = student;
	}
	public Student getStudent() {
		return this.student;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  Collection<Roles> roles = student.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (Roles role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
	        }
	        return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return student.getPassword();
	}
	

	public String getLastName() {
		return student.getLast();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return student.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
