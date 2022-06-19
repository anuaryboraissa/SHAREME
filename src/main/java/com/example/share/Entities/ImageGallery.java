package com.example.share.Entities;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "image_gallery")
public class ImageGallery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "img_id", nullable = false, unique = true)
	private Long id;
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    @ManyToMany(mappedBy="imagess")
	private Collection<Student> student;
	
	public Collection<Student> getStudent() {
		return student;
	}
	public void setStudent(Collection<Student> student) {
		this.student = student;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "ImageGallery [id=" + id + ", image=" + Arrays.toString(image) + "]";
	}
	
	
}
