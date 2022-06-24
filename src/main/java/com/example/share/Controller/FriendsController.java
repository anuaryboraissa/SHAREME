package com.example.share.Controller;

import java.io.IOException;

import java.security.Principal;
import java.time.LocalTime;
import java.time.ZoneId;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.share.Controller.DTOS.GroupDTO;
import com.example.share.Controller.DTOS.MessageDTO;
import com.example.share.Entities.Archieve;
import com.example.share.Entities.Course;
import com.example.share.Entities.Delete;
import com.example.share.Entities.FileAploadUtil;
import com.example.share.Entities.Files;
import com.example.share.Entities.Groups;
import com.example.share.Entities.LeftGroup;
import com.example.share.Entities.Messages;
import com.example.share.Entities.Requests;
import com.example.share.Entities.Seen;
import com.example.share.Entities.Student;
import com.example.share.Repositories.MessageRepo;
import com.example.share.Repositories.StudentRepostry;
import com.example.share.Services.Implement.FileServiceImple2;
import com.example.share.Services.Implement.StudentsServices;
import com.sun.source.tree.IfTree;

@Controller
public class FriendsController {
	@Autowired
	private FileServiceImple2 services;
	@Autowired
	private MessageRepo msrrepo;
	@Autowired
	private StudentRepostry stdrepol;
	@ModelAttribute("messag")
	public MessageDTO obtainStudent() {
		return new MessageDTO();
	}
	@Autowired
	private StudentsServices service;
    
	@RequestMapping(value = "/Gchating")
	public String friendsGroupChat(@ModelAttribute("toidd")
	long toidd,Model m,Authentication auth,HttpServletRequest request) {
		Groups grps=service.getGroupById(toidd);
		m.addAttribute("grp.id", toidd);
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Student> lefts=service.getStudentLeft(grps.getId());
		m.addAttribute("lefts",lefts);
		if (!lefts.contains(mystd)) {
			Collection<Messages> seenn=service.getAllMessagesSeen();
			m.addAttribute("seenn",seenn);
			Collection<Student> whosendMetxt=service.whoSendMsgToMe(mystd.getId(),1,1);
			Collection<Student> gpadmis=service.goupAdmis(grps.getId());
			m.addAttribute("admin",gpadmis);
			if (gpadmis.contains(mystd)) {
				m.addAttribute("hide",1);
			}
			Collection<Messages> msgs=service.getAllMessagesReceived(mystd.getId(),1);
			Collection<Messages> grpmsgs=service.getAllGroupMessagesReceived(mystd.getId(),1);
			Collection<Messages> sent=service.getGroupSentBy(mystd.getId(),grps.getId(),1);
			Collection<Messages> receivOrsent=service.allGroupMsgs(grps.getId(),1,1,mystd.getId());
			Collection<Messages> sendby=service.getGroupSendBy(mystd.getId(),grps.getId(),1);
			System.out.println(sendby);
			System.out.println("seen "+seenn);
			for (Messages messages : sendby) {
					if(seenn.isEmpty()) {
						service.setStudentSeeGrpMsgs(mystd, messages);
						 System.out.println("seen b4 content");
					}
					else if(!seenn.contains(messages)) {
						service.setStudentSeeGrpMsgs(mystd, messages);
						 System.out.println("seen after content");
					} 
							
					else {
						System.out.println("already seen");
					}
	}
	      	 Collection<Student> students=service.selectAll();
				  m.addAttribute("allstd",students);
				m.addAttribute("tmsgs", msgs);
				m.addAttribute("logger", mystd);
				m.addAttribute("tmsg", msgs.size()+grpmsgs.size());
				m.addAttribute("whosendMetxt",whosendMetxt);
				
				
				for (Messages messages : sendby) {
					for (Student sttt : messages.getStdfrom()) {
						m.addAttribute("pichhh",sttt.getPhotosImagePath());
					}
				}
				
				m.addAttribute("allsentRec", receivOrsent);
				m.addAttribute("totConver",(sendby.size()+sent.size()));
				for (Messages messages : sendby) {
					m.addAttribute("mess", messages.getId());
					System.out.println("msgid is "+messages.getId());
				}
				for (Student std : whosendMetxt) {
					m.addAttribute("toidd", std.getId());
					System.out.println("fromid is "+std.getId());
				}
				if(!sent.isEmpty()) {
					m.addAttribute("sent", sent);
				}

				m.addAttribute("namee",grps.getGrp_name());
				m.addAttribute("gstd",grps.getStudent());
				for (Student grp : grps.getStudent()) {
					m.addAttribute("name",grp.getFirst()+" "+grp.getLast());
					m.addAttribute("id",grp.getId());
					m.addAttribute("university", grp.getProgramme().getUniversity().getUn_name()+","+grp.getProgramme().getCollege().getColl_name());
					m.addAttribute("couse", grp.getCourses().size()+" courses");
					m.addAttribute("email", grp.getEmail());
					m.addAttribute("prog", grp.getProgramme().getProg_name());
				}
			  
				m.addAttribute("sendby", sendby);
				Messages message=new Messages();
				m.addAttribute("message", message);
				m.addAttribute("sents", sent.size());
				m.addAttribute("ehhh","3");
				

		}
		  m.addAttribute("userimage", mystd.getPhotosImagePath());
	m.addAttribute("first", mystd.getFirst());
	m.addAttribute("logggg", mystd);
	m.addAttribute("last", mystd.getLast());
	m.addAttribute("ownid", mystd.getId());
		m.addAttribute("ehh","2");
		m.addAttribute("picha", mystd.getPhotosImagePath());
		if(grps!=null) {
			Collection<Groups> stdgroups=service.getStudentGroups(mystd.getId());
			m.addAttribute("mygrps", stdgroups);
			m.addAttribute("toidg", grps.getId());
			m.addAttribute("from", grps.getGrp_name());
			
			m.addAttribute("picha2", grps.getPhotosImagePath());
			
			m.addAttribute("too", grps.getId());
		}
	
		
	
		LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
		System.out.println("time is "+localTime.getHour()+":"+localTime.getMinute());	
		return "mychat";

	}
	
	
	@RequestMapping(value = "/chating")
	public String friendsChat(@ModelAttribute("toid") long toid,Model m,Authentication auth,HttpServletRequest request) {
		Student student=service.findStudent(toid);
		Collection<Seen> seen=service.getSeen(2);
		m.addAttribute("st.id", toid);
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
        Collection<Student> whosendMetxt=service.whoSendMsgToMe(mystd.getId(),1,1);
		Collection<Messages> msgs=service.getAllMessagesReceived(mystd.getId(),1);
		System.out.println("bdo seen "+msgs.size());
		for (Messages messages : msgs) {
			System.out.println("bdo seen "+messages.getMsg());
		}
		Collection<Groups> stdgroups=service.getStudentGroups(mystd.getId());
		Collection<Messages> grpmsgs=service.getAllGroupMessagesReceived(mystd.getId(),1);
		Collection<Messages> sent=service.getSentMsgs(mystd.getId(),student.getId(),1,1);
		
		Collection<Messages> sendby=service.getSentMsgs(student.getId(),mystd.getId(),1,1);
		Collection<Messages> receivOrsent=service.allreciivOrSent(mystd.getId());
		Collection<Messages> seenn=service.getAllMessagesSeen();
		m.addAttribute("seenn",seenn);
		for (Messages messages : sendby) {
			if(seenn.isEmpty()) {
				service.setStudentSeeGrpMsgs(mystd, messages);
				 System.out.println("seen b4 content");
			}
			else if(!seenn.contains(messages)) {
				service.setStudentSeeGrpMsgs(mystd, messages);
				 System.out.println("seen after content");
			} 
					
			else {
				System.out.println("already seen");
			}
}
		m.addAttribute("mygrps", stdgroups);
		if (student!=null) {
			for (Messages msgsss : student.getMsgfrom()) {
				for (Student std : msgsss.getStdto()) {
					if(std.getId()==mystd.getId()) {
						System.out.println("msg id is "+toid);
						System.out.println("msg is "+msgsss.getMsg());
					
					   
					}else {
						System.out.println("amna msg ");
					}
				}
		
			}
			m.addAttribute("tmsgs", msgs);
			
			m.addAttribute("tmsg", msgs.size()+grpmsgs.size());
			m.addAttribute("whosendMetxt",whosendMetxt);
		
			m.addAttribute("allsentRec", receivOrsent);
			m.addAttribute("totConver",(sendby.size()+sent.size()));
			for (Messages messages : sendby) {
				m.addAttribute("mess", messages.getId());
				System.out.println("msgid is "+messages.getId());
			}
			for (Student std : whosendMetxt) {
				m.addAttribute("toidd", std.getId());
				System.out.println("fromid is "+std.getId());
			}
			if(!sent.isEmpty()) {
				m.addAttribute("sent", sent);
			}

			m.addAttribute("namee", student.getFirst()+" "+student.getLast());
			m.addAttribute("agee", student.getAge());
			int yr=0;
			for (Course cs : student.getCourses()) {
				yr=cs.getYear();
			}
			m.addAttribute("yearr",yr);
			m.addAttribute("ehh","1");
			m.addAttribute("id",student.getId());
			m.addAttribute("university", student.getProgramme().getUniversity().getUn_name()+","+student.getProgramme().getCollege().getColl_name());
			m.addAttribute("couse", student.getCourses().size()+" courses");
			m.addAttribute("email", student.getEmail());
			m.addAttribute("prog", student.getProgramme().getProg_name());

			m.addAttribute("userimage", mystd.getPhotosImagePath());
			m.addAttribute("first", mystd.getFirst());
			m.addAttribute("last", mystd.getLast());
			m.addAttribute("ownid", mystd.getId());
			m.addAttribute("sendby", sendby);
			Messages message=new Messages();
			m.addAttribute("message", message);
			m.addAttribute("sents", sent.size());
			System.out.println("name is "+student.getFirst());
			
				m.addAttribute("toid", student.getId());
				m.addAttribute("from", student.getFirst()+" "+student.getLast());
				m.addAttribute("picha2", student.getPhotosImagePath());
				m.addAttribute("too", student.getId());
		
			m.addAttribute("picha", mystd.getPhotosImagePath());

			Collection<Student> except=service.selectBlockedFriends(userPrincipal.getName(),0,mystd.getId());
			if(except.contains(student)) {
				System.out.println("ipooooo");
				m.addAttribute("ipo",except); 
			}
		}

	
		LocalTime localTime=LocalTime.now(ZoneId.of("GMT+02:59"));
		System.out.println("time is "+localTime.getHour()+":"+localTime.getMinute());	
		return "mychat";

	}
	@GetMapping("/chat")
	public String getChat(Model m,Authentication auth,HttpServletRequest request,String who) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Student> whosendMetxt=service.whoSendMsgToMe(mystd.getId(),1,1);
		Collection<Student> searchWhosendTome=stdrepol.searchmgsWhoSendToMeByKey(mystd.getId(),1,1,who);
		Collection<Messages> msgss=service.getAllMessagesReceived(mystd.getId(),1);
		Collection<Groups> stdgroups=service.getStudentGroups(mystd.getId());
		Collection<Messages> grpmsgs=service.getAllGroupMessagesReceived(mystd.getId(),1);
		m.addAttribute("mygrps", stdgroups);
		m.addAttribute("tmsgs", msgss);
		m.addAttribute("ownid", mystd.getId());
		m.addAttribute("tmsg", msgss.size()+grpmsgs.size());
		if (who==null) {
			m.addAttribute("whosendMetxt",whosendMetxt);
		} else {
			m.addAttribute("who",who);
			m.addAttribute("whosendMetxt",searchWhosendTome);
		}
	
		m.addAttribute("userimage", mystd.getPhotosImagePath());
		m.addAttribute("first", mystd.getFirst());
		m.addAttribute("last", mystd.getLast());

		Messages message=new Messages();
		m.addAttribute("message", message);
		return "mychat";	
	}

	@RequestMapping(value = "/more")
	public String moreDetails(@RequestParam(value ="more") long id,Model m,
			RedirectAttributes redirect) {
		Student student=service.findStudent(id);
		Collection<Files> pubfiles=services.getFileHistory(3, student.getId());
		Collection<Files> taggedfiles=services.getFileHistory(5, student.getId());
		int yr = 0,sem = 0;
		for (Course cs : student.getCourses()) {
			yr=cs.getYear();
			sem=cs.getSemister();

		}

		if (student!=null) {
			redirect.addFlashAttribute("anu","1");
		}
		Collection<Student> frindsss=service.selectNewFriends(student.getEmail(), 0,student.getId());
		redirect.addFlashAttribute("name",student.getFirst()+" "+student.getLast());
		redirect.addFlashAttribute("age",student.getAge()+" yrs");
		redirect.addFlashAttribute("year",yr);
		redirect.addFlashAttribute("email",student.getEmail());
		redirect.addFlashAttribute("closee",frindsss);
		redirect.addFlashAttribute("photo",student.getPhotosImagePath());
		redirect.addFlashAttribute("stdid",student.getId());
		redirect.addFlashAttribute("sem",sem);
		redirect.addFlashAttribute("share",pubfiles.size()+" public files, "+taggedfiles.size()+" tagged files");
		redirect.addFlashAttribute("couse",student.getCourses().size()+" Courses");
		redirect.addFlashAttribute("progr",student.getProgramme().getProg_name());
		redirect.addFlashAttribute("University",student.getProgramme().getUniversity().getUn_name()+", "+student.getProgramme().getCollege().getColl_name());

		return "redirect:/friends";

	}
	@ModelAttribute("group")
	public GroupDTO obtainGroups() {
		return new GroupDTO();
	}
	@PostMapping("/savegrp")
	public String registerGroup(@Valid
			@ModelAttribute("group") GroupDTO groupDTO,BindingResult bindingResult
			,RedirectAttributes attributes,Authentication auth,HttpServletRequest request, @RequestParam("image") MultipartFile file
			) throws IOException  {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		String filename=org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		byte[] imageData = file.getBytes();
		groupDTO.setGrp_icon(imageData);
		Groups saveGroup=service.saveGroup(groupDTO, mystd);
		String uploadDir="src/main/resources/static/img1/GrpIcons/"+saveGroup.getId();
		FileAploadUtil.saveFile(uploadDir, filename, file);
		System.out.println(" group success");
		attributes.addFlashAttribute("messagee", "group created saccessfully");
		return "redirect:/friends";

	}

	@RequestMapping(value = "/moreD")
	public String moreReqDetails(@RequestParam(value ="morer") long id,
			RedirectAttributes redirect) {
		Student student=service.findStudent(id);
		Collection<Files> pubfiles=services.getFileHistory(3, student.getId());
		Collection<Files> taggedfiles=services.getFileHistory(5, student.getId());
		int yr = 0,sem = 0;
		for (Course cs : student.getCourses()) {
			yr=cs.getYear();
			sem=cs.getSemister();

		}

		if (student!=null) {
			redirect.addFlashAttribute("anuu","1");
		}
		Collection<Student> frindsss=service.selectNewFriends(student.getEmail(), 0,student.getId());
		redirect.addFlashAttribute("name",student.getFirst()+" "+student.getLast());
		redirect.addFlashAttribute("age",student.getAge()+" yrs");
		redirect.addFlashAttribute("year",yr);
		redirect.addFlashAttribute("email",student.getEmail());
		redirect.addFlashAttribute("closee",frindsss);
		redirect.addFlashAttribute("photo",student.getPhotosImagePath());
		redirect.addFlashAttribute("stdid",student.getId());
		redirect.addFlashAttribute("sem",sem);
		redirect.addFlashAttribute("share",pubfiles.size()+" public files, "+taggedfiles.size()+" tagged files");
		redirect.addFlashAttribute("couse",student.getCourses().size()+" Courses");
		redirect.addFlashAttribute("progr",student.getProgramme().getProg_name());
		redirect.addFlashAttribute("University",student.getProgramme().getUniversity().getUn_name()+", "+student.getProgramme().getCollege().getColl_name());
		return "redirect:/friends";

	}


	
	@PostMapping("/msgreq")
	public String sandMsg(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@ModelAttribute("messag") MessageDTO msg) {
		Principal userPrincipal = request.getUserPrincipal();
		Student sd=service.saveMessage(msg,userPrincipal.getName());
		String ametumiwa=null;
		if(!msg.getStdto().isEmpty()) {
		for (Student sttt : msg.getStdto()) {
			ametumiwa=sttt.getFirst();
			System.out.println("ametumiwa "+sttt.getFirst());
			   redirect.addFlashAttribute("toid",sttt.getId());
		 }
		if(sd!=null) {
			System.out.println("success");
			redirect.addFlashAttribute("messagee", "message sent to "+ametumiwa);
		}
		
		}
		if (!msg.getGroups().isEmpty()) {
			for (Groups grp : msg.getGroups()) {
			ametumiwa=grp.getGrp_name();
				System.out.println("ametumiwa "+grp.getGrp_name());
				redirect.addFlashAttribute("toidd",grp.getId());
			}
			if(sd!=null) {
				System.out.println("success");
				redirect.addFlashAttribute("messagee", "message sent to "+ametumiwa);
			}
			return "redirect:/Gchating";
		}
		return "redirect:/chating";
		
	}
	@PostMapping("/achieve")
	public String achieveMsg(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("toid") long msgid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student student=service.findStudent(msgid);
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Messages> sent=service.getSentMsgs(mystd.getId(),student.getId(),1,1);
		Collection<Messages> sendby=service.getSentMsgs(student.getId(),mystd.getId(),1,1);
		if(!sendby.isEmpty() || !sent.isEmpty()) {
			Collection<Messages> msgs=service.updateArchieve(mystd.getId(), student.getId(),1);
			mystd.setAchieve(msgs);
			stdrepol.save(mystd);
			if(msgs!=null) {
				System.out.println("success");
				redirect.addFlashAttribute("messagee", "message achieved");
				redirect.addFlashAttribute("mojaa", "1");
			}
			else {
				System.out.println("error");
				redirect.addFlashAttribute("messagee", "message already achieved previous time");
				redirect.addFlashAttribute("moja", "1");
			}
		}
		else {
			System.out.println("error");
			redirect.addFlashAttribute("messagee", "message already achieved previous time");
			redirect.addFlashAttribute("moja", "1");
		}
		return "redirect:/chat";
	}
	
	@PostMapping("/gclear")
	public String clearGroupMsgs(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("gid") long gid) {
		Principal userPrincipal = request.getUserPrincipal();
		Groups grps=service.getGroupById(gid);
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Messages> sent=service.getGroupSentBy(mystd.getId(),grps.getId(),1);
		Collection<Messages> sendby=service.getGroupSendBy(mystd.getId(),grps.getId(),1);
		Collection<Messages> receivOrsent=service.allGroupMsgs(grps.getId(),1,1,mystd.getId());
		if(!sendby.isEmpty() || !sent.isEmpty()) {
			for (Messages messages : receivOrsent) {
				service.setStudentClearGrpMsgs(mystd,messages);
				System.out.println("clear success");
			}
		
		}
		else {
			System.out.println("error");
			redirect.addFlashAttribute("messagee", "message already cleared previous time");
			redirect.addFlashAttribute("moja", "1");
		}
		return "redirect:/chat";
	}
	
	@PostMapping("/gachieve")
	public String achieveGroupMsgs(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("gid") long gid) {
		Principal userPrincipal = request.getUserPrincipal();
		Groups grps=service.getGroupById(gid);
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Messages> sent=service.getGroupSentBy(mystd.getId(),grps.getId(),1);
		Collection<Messages> sendby=service.getGroupSendBy(mystd.getId(),grps.getId(),1);
		if(!sendby.isEmpty() || !sent.isEmpty()) {
			Collection<Messages> msgs=service.updateGrpArchieve(mystd.getId(), grps.getId(),1,1,0);
			mystd.setAchieve(msgs);
			stdrepol.save(mystd);
			if(msgs!=null) {
				System.out.println("success");
				redirect.addFlashAttribute("messagee", "message achieved");
				redirect.addFlashAttribute("mojaa", "1");
			}
			else {
				System.out.println("error");
				redirect.addFlashAttribute("messagee", "message already achieved previous time");
				redirect.addFlashAttribute("moja", "1");
			}
		}
		else {
			System.out.println("error");
			redirect.addFlashAttribute("messagee", "message already achieved previous time");
			redirect.addFlashAttribute("moja", "1");
		}
		return "redirect:/chat";
	}

	
	@PostMapping("/unachieve")
	public String unachieveMsg(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("from") long fromid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student student=service.findStudent(fromid);
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Messages> msgs=service.updateArchieve(mystd.getId(),student.getId(),2);
		if(msgs!=null) {
			System.out.println("success");
			redirect.addFlashAttribute("messagee", "message un-archieved");
		}
		else {
			System.out.println("error");
			redirect.addFlashAttribute("messagee", "message already un achieved previous time");
			redirect.addFlashAttribute("moja", "1");
		}

		return "redirect:/chat";
	}
	@PostMapping("/gunachieve")
	public String groupunachieveMsg(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("gid") long gid) {
		Principal userPrincipal = request.getUserPrincipal();
		Groups grps=service.getGroupById(gid);
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
			Collection<Messages> msgs=service.updateGrpArchieve(mystd.getId(), grps.getId(),2,1,0);
			if(msgs!=null) {
				System.out.println("success");
				redirect.addFlashAttribute("messagee", "message un-achieved");
			}
			else {
				System.out.println("error");
				redirect.addFlashAttribute("messagee", "message already un-achieved previous time");
				redirect.addFlashAttribute("moja", "1");
			}
		return "redirect:/chat";
	}

	@PostMapping("/deletemsg")
	public String deleteMsg(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("toid") long msgid,Messages deleteMsg) {
		Student student=service.findStudent(msgid);
		Principal userPrincipal = request.getUserPrincipal();
		System.out.println("deletee");
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Messages> sendby=service.getSentMsgs(student.getId(),mystd.getId(),1,1);
		Collection<Messages> sent=service.getSentMsgs(mystd.getId(),student.getId(),1,1);
		for (Messages messages : sendby) {
			deleteMsg=service.deletemsgByid(msgid,mystd.getId(),messages.getId());
			mystd.setDeletee(sendby);
			stdrepol.save(mystd);
		}
		for (Messages messages : sent) {
			deleteMsg=service.deletemsgByid(msgid,mystd.getId(),messages.getId());
			System.out.println("msgid is "+messages.getId());
		}
		if(deleteMsg!=null) {
			System.out.println("deleted");
			redirect.addFlashAttribute("messagee", "message deleted");
		}
		return "redirect:/chat";
	}
	@PostMapping("/deletefrommsg")
	public String deleteMsgFrom(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("toid") long msgid,Messages deleteMsg) {
		System.out.println("idii  ni"+msgid);
		Student student=service.findStudent(msgid);

		Principal userPrincipal = request.getUserPrincipal();
		System.out.println("deletee");
		Student mystd=stdrepol.findByEmail(userPrincipal.getName());
		Collection<Messages> sendby=service.getSentMsgs(mystd.getId(),student.getId(),1,1);
		for (Messages messages : sendby) {
			deleteMsg=service.deletemsgByid(mystd.getId(),msgid,messages.getId());
			System.out.println("msgid is "+messages.getId());
		}
		if(deleteMsg!=null) {
			System.out.println("deleted");
			redirect.addFlashAttribute("messagee", "message deleted");
		}
		return "redirect:/chat";
	}
	@GetMapping("/friends")
	public String viewFrinds(Model m,Authentication auth,HttpServletRequest request,
			RedirectAttributes direct,String friend,String blockedd,String suggest) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		m.addAttribute("userimage", mystd.getPhotosImagePath());
		m.addAttribute("first", mystd.getFirst());
		m.addAttribute("last", mystd.getLast());
		m.addAttribute("login", mystd.getId());
		m.addAttribute("sdd", mystd);
		Student stud=new Student();
		m.addAttribute("stud", stud);
		Requests reques=new Requests();
		m.addAttribute("reques", reques);
		Collection<Student> listOfStud=service.selectSuggestedRequests(userPrincipal.getName(), 1,0,4,3,mystd.getId());
		
		Collection<Student> requests=service.selectRequests(1,mystd.getId());
		Collection<Student> newFriends=service.selectNewFriends(userPrincipal.getName(), 0,mystd.getId());
		Collection<Student> blocked=service.selectBlockedFriends(userPrincipal.getName(),3,mystd.getId());
		Collection<Student> except=service.selectBlockedFriends(userPrincipal.getName(),0,mystd.getId());
		Collection<Student> whoConfirm=service.whoConfirmRequest(userPrincipal.getName(), 0, mystd.getId());
		Collection<Student> whoConcel=service.whoConfirmRequest(userPrincipal.getName(), 2, mystd.getId());
		m.addAttribute("notisize",(whoConfirm.size()+whoConcel.size()));
		m.addAttribute("whoConfirm",whoConfirm);
		if(except.isEmpty()) {
			m.addAttribute("v1", "1");
			System.out.println("ni 1");
		} 
		else {
			m.addAttribute("v1",except); 
		}

		m.addAttribute("whoConcel",whoConcel);
		 Collection<Student> serchedFr=stdrepol.searchByKey(userPrincipal.getName(),0,mystd.getId(),friend);
		 Collection<Student> searchBlocked=stdrepol.searchBlockeByKey(userPrincipal.getName(),3,mystd.getId(),blockedd);
		 Collection<Student> searchsuggested=stdrepol.searchSuggestedByKey(suggest,userPrincipal.getName(), 1,0,4,3,mystd.getId());
		if (friend==null) {
			m.addAttribute("newFriends",newFriends);
		} else {
			m.addAttribute("friend",friend);
			m.addAttribute("newFriends",serchedFr);
		}
		if (blockedd==null) {
			m.addAttribute("blocked", blocked);
		} else {
			m.addAttribute("blockedd",blockedd);
			m.addAttribute("blocked",searchBlocked);
		}
		if (suggest==null) {
			m.addAttribute("listOfStudents", listOfStud);
		} else {
			m.addAttribute("suggest",suggest);
			m.addAttribute("listOfStudents",searchsuggested);
		}
		
		
		m.addAttribute("requests", requests);
		
		
		return "friends";
	}
	@PostMapping("/sendReq")
	public String sandRequests(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("stid") long id) {
		Principal userPrincipal = request.getUserPrincipal();
		Student student=service.findStudent(id);
		Student  student1=service.saveRequests(userPrincipal.getName(), id,1);
		if(student1!=null) {
			System.out.println("success");
			redirect.addFlashAttribute("message", "Request Sent to "+student.getFirst());
		}
		return "redirect:/friends";
	}
	@PostMapping("/updateReq")
	public String editRequests(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("fromid") long fromid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),fromid,2);
		if(student!=null) {
			System.out.println("updated..");
			redirect.addFlashAttribute("message", "Request from "+student.getFirst()+" Canceled");
		}
		return "redirect:/friends";

	}
	@PostMapping("/Reqconcel")
	public String editRequest(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("conf") long concid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),concid,0);
		if(student!=null) {
			System.out.println("updated..");
			redirect.addFlashAttribute("message", "Now Friends to "+student.getFirst());
		}
		return "redirect:/friends";

	}
	@PostMapping("/blockSt")
	public String blockSt(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("block") long blockid) {
		System.out.println(blockid);
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),blockid,3);
		if(student!=null) {
			System.out.println("blocked..");
			redirect.addFlashAttribute("message",student.getFirst()+" is no longer your friend");
		}
		return "redirect:/friends";

	}
	@PostMapping("/removeSugg")
	public String removeSuggestion(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("remove") long remid) {
		System.out.println(remid);
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Student student=service.editRequestStatus(mystd.getId(),remid,4);
		if(student!=null) {
			System.out.println("blocked..");
			redirect.addFlashAttribute("message",student.getFirst()+" Doesn't appear any more");
		}
		return "redirect:/friends";

	}
	@PostMapping("/left")
	public String leeftGroup(Authentication auth,HttpServletRequest request,RedirectAttributes redirect
			,@RequestParam("gid") long gid) {
		Principal userPrincipal = request.getUserPrincipal();
		Student mystd=service.selectStudent(userPrincipal.getName());
		Groups grps=service.getGroupById(gid);
		LeftGroup grp=service.getStudentLeftGrp(mystd,grps);
		if(grp!=null) {
			System.out.println("you left group "+grps.getGrp_name());
			redirect.addFlashAttribute("messagee","successfull left from "+grps.getGrp_name());
		}
		return "redirect:/chat";

	}


}
