package com.training.hibernate.onetomany.review.unidirectional;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,}) //not using REMOVE coz we dont want to remove associated items
	@JoinColumn(name="instructor_id")  //course knows how to find its given instructor thru this column - foreign key
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL) //lazy load the reviews as demanded by the app // delete all associated reviews when delete a course
	@JoinColumn(name="course_id") //associate reviews with a given course in db 
	private List<Review> reviews; //Course with a list of Reviews
	
	public Course() {

	}

	public Course(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	// add convenience method for bi-directional relationship
	public void addReview(Review tempReview) {
		if (reviews == null) {
			reviews = new ArrayList<Review>();
		}
		// add course to list
		reviews.add(tempReview);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
	
	
}
