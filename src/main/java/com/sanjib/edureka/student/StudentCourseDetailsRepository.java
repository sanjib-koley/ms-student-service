package com.sanjib.edureka.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseDetailsRepository extends JpaRepository<StudentCourseDetails, Integer>{

	public Optional<StudentCourseDetails> findByStudentIdAndCourseId(Integer studentId,Integer courseId);
}
