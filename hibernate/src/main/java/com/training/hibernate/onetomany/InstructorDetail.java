package com.training.hibernate.onetomany;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youtube_channel;
	
	@Column(name="hobby")
	private String hobby;
	
	//this is only needed when we want one-to-one bidirection mapping
	//mappedBy tells Hibernate - to look at the instructorDetail property in the Instructor class
	//to use info from the Instructor class @JoinColumn to help find associated instructor
	//CascadeType.ALL will allow this to delete the corresponding instructor
	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL) //this refers to the 'instructorDetail' attribute/property in the 'Instructor' class -"private InstructorDetail instructorDetail;"
	private Instructor instructor;
	
	public InstructorDetail() {
		
	}

	public InstructorDetail(String youtube_chanel, String hobby) {
		this.youtube_channel = youtube_chanel;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutube_chanel() {
		return youtube_channel;
	}

	public void setYoutube_chanel(String youtube_chanel) {
		this.youtube_channel = youtube_chanel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public String getFirstName() {
		return instructor.getFirstName();
	}

	public void setFirstName(String firstName) {
		instructor.setFirstName(firstName);
	}

	public String getLastName() {
		return instructor.getLastName();
	}

	public void setLastName(String lastName) {
		instructor.setLastName(lastName);
	}

	public String getEmail() {
		return instructor.getEmail();
	}

	public void setEmail(String email) {
		instructor.setEmail(email);
	}
//========================================================
	//this is only needed when we want one-to-one bidirection mapping
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
//========================================================
	
	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtube_chanel=" + youtube_channel + ", hobby=" + hobby + "]";
	}
	
}
