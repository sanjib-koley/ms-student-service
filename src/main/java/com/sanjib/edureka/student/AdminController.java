package com.sanjib.edureka.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	
	@PostMapping("/student/add")
	public Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PostMapping("/register/course")
	public Course addCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	
	@PostMapping("/register/teacher/course/{courseId}")
	public Teacher addTeacher(@RequestBody Teacher teacher,@PathVariable("courseId") int courseId) {
		
		teacherRepository.save(teacher);
		Course course = courseRepository.findById(courseId).get();
		course.setTeacher(teacher);
		courseRepository.save(course);
		return teacher;
	}
	
	@PostMapping("/register/student/{studentId}/course/{courseId}")
	public Student addCourseForStudent(@PathVariable("studentId") int studentId,@PathVariable("courseId") int courseId) {
		
		Student student = studentRepository.findById(studentId).get();
		Course course = courseRepository.findById(courseId).get();
		
		List<Course> courses = student.getCourses();
		courses.add(course);
		return studentRepository.save(student);
		
	}
}
