package com.sanjib.edureka.student;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
	
	
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	StudentCourseDetailsRepository studentCourseDetailsRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@GetMapping("/{teacherId}/courses")
	public List<Course> showTeacherCourses(@PathVariable("teacherId") int teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId).get();
		List<Course> courses = teacher.getCourses();
		return courses;
	}
	
	@GetMapping("/{teacherId}/course/{courseId}/assignment")
	public List<Assignment> showTeacherCourseDetails(@PathVariable("teacherId") int teacherId,
			@PathVariable("courseId") int courseId) {

		
		List<Assignment> assignments =null;
		Teacher teacher = teacherRepository.findById(teacherId).get();
		Course course = courseRepository.findById(courseId).get();

		List<Student> students = course.getStudents();

		if (students.size() != 0) {

			assignments = studentCourseDetailsRepository
					.findByStudentIdAndCourseId(students.get(0).getId(), courseId).get().getAssignments();

			for (Assignment assignment : assignments) {
				int daysRemaining = findDayDifference(assignment);
				assignment.setDaysRemaining(daysRemaining);
				assignmentRepository.save(assignment);
			}
		}

		return assignments;
	}
	
	
	
	
	private int findDayDifference(Assignment assignment) {
		String dateString = assignment.getDueDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate dueDate = LocalDate.parse(dateString, dtf);
			LocalDate today = LocalDate.now();
			int dayDiff = (int) Duration.between(today.atStartOfDay(), dueDate.atStartOfDay()).toDays();
			
			return dayDiff;	
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return -1;
	}
	
}




