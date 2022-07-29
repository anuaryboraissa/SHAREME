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
@Table(name = "achieve")
public class Archieve {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="achieve_id")
	private long id;
	
	@ManyToOne()
	@JoinColumn(name="std_id", referencedColumnName = "st_id")    
	private Student studentachiev;
	@ManyToOne()
	@JoinColumn(name="stdarchieved_id", referencedColumnName = "st_id")    
	private Student stdmsgs;
	@ManyToOne()
	@JoinColumn(name="msg_id", referencedColumnName = "msg_id")    
	private Messages msg;
    private int status;
    
	public Messages getMsg() {
		return msg;
	}
	public void setMsg(Messages msg) {
		this.msg = msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Student getStudentachiev() {
		return studentachiev;
	}
	public void setStudentachiev(Student studentachiev) {
		this.studentachiev = studentachiev;
	}
	public Student getStdmsgs() {
		return stdmsgs;
	}
	public void setStdmsgs(Student stdmsgs) {
		this.stdmsgs = stdmsgs;
	}


	
}
