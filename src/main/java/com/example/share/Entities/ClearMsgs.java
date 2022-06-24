package com.example.share.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Msg_Cleared")
public class ClearMsgs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="clear_id")
	private long id;
	
	@ManyToOne()
	@JoinColumn(name="std_id", referencedColumnName = "st_id")    
	private Student studentclear;
	@ManyToOne()
	@JoinColumn(name="msg_id", referencedColumnName = "msg_id")    
	private Messages msgs;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Student getStudentclear() {
		return studentclear;
	}
	public void setStudentclear(Student studentclear) {
		this.studentclear = studentclear;
	}
	public Messages getMsgs() {
		return msgs;
	}
	public void setMsgs(Messages msgs) {
		this.msgs = msgs;
	}
	
}
