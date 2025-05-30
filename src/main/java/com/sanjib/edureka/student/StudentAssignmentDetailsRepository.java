package com.sanjib.edureka.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAssignmentDetailsRepository extends JpaRepository<Assignment, Integer>{

	public List<Assignment> findByCourseId(Integer courseId);
	
	public List<Assignment> findByStudentIdAndCourseId(Integer studentId,Integer courseId);
	
	public Optional<Assignment> findById(Integer id);
}
