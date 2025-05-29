package com.sanjib.edureka.student;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "student_assignment_details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "due_date")
	private String dueDate;

	@Column(name = "days_remaining")
	private int daysRemaining;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_assignment_details", joinColumns = { @JoinColumn(name = "id")}, inverseJoinColumns = {
					@JoinColumn(name = "course_id") })
	private List<StudentCourseDetails> courseDetails;

}
