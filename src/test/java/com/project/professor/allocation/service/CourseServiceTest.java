package com.project.professor.allocation.service;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
	CourseService courseService;
	
	@Test
	public void create() {
		
		Course curso = new Course();
		
		curso.setName("Desenvolvimento Web Front-End");
		
		Course course = courseService.create(curso);
		
		System.out.println(course);
		
	}
	
	@Test
	public void findAll() {
		
		List<Course> curso = courseService.findAll();
		
		System.out.println(curso);
		
	}
	
	@Test
	public void findById() {
		
		Long id = 8L;
		
		Course curso = courseService.findById(id);
		
		System.out.println(curso);
		
	}
	
	@Test
	public void update() {
		
		Course curso = new Course();
		
		curso.setId(1L);
		curso.setName("Desenvolvimento de Software");
		
		curso = courseService.update(curso);
		
		System.out.println(curso);
		
	}	
}
