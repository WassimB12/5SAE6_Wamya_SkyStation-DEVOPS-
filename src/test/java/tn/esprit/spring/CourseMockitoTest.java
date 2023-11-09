package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controllers.CourseRestController;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.services.ICourseServices;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CourseMockitoTest {

    @Mock
    ICourseServices courseServices;

    @InjectMocks
    CourseRestController courseRestController;

    Course c = new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100F, 60, new HashSet<>());
    Course c2 = new Course(2L, 2, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 200F, 120, new HashSet<>());
    Set<Registration> registrations = new HashSet<>();

    @Test
    public void addCourseTest() {
        Mockito.when(courseServices.addCourse(Mockito.any(Course.class))).then(invocation -> {
            Course model = invocation.getArgument(0, Course.class);
            model.setNumCourse(1L);
            return model;
        });
        log.info("Before ==> " + c.toString());
        Course course = courseRestController.addCourse(c);
        assertSame(course, c);
        log.info("After ==> " + c.toString());
    }

    @Test
    public void retrieveCourseTest() {
        Mockito.when(courseServices.retrieveCourse(Mockito.anyLong())).thenReturn(c);
        Course course = courseRestController.getById(1L);
        assertNotNull(course);
        log.info("Retrieved Course ==> " + course.toString());
        verify(courseServices).retrieveCourse(Mockito.anyLong());
    }

    @Test
    public void retrieveAllCoursesTest() {
        Mockito.when(courseServices.retrieveAllCourses()).thenReturn(List.of(c));
        List<Course> courses = courseRestController.getAllCourses();
        assertNotNull(courses);
    }

    @Test
    public void updateCourseTest() {
        Mockito.when(courseServices.updateCourse(Mockito.any(Course.class))).thenReturn(c);
        Course updatedCourse = courseRestController.updateCourse(c);
        assertNotNull(updatedCourse);
    }
}
