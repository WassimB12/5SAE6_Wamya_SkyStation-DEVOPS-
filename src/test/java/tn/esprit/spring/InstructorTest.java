package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InstructorTest {
	@Mock
	private IInstructorRepository instructorRepository;

	@Mock
	private ICourseRepository courseRepository;
	@InjectMocks
	private InstructorServicesImpl instructorService;

	@Test
	public void testAddInstructor() {
		// Create a sample instructor with a set of courses
		Set<Course> courses = new HashSet<>();
		courses.add(Course.builder()
				.numCourse(1L)
				.level(1)
				.typeCourse(TypeCourse.INDIVIDUAL)
				.support(Support.SKI)
				.price(100.0f)
				.timeSlot(1)
				.build());

		Instructor instructor = Instructor.builder()
				.firstName("John")
				.lastName("Doe")
				.dateOfHire(LocalDate.of(2023, 10, 30))
				.courses(courses)
				.build();

		// Mock the repository to return the saved instructor
		when(instructorRepository.save(instructor)).thenReturn(instructor);

		// Test adding an instructor
		Instructor savedInstructor = instructorService.addInstructor(instructor);

		assertEquals(instructor, savedInstructor);
	}

	@Test
	public void testGetInstructorById() {
		// Create a sample instructor with a set of courses
		Set<Course> courses = new HashSet<>();
		courses.add(Course.builder()
				.numCourse(1L)
				.level(1)
				.typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
				.support(Support.SNOWBOARD)
				.price(100.0f)
				.timeSlot(1)
				.build());

		Instructor instructor = Instructor.builder()
				.numInstructor(1L)
				.firstName("John")
				.lastName("Doe")
				.dateOfHire(LocalDate.of(2023, 10, 30))
				.courses(courses)
				.build();

		// Mock the repository to return the instructor when searching by ID
		when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

		// Test retrieving an instructor by ID
		Instructor retrievedInstructor = instructorService.retrieveInstructor(1L);

		assertEquals(instructor, retrievedInstructor);
	}

	@Test
	public void testGetAllInstructors() {
		// Create a list of sample instructors with lists of courses
		Set<Course> courses1 = new HashSet<>();
		Course course1 = Course.builder()
				.numCourse(1L)
				.level(1)
				.typeCourse(TypeCourse.INDIVIDUAL)
				.support(Support.SKI)
				.price(100.0f)
				.timeSlot(1)
				.build();
		courses1.add(course1);

		Set<Course> courses2 =new HashSet<>();
		Course course2 = Course.builder()
				.numCourse(2L)
				.level(2)
				.typeCourse(TypeCourse.INDIVIDUAL)
				.support(Support.SKI)
				.price(150.0f)
				.timeSlot(2)
				.build();
		courses2.add(course2);

		Instructor instructor1 = Instructor.builder()
				.numInstructor(1L)
				.firstName("John")
				.lastName("Doe")
				.dateOfHire(LocalDate.of(2023, 10, 30))
				.courses(courses1)
				.build();

		Instructor instructor2 = Instructor.builder()
				.numInstructor(2L)
				.firstName("Alice")
				.lastName("Smith")
				.dateOfHire(LocalDate.of(2023, 11, 1))
				.courses(courses2)
				.build();

		// Mock the repository to return the list of instructors
		when(instructorRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(instructor1, instructor2)));

		// Test retrieving all instructors
		List<Instructor> retrievedInstructors = instructorService.retrieveAllInstructors();

		assertEquals(new ArrayList<>(Arrays.asList(instructor1, instructor2)), retrievedInstructors);
	}

	@Test
	public void testAddAndAssignToInstructor() {
		// Create a sample course
		Course course = Course.builder()
				.numCourse(1L)
				.level(1)
				.typeCourse(TypeCourse.INDIVIDUAL)
				.support(Support.SKI)
				.price(100.0f)
				.timeSlot(1)
				.build();

		// Mock the courseRepository to return the course when findById is called
		when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

		// Create a sample instructor with a set of courses
		Set<Course> courses = new HashSet<>();
		courses.add(course);

		Instructor instructor = Instructor.builder()
				.firstName("John")
				.lastName("Doe")
				.dateOfHire(LocalDate.of(2023, 10, 30))
				.courses(courses)
				.build();

		// Mock the repository to return the saved instructor
		when(instructorRepository.save(instructor)).thenReturn(instructor);

		// Test adding an instructor and assigning them to a course
		Instructor savedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, 1L);

		Assertions.assertEquals(instructor, savedInstructor);
	}

	@Test
	public void testUpdateInstructor() {
		// Create a sample instructor with a set of courses
		Set<Course> courses = new HashSet<>();
		Course course = Course.builder()
				.numCourse(1L)
				.level(1)
				.typeCourse(TypeCourse.INDIVIDUAL)
				.support(Support.SKI)
				.price(100.0f)
				.timeSlot(1)
				.build();
		courses.add(course);

		Instructor instructor = Instructor.builder()
				.numInstructor(1L)
				.firstName("John")
				.lastName("Doe")
				.dateOfHire(LocalDate.of(2023, 10, 30))
				.courses(courses)
				.build();

		// Mock the repository to return the updated instructor
		when(instructorRepository.save(instructor)).thenReturn(instructor);

		// Test updating an instructor
		Instructor updatedInstructor = instructorService.updateInstructor(instructor);

		Assertions.assertEquals(instructor, updatedInstructor);
	}

}