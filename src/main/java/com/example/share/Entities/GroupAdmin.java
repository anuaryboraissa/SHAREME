package com.example.share.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.share.Entities.Composites.AdminComposite;


@Entity
@Table(name = "grp_Admins")
@IdClass(AdminComposite.class)
public class GroupAdmin {
	@ManyToOne()
	@JoinColumn(name="st_id", referencedColumnName = "st_id") 
	@Id
private Student stdId;
	@ManyToOne()
	@JoinColumn(name="Group_id", referencedColumnName = "Group_id") 
	@Id
    private Groups grpId;

	public Student getStdId() {
		return stdId;
	}
	public void setStdId(Student stdId) {
		this.stdId = stdId;
	}
	public Groups getGrpId() {
		return grpId;
	}
	public void setGrpId(Groups grpId) {
		this.grpId = grpId;
	}

	
}
