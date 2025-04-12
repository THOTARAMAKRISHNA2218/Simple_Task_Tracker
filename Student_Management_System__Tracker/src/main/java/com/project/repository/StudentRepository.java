package com.project.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.model.Student;

public interface StudentRepository  extends JpaRepository<Student,Long>{

	
	    List<Student> findByTitleContainingIgnoreCase(String title); 
	

}
