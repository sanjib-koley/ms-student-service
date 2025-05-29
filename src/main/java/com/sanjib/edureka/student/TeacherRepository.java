package com.sanjib.edureka.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	public Optional<Teacher> findById(Integer id);
}
