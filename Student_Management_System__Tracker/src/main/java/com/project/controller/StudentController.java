package com.project.controller;

import java.util.List;


import com.project.model.Student;
import com.project.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*") 
public class StudentController {

	 private final StudentService studentService;

	    @Autowired
	    public StudentController(StudentService studentService) {
	        this.studentService = studentService;
	    }

	    
	    @PostMapping
	    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
	        Student createdStudent = studentService.addStudent(student);
	        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	    }

	   
	    @GetMapping
	    public ResponseEntity<List<Student>> getAllStudents() {
	        List<Student> students = studentService.getAllStudent(); 
	        return new ResponseEntity<>(students, HttpStatus.OK);
	    }

	    @GetMapping("/search/{title}")
	    public ResponseEntity<List<Student>> searchTasksByTitle(@PathVariable String title) {
	       
	        List<Student> students = studentService.getStudentsByTitle(title); 

	        if (students.isEmpty()) {
	          
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	       
	        return new ResponseEntity<>(students, HttpStatus.OK);
	    }


	   
	    @PutMapping("/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
	        Student student = studentService.updateStudent(id, updatedStudent);
	        return student != null
	                ? new ResponseEntity<>(student, HttpStatus.OK)
	                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
	        studentService.deleteStudentById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
