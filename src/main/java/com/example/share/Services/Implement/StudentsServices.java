package com.example.share.Services.Implement;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.share.Entities.Requests;
import com.example.share.Entities.Student;
import com.example.share.Repositories.RequestRepostry;
import com.example.share.Repositories.StudentRepostry;

@Service
public class StudentsServices {
	@Autowired
	private StudentRepostry studeRepo;
	@Autowired
	private RequestRepostry reqRepo;
	
	
    public Collection<Student> selectStudents(Student student,String email) {
    	return studeRepo.findStudentsByEmail(email);
    }
    public Student selectStudent(String email) {
    	return studeRepo.findByEmail(email);
    }
    
    public Student saveRequests(String e1,long id) {
    	Student student=studeRepo.findByEmail(e1);
    	System.out.println(student);
    	System.out.println(id);
    	Student std2=studeRepo.findStById(id);
        Requests request=new Requests();
    		request.setStatus(1);
    		request.setStudentfrom(student);
    		request.setStudentTo(std2);
    		reqRepo.save(request);
    		System.out.println("duuh");
		return student;	
    }
    public Student findStudent(long id) {
    	return studeRepo.findStById(id);
    }
    public Collection<Student> selectRequests(int status,long ownid) {
		return studeRepo.findStudRequestByStatus(status, ownid);	
    }
    public Collection<Student> selectNewFriends(String email,int status,long toid) {
 		return studeRepo.findSuggestedStudNewFriedsByStatus(email, status,toid);	
     }
    public Collection<Student> selectBlockedFriends(String email,int status,long toid) {
  		return studeRepo.findBlockedstudentsByStatus(email, status, toid);	
      }
    public Collection<Student> whoConfirmRequest(String email,int status,long fromid) {
 		return studeRepo.findwhoConfirmFriedsByStatus(email, status, fromid);	
     }
    public Collection<Student> selectSuggestedRequests(String email,int status,int status1,int status2,int status3,long ownid) {
		return studeRepo.findSuggestedStudRequestByStatus(email,status,status1,status2,status3, ownid);	
    }
    public Student editRequestStatus(long toid,long fromid,int status) {
    	Student student=studeRepo.editRequestStatus(toid,fromid);
    	System.out.println(toid+" "+fromid);
    	Student std2=studeRepo.findStById(toid);
    	Student stdf=studeRepo.findStById(fromid);
    	Requests req=reqRepo.selectRequestById(toid, fromid,1);
    	Requests req2=reqRepo.selectRequestById(toid, fromid,0);
    	Requests req3=reqRepo.selectRequestById(toid, fromid,3);
    	Requests request=new Requests();
    	if(status==4) {
    		if(student==null) {
    			request.setStudentfrom(stdf);
    		}
    		else {
    			request.setStudentfrom(student);
    		}
    		
			if(req2!=null) {
				reqRepo.deleteById(req2.getId());
				
			}
			else if(req!=null) {
				reqRepo.deleteById(req.getId());
			}
			request.setStatus(status);
			
			request.setStudentTo(std2);
			reqRepo.save(request);
			return stdf;
		}
    	else {
    		request.setStudentfrom(student);
    	}
    	
    	if(status==3) {
    		if(req2!=null) {
				reqRepo.deleteById(req2.getId());
			}
    	
    	}	
    	request.setStatus(status);
		request.setStudentTo(std2);
		reqRepo.save(request);
	    
		
		if(status==0) {
			if(req3!=null) {
				reqRepo.deleteById(req3.getId());
			}
		}
		if(req!=null) {
			reqRepo.deleteById(req.getId());
		}
		
		return student;
    	
    }
}
