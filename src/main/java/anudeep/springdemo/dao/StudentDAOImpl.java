package anudeep.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import anudeep.springdemo.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Student> getStudents() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Student> theQuery = 
				currentSession.createQuery("from Student order by lastName",
											Student.class);
		
		// execute query and get result list
		List<Student> students = theQuery.getResultList();
				
		// return the results		
		return students;
	}

	@Override
	public void saveStudent(Student theStudent) {

	
		Session currentSession = sessionFactory.getCurrentSession();
	
		System.out.println(theStudent.getId());
		currentSession.saveOrUpdate(theStudent);
		
	}

	@Override
	public Student getStudent(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Student theStudent = currentSession.get(Student.class, theId);
		
		return theStudent;
	}

	@Override
	public void deleteStudent(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Student where id=:studentId");
		theQuery.setParameter("studentId", theId);
		
		theQuery.executeUpdate();		
	}

	@Override
	public List<Student> findStudents(String studentName) {
		Query theQuery = null;
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		 if (studentName != null && studentName.trim().length() > 0) {

			  theQuery = currentSession.createQuery("from Student where lower(firstName) like :theName or lower(lastName) like :theName", Student.class);
	            // search for firstName or lastName ... case insensitive
	         
	            theQuery.setParameter("theName", "%" + studentName.toLowerCase() + "%");

	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Student", Student.class);            
	        }
	        
	        // execute query and get result list
	        List<Student> students = theQuery.getResultList();
	                
	        // return the results        
	        return students;
	        
	    }

}











