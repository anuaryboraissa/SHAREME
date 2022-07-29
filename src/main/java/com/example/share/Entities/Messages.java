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
	@OneToMany(targetEntity=Seen.class, mappedBy="msg",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Seen> msgseen;
	@OneToMany(targetEntity=Delete.class, mappedBy="msg",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Delete> msgdelet;
	@OneToMany(targetEntity=Archieve.class, mappedBy="msg",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Archieve> msgachieved;
	@OneToMany(targetEntity=ClearMsgs.class, mappedBy="msgs",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<ClearMsgs> msgcleared;


	 @ManyToMany
		@JoinTable(
				name="grp_messages",
				joinColumns= @JoinColumn(
						name="msg_id"),
				inverseJoinColumns=@JoinColumn(
						name="Group_id")
				  )
	 private Collection<Groups> groups;
	 
	public Collection<Delete> getMsgdelet() {
		return msgdelet;
	}



	public Collection<Archieve> getMsgachieved() {
		return msgachieved;
	}



	public void setMsgachieved(Collection<Archieve> msgachieved) {
		this.msgachieved = msgachieved;
	}



	public void setMsgdelet(Collection<Delete> msgdelet) {
		this.msgdelet = msgdelet;
	}

	public Collection<ClearMsgs> getMsgcleared() {
		return msgcleared;
	}

	public void setMsgcleared(Collection<ClearMsgs> msgcleared) {
		this.msgcleared = msgcleared;
	}


	public Collection<Seen> getMsgseen() {
		return msgseen;
	}

	public void setMsgseen(Collection<Seen> msgseen) {
		this.msgseen = msgseen;
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
