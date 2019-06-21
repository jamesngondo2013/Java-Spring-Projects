package com.training.hibernate;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.training.hibernate.data.utils.DateUtils;
import com.training.hibernate.demo.entity.Student;

/**
 * Hello world!
 *
 */
public class StudentDemo 
{
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			String theDateOfBirthStr = "31/12/1998";
			Date theDateOfBirth;

			theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);

			// create a student object
			System.out.println("Creating new student object...");
			Student temoStudent = new Student("Paully", "Doe", "paul@luv.com", theDateOfBirth);

			// start transaction session.beginTransaction();

			// save student System.out.println("Saving the student...");
			session.save(temoStudent);

			// commit the transaction session.getTransaction().commit();

			System.out.println("Done...");

		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	 
}
