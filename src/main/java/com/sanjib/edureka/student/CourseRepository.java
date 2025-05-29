package com.sanjib.edureka.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	
	public Optional<Course> findById(Integer id);
}
