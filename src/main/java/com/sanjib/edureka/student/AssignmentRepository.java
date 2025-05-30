package com.sanjib.edureka.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{

	public Optional<Assignment> findById(Integer id);
}
