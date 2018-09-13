package anudeep.springdemo.dao;

import java.util.List;


import anudeep.springdemo.entity.Student;

public interface StudentDAO {

	public List<Student> getStudents();

	public void saveStudent(Student theStudent);

	public Student getStudent(int theId);

	public void deleteStudent(int theId);

	public List<Student> findStudents(String studentName);


	
}
