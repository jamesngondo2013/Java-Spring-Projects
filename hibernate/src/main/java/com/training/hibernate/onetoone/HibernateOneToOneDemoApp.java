package com.training.hibernate.onetoone;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateOneToOneDemoApp {

	public static void main(String[] args) {
	
		SessionFactory factory = new Configuration()
				.configure("hibernateOneToOne.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//createInstructor(factory);
		//deleteInstructor(factory, 3);
		getAllStudents(factory);
		//getInstructorUsingBidirectional(factory, 4);
	}

	private static void createInstructor(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		
		Instructor tempInstructor = new Instructor("Bright","Banda", "bright@gmail.com");
		
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.saitcleaning.com/youtube", "Cleaning!!!");
		
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		try {
		//start a session
		session.beginTransaction();
		
		System.out.println("Saving instructor: "+ tempInstructor);
		session.save(tempInstructor);
		
		//commit tnx
		session.getTransaction().commit();
		
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	private static void deleteInstructor(SessionFactory factory, int id) {
		
		Session session = factory.getCurrentSession();
		
		try {
		//start a session
		session.beginTransaction();
		
		//GET INSTRUCTOR BY PRIMARY KEY ID
		Instructor instructor = session.get(Instructor.class, id);
		
		//DELETE INSTRUCTOR - with associated details coz of CascadeType.ALL
		System.out.println("Found instructor: "+ instructor);
		session.delete(instructor);
		
		//commit tnx
		session.getTransaction().commit();
		
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static void getInstructorUsingBidirectional(SessionFactory factory, int instructorID) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve student based on PK (ID)
			System.out.println("\nGetting Instructor with id: " + instructorID);
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorID);
			
			System.out.println(instructorDetail.getId()+", "+instructorDetail.getInstructor().getFirstName()+", "+instructorDetail.getInstructor().getLastName()+", "+
			instructorDetail.getInstructor().getEmail()+", "+ instructorDetail.getHobby()+", "+instructorDetail.getYoutube_chanel());

			//delete instructor - bidirectional mapping
			session.delete(instructorDetail);
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done...");
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static void getAllStudents(SessionFactory factory) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve all students
			List<Instructor> instructors = session.createQuery("from Instructor").getResultList();

			// commit transaction
			session.getTransaction().commit();

			System.out.println("All Instructors...");
			
			displayInstructors(instructors);
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	private static void displayInstructors(List<Instructor> instructors) {
		for (Instructor instr : instructors) {
			System.out.println(instr.getId()+", "+instr.getFirstName()+", "+instr.getLastName()+", "+instr.getInstructorDetail().getHobby()+", "+instr.getEmail()+ instr.getInstructorDetail().getYoutube_chanel());
		}
	}

}
