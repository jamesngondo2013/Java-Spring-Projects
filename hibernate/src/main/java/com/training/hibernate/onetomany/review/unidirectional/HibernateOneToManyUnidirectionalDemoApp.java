package com.training.hibernate.onetomany.review.unidirectional;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import  com.training.hibernate.onetomany.review.unidirectional.Instructor;
import com.training.hibernate.onetomany.review.unidirectional.InstructorDetail;

/*
 * This One-To-Many Bi-direction - where we don't use CascadeType.ALL coz we dont want to delete
 * associated items eg. when we delete a course, we dont want to also delete an instructor
 */
public class HibernateOneToManyUnidirectionalDemoApp {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernateOneToManyUnidirectional.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		//HibernateOneToManyUnidirectionalDemoApp.createInstructorOnly(factory);
		//HibernateOneToManyUnidirectionalDemoApp.createCourseAndReviewsForSpecificInstructor(factory, 13);
		
		//HibernateOneToManyUnidirectionalDemoApp.createCourseAndReview(factory);
		HibernateOneToManyUnidirectionalDemoApp.getCoursesAndReviews(factory,10);
		//HibernateOneToManyUnidirectionalDemoApp.deleteCourse(factory, 10);
		
		
	}
	
	private static void createCourseAndReview(SessionFactory factory) {
		Session session = factory.getCurrentSession();

		try {

			// start a session
			session.beginTransaction();
			
			//create a course
			//create course
			Course javaSpring = new Course("Java Spring");
			Course python = new Course("Python");
			Course cprog = new Course("C#");
			
			//add some reviews
			javaSpring.addReview(new Review("Great course...loved it!!!"));
			javaSpring.addReview(new Review("Cool course, job well done"));
			
			python.addReview(new Review("This was well cooked up...!!!"));
			python.addReview(new Review("What a dumb course, idiot"));
			
			cprog.addReview(new Review("The instructor is knowlegeable"));
			cprog.addReview(new Review("Very excting tutorial...!!!"));
			
			//save the course...and leverage cascade all
			System.out.println("Saving the course...");
			System.out.println(javaSpring);
			System.out.println(javaSpring.getReviews());
			
			System.out.println(python);
			System.out.println(python.getReviews());
			
			System.out.println(cprog);
			System.out.println(cprog.getReviews());
			
			session.save(javaSpring);
			session.save(python);
			session.save(cprog);
			
			// commit tnx
			session.getTransaction().commit();
						
			System.out.println("Done!!!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
	
	public static void getCoursesAndReviews(SessionFactory factory, int courseid) {
		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();

			// GET INSTRUCTOR BY PRIMARY KEY ID
			Course course = session.get(Course.class, courseid);
			System.out.println("Found Course: " + course);
			
			List<Review> reviewList = course.getReviews();
			System.out.println(course.getReviews());
			// commit tnx
			session.getTransaction().commit();
			
			displayReviews(reviewList);
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	// This will delete the course and its corresponding reviews
	private static void deleteCourse(SessionFactory factory, int courseid) {

		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();

			// GET INSTRUCTOR BY PRIMARY KEY ID
			Course tempCourse = session.get(Course.class, courseid);

			// DELETE INSTRUCTOR - with associated details coz of CascadeType.ALL
			System.out.println("Found Course to delete: " + tempCourse);
			session.delete(tempCourse);

			// commit tnx
			session.getTransaction().commit();
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private static void createInstructorOnly(SessionFactory factory) {
		Session session = factory.getCurrentSession();

		Instructor tempInstructorJay = new Instructor("Jay", "Ngondo", "jay@gmail.com");
		Instructor tempInstructorGilby = new Instructor("Gilby", "Gombe", "gilby@gmail.com");

		InstructorDetail tempInstructorDetailJay = new InstructorDetail("www.onelovecharity.com/youtube",
				"Watching Movies");
		InstructorDetail tempInstructorDetailGilby = new InstructorDetail("www.emmanueltv.com/youtube",
				"Watching EmmanuelTV");

		tempInstructorJay.setInstructorDetail(tempInstructorDetailJay);
		tempInstructorGilby.setInstructorDetail(tempInstructorDetailGilby);

		try {
			// start a session
			session.beginTransaction();

			System.out.println("Saving instructor: " + tempInstructorGilby);
			session.save(tempInstructorGilby);
			
			System.out.println("Saving instructor: " + tempInstructorJay);
			session.save(tempInstructorJay);

			// commit tnx
			session.getTransaction().commit();
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}


	
	private static void createCourseAndReviewsForSpecificInstructor(SessionFactory factory, int instructorid) {

		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();

			// GET INSTRUCTOR BY PRIMARY KEY ID
			Instructor instructor = session.get(Instructor.class, instructorid);
			System.out.println("Found instructor: " + instructor);
			
			//create course
			Course javaSpring = new Course("Java Spring");
			Course python = new Course("Python");
			Course cprog = new Course("C#");
			
			//add some reviews
			javaSpring.addReview(new Review("Great course...loved it!!!"));
			javaSpring.addReview(new Review("Cool course, job well done"));
			
			python.addReview(new Review("This was well cooked up...!!!"));
			python.addReview(new Review("What a dumb course, idiot"));
			
			cprog.addReview(new Review("The instructor is knowlegeable"));
			cprog.addReview(new Review("Very excting tutorial...!!!"));
			
			//save the course...and leverage cascade all
			System.out.println("Saving the course...");
			session.save(javaSpring);
			session.save(python);
			session.save(cprog);
			
			//add Reviews to course
			
			//add course to instructor
			instructor.addCourse(javaSpring);
			instructor.addCourse(python);
			instructor.addCourse(cprog);
			
			//save course
			session.save(javaSpring);
			session.save(python);
			session.save(cprog);
			
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private static void displayReviews(List<Review> reviews) {
		for (Review review : reviews) {
			System.out.println(review.getComment());
		}
	}

}
