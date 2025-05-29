package com.sanjib.edureka.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	
	public Optional<Student> findById(Integer id);
}
