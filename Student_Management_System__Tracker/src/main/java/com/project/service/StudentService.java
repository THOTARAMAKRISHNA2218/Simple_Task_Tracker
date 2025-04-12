package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Student;
import com.project.repository.StudentRepository;

@Service
public class StudentService {
	 @Autowired
	 private final StudentRepository studentRepo;

	   
	    public StudentService(StudentRepository studentRepo) {
	        this.studentRepo = studentRepo;
	    }

	    public Student addStudent(Student student) {
	        return studentRepo.save(student);
	    }

	    public List<Student> getAllStudent() {
	        return studentRepo.findAll();
	    }

	    public List<Student> getStudentsByTitle(String title) {
	        return studentRepo.findByTitleContainingIgnoreCase(title);  
	    }


	    public Student updateStudent(Long id, Student updatedstudent) {
	        Optional<Student> existingStudent = studentRepo.findById(id);
	        if (existingStudent.isPresent()) {
	            Student student = existingStudent.get();
	            student.setTitle(student.getTitle());
	            student.setStatus(updatedstudent.getStatus());
	            return studentRepo.save(student);
	        }
	        return null; 
	    }

	    public void deleteStudentById(Long id) {
	    	studentRepo.deleteById(id);
	    }
	
}
