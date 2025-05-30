package com.sanjib.edureka.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentAssignmentDetailsRepository studentAssignmentDetailsRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	
	
	@GetMapping("/{studentId}/courses")
	public List<Course> getCourses(@PathVariable("studentId") int studentId) {

		Student student = studentRepository.findById(studentId).get();

		List<Course> courses = student.getCourses();

		return courses;
	}
	
	@GetMapping("/{studentId}/courses/{courseId}/assignments")
	public List<com.sanjib.edureka.student.Assignment> getAssignments(@PathVariable("studentId") int studentId,
			@PathVariable("courseId") int courseId, Model theModel) {

		return studentAssignmentDetailsRepository.findByStudentIdAndCourseId(studentId, courseId);
	}
	
	
	@PutMapping("/{studentId}/courses/{courseId}/complete/{assignmentId}")
	public Assignment markAsCompleted(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId,
									@PathVariable("assignmentId") int assignmentId) {
		
		Assignment a = studentAssignmentDetailsRepository.findById(assignmentId).get();
		
		a.setDueDate("Completed");
		a.setDaysRemaining(0);
		assignmentRepository.save(a);
		return a;
	}

}
