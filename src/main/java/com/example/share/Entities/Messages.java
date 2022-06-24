package com.example.share.Entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Messages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="msg_id")
	private long id;
	@Column(name="user_msg",nullable = false)
	private String msg;
	@Column(name="date")
    private String time;
	@OneToMany(targetEntity=Seen.class, mappedBy="studentsee",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Seen> msgseen;
	@OneToMany(targetEntity=ClearMsgs.class, mappedBy="msgs",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<ClearMsgs> msgcleared;
	@ManyToMany(mappedBy="achieve")
	private Collection<Student> stdacheve;
	@ManyToMany(mappedBy="deletee")
	private Collection<Student> stdachieve;
	 @ManyToMany
		@JoinTable(
				name="msg_Archieved",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="Achieve_id")
				  )
	private Collection<Archieve> achievedd;
	 @ManyToMany
		@JoinTable(
				name="msg_deleted",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="Delete_id")
				  )
	private Collection<Delete> deletee;
	
	 @ManyToMany
		@JoinTable(
				name="grp_messages",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="Group_id")
				  )
	 private Collection<Groups> groups;
	 
	public Collection<ClearMsgs> getMsgcleared() {
		return msgcleared;
	}

	public void setMsgcleared(Collection<ClearMsgs> msgcleared) {
		this.msgcleared = msgcleared;
	}

	public Collection<Student> getStdacheve() {
		return stdacheve;
	}

	public void setStdacheve(Collection<Student> stdacheve) {
		this.stdacheve = stdacheve;
	}

	public Collection<Student> getStdachieve() {
		return stdachieve;
	}

	public void setStdachieve(Collection<Student> stdachieve) {
		this.stdachieve = stdachieve;
	}

	public Collection<Archieve> getAchievedd() {
		return achievedd;
	}

	public void setAchievedd(Collection<Archieve> achievedd) {
		this.achievedd = achievedd;
	}

	public Collection<Seen> getMsgseen() {
		return msgseen;
	}

	public void setMsgseen(Collection<Seen> msgseen) {
		this.msgseen = msgseen;
	}

	public Collection<Delete> getDeletee() {
		return deletee;
	}

	public void setDeletee(Collection<Delete> deletee) {
		this.deletee = deletee;
	}

	public Collection<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Groups> groups) {
		this.groups = groups;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	 @ManyToMany
		@JoinTable(
				name="msgTo_std",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="std_id")
				  )
	 private Collection<Student> stdto;
	 @ManyToMany
		@JoinTable(
				name="msgfrom_std",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="std_id")
				  )
	 private Collection<Student> stdfrom;


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

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
