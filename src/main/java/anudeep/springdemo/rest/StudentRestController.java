package anudeep.springdemo.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anudeep.springdemo.entity.Student;
import anudeep.springdemo.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return studentService.getStudents();
		
	}
	
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		Student theStudent = studentService.getStudent(studentId);
		
		if (theStudent == null) {
			throw new StudentNotFoundException("STudent id not found - " + studentId);
		}
		
		return theStudent;
	}
	
	
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student theStudent) {
		
		studentService.saveStudent(theStudent);
		
		return theStudent;
	}
	
	
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student theStudent) {
		
		studentService.saveStudent(theStudent);
		
		return theStudent;
		
	}
	
	
	
	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {
		
		Student tempStudent = studentService.getStudent(studentId);
		
		// throw exception if null
		
		if (tempStudent == null) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
				
		studentService.deleteStudent(studentId);
		
		return "Deleted student id - " + studentId;
	}
	
	@GetMapping("/students/name/{studentName}")
	public List<Student> findStudent(@PathVariable String studentName) {
		
		return studentService.findStudents(studentName);
		
	}
	
	
}


















