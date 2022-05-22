package com.example.share.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "college")
public class College {
	@NotBlank(message = "college must not be empty")
	@Size(max = 8,min = 4)
	@Column(name="coll_name",nullable=false)
	private String coll_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="coll_id")
	private long id;
	
	@OneToMany(targetEntity=Programme.class, mappedBy="college",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Programme> programe;
	
	@ManyToOne()
	@JoinColumn(name="Uni_id", referencedColumnName = "Uni_id")    
	private University university;

	public College() {
		super();
	}

	public College(String name,
		 University university) {
		super();
		this.coll_name = name;
		this.university = university;
	}



	public String getColl_name() {
		return coll_name;
	}

	public void setColl_name(String coll_name) {
		this.coll_name = coll_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Programme> getPrograme() {
		return programe;
	}

	public void setPrograme(Collection<Programme> programe) {
		this.programe = programe;
		programe.forEach(programes ->
		programes.setCollege(this));
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return "College [coll_name=" + coll_name + ", id=" + id + ", programe=" + programe  
				+ ", university=" + university + "]";
	}
	
	

}
