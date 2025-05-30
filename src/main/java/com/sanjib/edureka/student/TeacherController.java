package com.sanjib.edureka.student;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	StudentAssignmentDetailsRepository studentAssignmentDetailsRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@GetMapping("/{teacherId}/courses")
	public List<Course> showTeacherCourses(@PathVariable("teacherId") int teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId).get();
		List<Course> courses = teacher.getCourses();
		return courses;
	}
	
	
	
	@PostMapping("/course/{courseId}/add/assignment")
	public String addAssignment(@PathVariable("courseId") int courseId,
			@RequestBody Assignment assignmentView) {

		Course course = courseRepository.findById(courseId).get();
		List<Student> students = course.getStudents();

		for (Student student : students) {

			Assignment assignment = new Assignment();
			assignment.setName(assignmentView.getName());
			assignment.setDueDate(assignmentView.getDueDate());
			assignment.setStudentId(student.getId());
			assignment.setCourseId(courseId);

			int daysRemaining = findDayDifference(assignment);
			assignment.setDaysRemaining(daysRemaining);
			assignmentRepository.save(assignment);
		}

		return "Assignment Created";
	}
	
	@GetMapping("/course/{courseId}/get/assignment")
	public List<Assignment> showTeacherCourseDetails(@PathVariable("courseId") int courseId) {

		List<Assignment> assignments = null;

		assignments = studentAssignmentDetailsRepository.findByCourseId(courseId);

		for (Assignment assignment : assignments) {
			int daysRemaining = findDayDifference(assignment);
			assignment.setDaysRemaining(daysRemaining);
			assignmentRepository.save(assignment);
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




