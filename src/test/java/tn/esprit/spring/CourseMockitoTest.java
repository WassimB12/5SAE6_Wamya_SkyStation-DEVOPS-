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
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CourseMockitoTest {

    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    CourseRestController courseRestController;

    Course c = new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100F, 60, new HashSet<>());
    Course c2 = new Course(2L, 2, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 200F, 120, new HashSet<>());
    List<Course> listcourse = new ArrayList<>();

    @Test
    public void addCourseTest() {
        when(courseRepository.save(Mockito.any(Course.class))).thenReturn(c);
        log.info("Before ==> " + c.toString());
        Course course = courseRepository.save(c);
        assertSame(course, c);
        log.info("After ==> " + c.toString());
    }

    @Test
    public void retrieveCourseTest() {
        when(courseRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(c));
        Course course = courseRepository.findById(1L).orElse(null);
        assertNotNull(course);
        verify(courseRepository).findById(Mockito.anyLong());
    }

    @Test
    public void retrieveAllCoursesTest() {
        listcourse.add(c);
        listcourse.add(c2);
        when(courseRepository.findAll()).thenReturn(listcourse);
        List<Course> courses = courseRepository.findAll();
        assertNotNull(courses);
    }

    @Test
    public void updateCourseTest() {
        when(courseRepository.save(Mockito.any(Course.class))).thenReturn(c);
        Course updatedCourse = courseRepository.save(c);
        assertNotNull(updatedCourse);
    }
}
