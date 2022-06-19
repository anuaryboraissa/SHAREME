package com.example.share.Entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@Entity
@Table(name = "Groups")
public class Groups implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1422641164637198070L;
	@Column(name="Group_name",nullable=false)
	private String grp_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Group_id")
	private long id;
	@Lob
    @Column(name = "Group_icon", length = Integer.MAX_VALUE, nullable = false)
	private byte[] grp_icon;
	@Column(name="Group_desc",nullable=false)
	private String grp_desc;
	@Column(name="Group_capacity",nullable=false)
	private int capacity;
	 
   @ManyToMany
		@JoinTable(
				name="grp_participants",
				joinColumns= @JoinColumn(
						name="Group_id"),
				inverseJoinColumns=@JoinColumn(
						name="st_Id")
				  )
	 private Collection<Student> student;
   @ManyToMany
		@JoinTable(
				name="grp_admins",
				joinColumns= @JoinColumn(
						name="Group_id"),
				inverseJoinColumns=@JoinColumn(
						name="st_Id")
				  )
	 private Collection<Student> grp_admin;
	 @ManyToMany(mappedBy="groups")
	 private Collection<Messages> messages;
     public Collection<Student> getGrp_admin() {
		return grp_admin;
	}

	public void setGrp_admin(Collection<Student> grp_admin) {
		this.grp_admin = grp_admin;
	}
	@Transient
    public String getPhotosImagePath() {
        if (grp_icon == null) return null;
         System.out.println("picha");
        return "img1/GrpIcons/" +id+"/"+grp_icon;
    }

	public String getGrp_name() {
		return grp_name;
	}

	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public byte[] getGrp_icon() {
		return grp_icon;
	}

	public void setGrp_icon(byte[] grp_icon) {
		this.grp_icon = grp_icon;
	}

	public String getGrp_desc() {
		return grp_desc;
	}

	public void setGrp_desc(String grp_desc) {
		this.grp_desc = grp_desc;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
   public Collection<Student> getStudent() {
		return student;
	}

	public void setStudent(Collection<Student> student) {
		this.student = student;
	}

	public Collection<Messages> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Messages> messages) {
		this.messages = messages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
}
