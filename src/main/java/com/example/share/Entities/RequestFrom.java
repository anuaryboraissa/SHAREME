package com.example.share.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.share.Entities.Composites.CompositeKey;

@Entity
@Table(name = "requestFrom")
@IdClass(CompositeKey.class)
public class RequestFrom {
@ManyToOne()
@JoinColumn(name="req_id", referencedColumnName = "request_id") 
@Id
private Requests request;
@ManyToOne()
@JoinColumn(name="st_id", referencedColumnName = "st_id")
@Id
private Student std;
public Requests getRequest() {
	return request;
}
public void setRequest(Requests request) {
	this.request = request;
}
public Student getStd() {
	return std;
}
public void setStd(Student std) {
	this.std = std;
}

}
