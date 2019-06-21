package com.training.hibernate.onetomany.eager.vs.lazy.loading;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * This One-To-Many Bi-direction - where we don't use CascadeType.ALL coz we dont want to delete
 * associated items eg. when we delete a course, we dont want to also delete an instructor
 */
public class FetchJoinDemoApp {

	/*
	 * Using FetchType.LAZY in Instructor class
	 */
	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernateOneToMany.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		//EagerLazyDemoApp.createInstructorOnly(factory);
		//EagerLazyDemoApp.createCourseForSpecificInstructor(factory,2);
		FetchJoinDemoApp.getInstructorAndCourses(factory,1);
		// EagerLazyDemoApp.deleteCourse(factory, 15);
		// EagerLazyDemoApp.getInstructorUsingBidirectional(factory, 4);
	}

	private static void createInstructorOnly(SessionFactory factory) {
		Session session = factory.getCurrentSession();

		Instructor tempInstructor = new Instructor("Gilby", "Gombe", "gilby@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("www.onelovecharity.com/youtube",
				"Watching Movies");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		try {
			// start a session
			session.beginTransaction();

			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit tnx
			session.getTransaction().commit();
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void createCourseForSpecificInstructor(SessionFactory factory, int instructorid) {

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
			Course cprop = new Course("C#");
			
			//add course to instructor
			instructor.addCourse(javaSpring);
			instructor.addCourse(python);
			instructor.addCourse(cprop);
			
			//save course
			session.save(javaSpring);
			session.save(python);
			session.save(cprop);
			
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void getInstructorAndCourses(SessionFactory factory, int instructorid) {
		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();
			
			//Hibernate query with HQL
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "JOIN FETCH i.courses "
															+ "where i.id=:theInstructorId",
															Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId", instructorid);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			

			System.out.println("Luv2Code Instructor: "+ tempInstructor); //lazy data
			
			// commit tnx
			session.getTransaction().commit();
			
			//close session
			session.close();
			
			System.out.println("\nLuv2Code: The session is now closed!\n");
			
			//get courses for the instructor
			System.out.println("Luv2Code: Courses: " + tempInstructor.getCourses());
			
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}

	//This will not delete the corresponding instructor
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

	private static void displayCourses(List<Course> courses) {
		for (Course course : courses) {
			System.out.println(course.getInstructor().getFirstName()+" "+course.getInstructor().getLastName()+"=>"+course.getId()+" "+ course.getTitle());
		}
	}

}
