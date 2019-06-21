package com.training.hibernate.manytomany;

import java.awt.image.CropImageFilter;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/*
 * This One-To-Many Bi-direction - where we don't use CascadeType.ALL coz we dont want to delete
 * associated items eg. when we delete a course, we dont want to also delete an instructor
 */
public class HibernateManyToManyDemoApp {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernateManyToMany.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		//HibernateManyToManyDemoApp.createCourseAndStudents(factory);
		//HibernateManyToManyDemoApp.addCourseForSpecificStudent(factory,2);
		//HibernateManyToManyDemoApp.getStudentAndCoursesLazyLoadingHQL(factory,2);           //this makes use of Lazy Loading HQL
		HibernateManyToManyDemoApp.getStudentAndCourses(factory,2); 
		// HibernateManyToManyDemoApp.deleteStudent(factory, 5);
		
	}

	private static void createCourseAndStudents(SessionFactory factory) {
		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();
			
			//create course
			Course javaSpring = new Course("Java Spring");
			Course python = new Course("Python");
			Course CSharp = new Course("C#");
			
			System.out.println("\nSaving the course...");
			
			//save course
			session.save(javaSpring);
			session.save(python);
			session.save(CSharp);
			
			//create the students
			Student std1 = new Student("Baxter", "Saiti", "baxter@gmail.com");
			Student std2 = new Student("Lloyd", "Phiri", "lloyd@gmail.com");
			Student std3 = new Student("Bright", "Banda", "bright@gmail.com");
			Student std4 = new Student("Cathy", "Samamlani", "cathy@gmail.com");
			Student std5 = new Student("Shanks", "Kaunda", "bishop@gmail.com");
			
			//add students to the course
			javaSpring.addStudent(std1);
			javaSpring.addStudent(std5);
			javaSpring.addStudent(std3);
			
			python.addStudent(std2);
			python.addStudent(std4);
			python.addStudent(std3);
			
			CSharp.addStudent(std3);
			CSharp.addStudent(std4);
			CSharp.addStudent(std1);
			
			//save the students
			session.save(std1);
			session.save(std2);
			session.save(std3);
			session.save(std4);
			session.save(std5);
			System.out.println("\nSaved students " + javaSpring.getStudents());
			System.out.println("\nSaved students " + python.getStudents());
			System.out.println("\nSaved students " + CSharp.getStudents());

			// commit tnx
			session.getTransaction().commit();
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void addCourseForSpecificStudent(SessionFactory factory, int studentid) {

		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();

			// GET Student BY PRIMARY KEY ID
			Student student = session.get(Student.class, studentid);
			System.out.println("Found Student: " + student);
			System.out.println("\nCourses for: "+student.getFirstName()+" "+student.getLastName() + "=> "+student.getCourses());
			
			//create more courses
			Course stats = new Course("Statistcs");
			Course dataViz = new Course("Data Visualization");
			
			//add student to courses
			stats.addStudent(student);
			dataViz.addStudent(student);
			
			//save the course with newly added students
			System.out.println("\nSaving new courses with student...!!!");
			session.save(stats);
			session.save(dataViz);
			
			session.getTransaction().commit();
			
			
			System.out.println("Done!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void getStudentAndCourses(SessionFactory factory, int studentid) {
		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();

			// GET Student BY PRIMARY KEY ID
			Student student = session.get(Student.class, studentid);
			System.out.println("\nFound Student: " + student.getFirstName() + " " + student.getLastName());
				
			List<Course> courses = student.getCourses();

			displayCourses(courses);

			// commit tnx
			session.getTransaction().commit();

			System.out.println("\nDone!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}
	//this makes use of Lazy Loading
	public static void getStudentAndCoursesLazyLoadingHQL(SessionFactory factory, int studentid) {
		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();
			
			//Hibernate query with HQL
			Query<Student> query = session.createQuery("select i from Student i "
															+ "JOIN FETCH i.courses "
															+ "where i.id=:theStudentId",
															Student.class);
			
			//set parameter on query
			query.setParameter("theStudentId", studentid);
			
			//execute query and get instructor
			Student tempStudent = query.getSingleResult();
			

			System.out.println("Luv2Code Student: "+ tempStudent.getFirstName()+" "+ tempStudent.getLastName()); //lazy data
			
			// commit tnx
			session.getTransaction().commit();
			
			//close session
			session.close();
			
			System.out.println("\nLuv2Code: The session is now closed!\n");
			
			//get courses for the instructor
			//System.out.println("\nLuv2Code: Courses: " + tempStudent.getCourses());
			List<Course> courses = tempStudent.getCourses();
			
			displayCourses(courses);
			
			System.out.println("\nDone!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}

	//This will not delete the corresponding instructor
	private static void deleteStudent(SessionFactory factory, int studentid) {

		Session session = factory.getCurrentSession();

		try {
			// start a session
			session.beginTransaction();

			// GET student BY PRIMARY KEY ID
			Student tempStudent = session.get(Student.class, studentid);

			// DELETE STUDENT - with associated details coz of CascadeType.ALL
			System.out.println("\nFound student to delete: " + tempStudent);
			session.delete(tempStudent);

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
			System.out.println("=>"+course.getId()+" "+ course.getTitle());
		}
	}

}
