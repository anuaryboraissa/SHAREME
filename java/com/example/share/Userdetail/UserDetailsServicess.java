package com.example.share.Userdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.share.Entities.Student;
import com.example.share.Repositories.StudentRepostry;

public class UserDetailsServicess implements UserDetailsService {
	@Autowired
	private StudentRepostry strepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student=strepo.findByEmail(username);
		if(student==null) {
			throw new UsernameNotFoundException("could not find user with that email..");
		}
		return new UserDetailss(student);
	}

}
