package com.example.share.Services.Implement;




import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.share.Controller.DTOS.StudentDTO;
import com.example.share.Entities.Permission;
import com.example.share.Entities.Roles;
import com.example.share.Entities.Student;
import com.example.share.Repositories.PermissionRepostry;
import com.example.share.Repositories.StudentRepostry;
import com.example.share.Services.UniversityService;
@Service
public class UniversityImpl implements UniversityService {
	@Autowired
	private BCryptPasswordEncoder encoder;
   @Autowired
   private StudentRepostry strepo;
   @Autowired
   private PermissionRepostry permrepo;
  
@Override
public Student saveUser(@Valid StudentDTO studentDTO) {
	 Permission permission=permrepo.findPerById(1);
	Student student=new Student(studentDTO.getFirst(),studentDTO.getPhotos(), studentDTO.getLast(),
			studentDTO.getEmail(),encoder.encode(studentDTO.getPassword()), studentDTO.getAge(),
			permission,
			studentDTO.getProgramme(),
			studentDTO.getCourses(),
			studentDTO.getRoles()
			);
	return strepo.save(student);
}

@Override
public Student saveUserWithoutDTO(Student student){
	return strepo.save(student);
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Student student=strepo.findByEmail(username);
    if(student==null) {
   	 throw new UsernameNotFoundException("invalid username or password");
    }
    return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(),
    		mapRolesToAuthorities(student.getRoles()));
}

private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
	// TODO Auto-generated method stub
	return roles.stream().map(role ->new SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList());
}
}
