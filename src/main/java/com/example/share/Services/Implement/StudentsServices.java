package com.example.share.Services.Implement;

import java.time.LocalDate;
import java.time.LocalTime;



import java.time.ZoneId;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.share.Controller.DTOS.GroupDTO;
import com.example.share.Controller.DTOS.MessageDTO;
import com.example.share.Entities.Archieve;
import com.example.share.Entities.ClearMsgs;
import com.example.share.Entities.Delete;
import com.example.share.Entities.GroupAdmin;
import com.example.share.Entities.Groups;
import com.example.share.Entities.Grp_participantss;
import com.example.share.Entities.LeftGroup;
import com.example.share.Entities.Messages;
import com.example.share.Entities.RequestFrom;
import com.example.share.Entities.RequestTo;
import com.example.share.Entities.Requests;

import com.example.share.Entities.Seen;
import com.example.share.Entities.Student;
import com.example.share.Repositories.ArcieveRepo;
import com.example.share.Repositories.ClearRepo;
import com.example.share.Repositories.DeleteRepo;
import com.example.share.Repositories.GroupRepo;
import com.example.share.Repositories.GrpAdminRepo;
import com.example.share.Repositories.Grp_Partici_Repo;
import com.example.share.Repositories.LeftGroupRepo;
import com.example.share.Repositories.MessageRepo;
import com.example.share.Repositories.ReqFromRepo;
import com.example.share.Repositories.RequestRepostry;
import com.example.share.Repositories.RequestToRepo;
import com.example.share.Repositories.SeenRepo;
import com.example.share.Repositories.StudentRepostry;


@Service
public class StudentsServices {//
	@Autowired
	private Grp_Partici_Repo grp_part_repo;
	@Autowired
	private GrpAdminRepo adminrepo;
	@Autowired
	private DeleteRepo deteterepo;
	@Autowired
	private RequestToRepo reqtorepo;
	@Autowired
	private ReqFromRepo reqfromrepo;
	@Autowired
	private ArcieveRepo achrepo;
	@Autowired
	private SeenRepo seenRepo;
	@Autowired
	private StudentRepostry studeRepo;
	@Autowired
	private ClearRepo clearepo;
	@Autowired
	private RequestRepostry reqRepo;
	@Autowired
	private MessageRepo msgRepo;
	@Autowired
	private LeftGroupRepo leftsrepo;
	@Autowired
	private GroupRepo grprepo;
	public Collection<Archieve> getArchied(long id){
		return achrepo.getArchieved(id);
		
	}
	public Collection<Delete> getDeleted(long id){
		return deteterepo.getdeleted(id);
		
	}
	public Collection<Student> getGrpParticipantsById(long grpid){
		return studeRepo.findParticipantsById(grpid);
		
	}
	public Collection<Seen> getSeen(long id){
		return seenRepo.getSeen(id);
		
	}
	public Groups getGroupById(long id) {
		return grprepo.getById(id);
		
	}
	public Collection<Groups> getStudentGroups(long ownid) {
		return grprepo.getMyGroupsByStId(ownid);	
	}
    public Collection<Student> selectStudents(Student student,String email) {
    	return studeRepo.findStudentsByEmail(email);
    }
    public Collection<Student> getStudentLeft(long grp) {
    	return studeRepo.findStudentLeftGroupById(grp);
    }
    public Student selectStudent(String email) {
    	return studeRepo.findByEmail(email);
    }
    public LeftGroup getStudentLeftGrp(Student ownid,Groups grp) {
        LeftGroup lefts=new LeftGroup();
        lefts.setGroupsleft(grp);
        lefts.setStudentleft(ownid);;
       return  leftsrepo.save(lefts);
 }
    public void setStudentSeeGrpMsgs(Student ownid,Messages msgs) {
    	Seen see=new Seen();
        see.setMsg(msgs);
        see.setStudentsee(ownid);
      seenRepo.save(see);
 }
    public void setStudentClearGrpMsgs(Student ownid,Messages msgs) {
    	ClearMsgs clear=new ClearMsgs();
    	clear.setStudentclear(ownid);;
    	clear.setMsgs(msgs);;
    	clearepo.save(clear);
 }
    public Student saveRequests(String e1,long id,int status) {
    	RequestTo to=new RequestTo();
    	RequestFrom from=new RequestFrom();
    	Student student=studeRepo.findByEmail(e1);
    	System.out.println(student);
    	System.out.println(id);
    	Student std2=studeRepo.findStById(id);
        Requests request=new Requests();
    		request.setStatus(status);
    		request.setStudentfrom(student);
    		request.setStudentTo(std2);
    		reqRepo.save(request);
    		System.out.println("duuh");
    		to.setRequest(request);
    		to.setStd(std2);
    		to.setTime("0");
    		to.setDate("0");
    		reqtorepo.save(to);
    		from.setRequest(request);
    		from.setStd(student);
    		reqfromrepo.save(from);
		return student;	
    }
    public Collection<Messages> getSentMsgs(long from,long to,int status1,int status2) {
		return msgRepo.getAllMsgSentById(from,to);
    	
    }
    public Collection<Messages> getSpecificGrpMsgNotseen(long own,long grp) {
		return msgRepo.fingSpecifricGrpMsgNotSeenById(own, grp);
    	
    }
    public Collection<Messages> getIndividualMsgsArchieved(long own,int status1) {
  		return msgRepo.allIndividualMsgsAchievedById(own,status1);
      	
      }
    public Collection<Messages> allGrpMsgsAchievedById(long own,int status1) {
  		return msgRepo.allGrpMsgsAchievedById(own,status1);
      	
      }
    public Messages getMsgById(long id) {
  		return msgRepo.findMsgById(id);
      	
      }
    public Collection<Messages> allreciivOrSent(long ownid) {
  		return msgRepo.allMSGSentOrReceivById(ownid);
    }
    public Collection<Messages> allGroupMsgs(long grpid,int status2,long ownid) {
  		return msgRepo.getAllGroupMSGSById(grpid,ownid);
    }
    public Collection<Messages> getGroupSendBy(long from,long to,int status) {
  		return msgRepo.getAllGroupSendById(from, to);
      }
    public Collection<Messages> getGroupSentBy(long from,long to,int status) {
  		return msgRepo.getAllGroupSentById(from, to);
      }
    /*
    public Collection<Messages> getGroupMsgArchieved(long status,long ownid) {
  		return msgRepo.findAllgrpMsgsArchievedById(status,ownid);
      }*/
    public Collection<Messages> getAllMessagesReceived(long ownid,int status) {
  		return msgRepo.totalMessagesReceivedById(ownid);
      }
    public Collection<Messages> getAllMessagesSeen() {
  		return msgRepo.fingMsgSeenByidd();
      }
    public Seen getAllSeen(long own,long msg) {
  		return seenRepo.getIfSeenById(own, msg);
      }
    public Collection<Messages> getAllGroupMessagesReceived(long ownid,int status) {
  		return msgRepo.getAllReceivedGroupSendById(ownid);
      }
    public Collection<Messages> getAllGroupSpecificMessagesReceived(long ownid,long grp) {
  		return msgRepo.getAllReceivedSpecificGroupSendById(ownid, grp);
      }
    public Collection<Messages> getAllMsgSee(long ownid) {
  		return msgRepo.fingMsgSeenLoggerByidd(ownid);
      }
    public Collection<Messages> getAllGrpsAchieved(long ownid,int status1) {
  		return msgRepo.allGrpMsgsAchievedById(ownid,status1);
      }
    public Collection<Messages> getStudentsArchieved(long ownid,int status1) {
  		return msgRepo.allIndividualMsgsAchievedById(ownid,status1);
      }
    public Collection<Messages> getAllSentMsgs(long ownid) {
  		return msgRepo.allMsgsentById(ownid);
      }

 public Archieve updateGrpArchieve(Student ownid,Student achieved,Messages msg) {
        Archieve achieve=new Archieve();
    	System.out.println("mbn hawaukuu "+achieved.getFirst());
        achieve.setStdmsgs(achieved);
        achieve.setMsg(msg);
    	achieve.setStudentachiev(ownid);
    	achieve.setStatus(1);
    	return achrepo.save(achieve);
       }
   public Archieve checkIfAchieved(long own,long msg,int sts) {
	return achrepo.getIfArchieveById(own, msg, sts);
	 
    }
    public Delete deletemsgByid(Student ownid,Messages msgs,int status) {
    	
       Delete del=new Delete();
       del.setMsg(msgs);
       if(status==1) {
       del.setStatus(1);
       }
       if (status==2) {
    	   del.setStatus(2);
	}
    	del.setStudentdel(ownid);
      return deteterepo.save(del);	
    }
    
    public Collection<Messages> findByIdd(long msgid) {
    	Collection<Messages> msg=msgRepo.fingByidd(msgid);
             return msg;	
    }
    
    
    public Student saveMessage(MessageDTO msg,String e1) {
    	LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
        Student student=studeRepo.findByEmail(e1);

    	Messages message=new Messages();
    	message.setMsg(msg.getMsg());
    	message.setTime(localTime.getHour()+":"+localTime.getMinute());
    	message.setStdfrom(msg.getStdfrom());
    	
    	if(msg.getStdto()!=null) {
    	
    		message.setStdto(msg.getStdto());	
    	}
    	if(msg.getGroups()!=null) {
    		System.out.println("group id is "+msg.getGroups());
    		message.setGroups(msg.getGroups());
    	}
    	
    	msgRepo.save(message);
    	System.out.println("id yake "+message.getId());
    	System.out.println(msg.getMsg());
    	System.out.println("mgs saved");
    	
		return student;	
    }

    public Student findStudent(long id) {
    	return studeRepo.findStById(id);
    }
    public Groups findGroup(long id) {
    	return grprepo.getById(id);
    }
    public Collection<Student> selectRequests(int status,long ownid) {
		return studeRepo.findStudRequestByStatus(status, ownid);	
    }
    public Collection<Groups> groupArchieved() {
  		return grprepo.getAllGrpArchievedById();	
      }
    public Groups getGrpById(long id) {
    	return grprepo.getById(id);
    }
    public Collection<Student> selectAll() {
		return studeRepo.findAll();	
    }

    public Collection<Student> selectNewFriends(String email,int status,long toid) {
 		return studeRepo.findSuggestedStudNewFriedsByStatus(email, status,toid);	
     }
    
    public Collection<Student> selectFriendsNotGrpMember(String email,int status,long ownid,long grpid) {
 		return studeRepo.findFriendsThatAreNotGrpMemberdById(email, status, ownid, grpid);	
     }
    
    public Collection<Student> selectBlockedFriends(String email,int status,long toid) {
  		return studeRepo.findBlockedstudentsByStatus(email, status, toid);	
      }
    public Collection<Student> whoConfirmRequest(String email,int status,long fromid) {
 		return studeRepo.findwhoConfirmFriedsByStatus(email, status, fromid);	
     }
    public Collection<Student> whoSendMsgToMe(long ownid,int status,int status1) {
 		return studeRepo.findmgsWhoSendToMeById(ownid);
     }

    public Collection<Student> exceptLogger(long ownid) {
 		return studeRepo.findExceptsStdById(ownid);
     }
    public Collection<Student> goupAdmis(long grpid) {
 		return studeRepo.findGoupAdminsdById(grpid);
     }
    public Collection<Student> selectSuggestedRequests(String email,int status,int status1,int status2,int status3,long ownid) {
		return studeRepo.findSuggestedStudRequestByStatus(email,status,status1,status2,status3, ownid);	
    }
    public Student editRequestStatus(long toid,long fromid,int status) {
    	LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
    	LocalDate date=LocalDate.now();
    	RequestTo to=reqtorepo.findReqToById(toid, fromid);
    	Student student=studeRepo.editRequestStatus(toid,fromid);
    	System.out.println(toid+" "+fromid);
    	Student std2=studeRepo.findStById(toid);
    	Student stdf=studeRepo.findStById(fromid);
    	Requests req=reqRepo.selectRequestById(toid, fromid,1);
    	Requests req2=reqRepo.selectRequestById(toid, fromid,0);
    	Requests req3=reqRepo.selectRequestById(toid, fromid,3);
    	Requests req4=reqRepo.selectRequestById(toid, fromid,2);
    	Requests request=new Requests();
    	if(status==4) {
    		if(req4!=null) {
    			req4.setStudentfrom(student);
    			req4.setStatus(status);
    			req4.setStudentTo(std2);
    			reqRepo.save(req4);
    		}
    		else {
    			request.setStudentfrom(std2);
    			request.setStatus(status);
    			request.setStudentTo(stdf);
    			reqRepo.save(request);
    		}	
    		return stdf;
		}
    	else if(status==3) {
    		req2.setStudentfrom(student);
    		req2.setStatus(status);
    		req2.setStudentTo(std2);
    		reqRepo.save(req2);
    	}
    	else if(status==2) {
    		req.setStudentfrom(student);
    		req.setStatus(status);
    		req.setStudentTo(std2);
    		reqRepo.save(req);
    		to.setTime(localTime.getHour()+":"+localTime.getMinute());
    		to.setDate(date.toString());
    		reqtorepo.save(to);
    	}
    	else if(status==0) {
    		if(req3!=null) {
    			req3.setStudentfrom(student);
        		req3.setStatus(status);
        		req3.setStudentTo(std2);
        		reqRepo.save(req3);
        		
    		}
    		else {
    			req.setStudentfrom(student);
        		req.setStatus(status);
        		req.setStudentTo(std2);
        		reqRepo.save(req);
        		to.setTime(localTime.getHour()+":"+localTime.getMinute());
        		to.setDate(date.toString());
        		reqtorepo.save(to);
    		}
    	
		   }
    	else 
    	{   request.setStudentfrom(student);
    		request.setStatus(status);
    		request.setStudentTo(std2);
    		reqRepo.save(request);
    	}
    	
			return student;
    	
    }
  public Groups saveGroup(GroupDTO groupDTO,Student student) {//
	  GroupAdmin gadmn=new GroupAdmin();
	Grp_participantss gp=new Grp_participantss();
    Student std=studeRepo.findStById(student.getId());
    System.out.println("std ni "+std.getFirst());
    Groups groups=new Groups();
    LeftGroup left=new LeftGroup();
    groups.setCapacity(groupDTO.getCapacity());
    groups.setGrp_desc(groupDTO.getGrp_desc());
    groups.setGrp_name(groupDTO.getGrp_name());
    left.setStudent(groupDTO.getStudent());
//    groups.setGrp_admin(groupDTO.getGrp_admin());
    groups.setGrp_icon(groupDTO.getGrp_icon());
    Groups grp=grprepo.save(groups);
    gp.setGrpId(grp);
    for (Student sst : groupDTO.getStudent()) {
    	gp.setStdId(sst);
        grp_part_repo.save(gp);
	}
    gadmn.setGrpId(groups);
    gadmn.setStdId(student);
    adminrepo.save(gadmn);
    return grp;
    	
    }

}
