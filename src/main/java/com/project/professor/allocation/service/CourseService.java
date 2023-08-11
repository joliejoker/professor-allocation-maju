package com.project.professor.allocation.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepo;

	public CourseService(CourseRepository courseRepo) {
		super();
		this.courseRepo = courseRepo;
	}
	
	public Course findById(Long id) {
		
		return courseRepo.findById(id).orElse(null);
		
	}
	
	
	public List<Course> findAll(){
		
		return courseRepo.findAll();
		
	}
	
	public Course create(Course course) {
		
		course.setId(null);
		
		return courseRepo.save(course);
		
	}
	
	public Course update(Course course) {
		
		Long courId = course.getId();
		
		if(!courseRepo.existsById(courId)) {
			return null;
		}
		
		return courseRepo.save(course);
		
	}
	
	public void deleteById(Long id) {
		
		if(!courseRepo.existsById(id)) {
			courseRepo.deleteById(id);
		}
		
	}
	
	public void deleteAll() {
		
		courseRepo.deleteAllInBatch();
		
	}
	
	
	
}
