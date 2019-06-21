package com.training.hibernate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.training.hibernate.data.utils.DateUtils;
import com.training.hibernate.demo.entity.Student;

/*
 * This will add multiple students
 */

public class PrimaryKeyDemo {

	public static void main(String[] args) throws ParseException {
		PrimaryKeyDemo demo = new PrimaryKeyDemo();
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		String theDateOfBirthStr = "31/12/1998";
        Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
        
        Student temoStudent = new Student("Paully", "Doe", "paul@luv.com", theDateOfBirth);
		
		//create student
		//int stdID = saveStudent(factory,temoStudent);
		
		//read student
		//getStudent(factory,4);
		
		//updated student fname
		//updateSpecificStudent(factory,  1, "Prosper");
		
		//get specific student
		//getSpecificStudent(factory,"from Student s where s.email LIKE '%gmail.com'");

		//delete student
		//deletsSpecificStudent(factory,  4);
		
		//get all students
		getAllStudents(factory);
	}
	
	public static int saveStudent(SessionFactory factory, Student student) {
		
		Session session = factory.getCurrentSession();
		int studentId;
		
		try {
			// start transaction
			session.beginTransaction();

			// save student
			System.out.println("Saving the student...");
			System.out.println(student);
			studentId = student.getId();
			session.save(student);

			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done...");

		} finally {
			factory.close();
		}
		
		return studentId;
		
	}
	
	public static void getStudent(SessionFactory factory, int studentID) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve student based on PK (ID)
			System.out.println("\nGetting student with id: " + studentID);
			Student std = session.get(Student.class, studentID);
			System.out.println(std.getId()+" "+std.getFirstName()+" "+std.getLastName()+" "+std.getDateOfBirth()+" "+std.getEmail());

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done...");
		} finally {
			factory.close();
		}
	}
	
	public static void getAllStudents(SessionFactory factory) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve all students
			List<Student> students = session.createQuery("from Student").getResultList();

			// commit transaction
			session.getTransaction().commit();

			System.out.println("All Students...");
			
			displayStudents(students);
			
		} finally {
			factory.close();
		}
	}
	
	public static void getSpecificStudent(SessionFactory factory,  String query) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve all students
			List<Student> students = session.createQuery(query).getResultList();

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Specific Students...");
			
			displayStudents(students);
			
		} finally {
			factory.close();
		}
	}
	
	public static void updateSpecificStudent(SessionFactory factory,  int studentId, String fname) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve all students
			Student student = session.get(Student.class, studentId);
			
			//update student fname
			System.out.println("Previous Student Details: " + student.getFirstName());
			student.setFirstName(fname);
			
			//update all students email 
			//session.createQuery("update Student set email='foo@gmail.com'");

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Updated Students...");
			System.out.println("New Student Details: " + student.getFirstName());
		
			
		} finally {
			factory.close();
		}
	}
	
	public static void deletsSpecificStudent(SessionFactory factory,  int studentId) {
		// get a new session and start a transaction
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();

			// retrieve all students
			Student student = session.get(Student.class, studentId);
			
			//delete student by id
			session.delete(student);
			
			//another way of deleting
			//session.createQuery("delete from Student where id=2").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student std : students) {
			System.out.println(std.getId()+" "+std.getFirstName()+" "+std.getLastName()+" "+std.getDateOfBirth()+" "+std.getEmail());
		}
	}

}
