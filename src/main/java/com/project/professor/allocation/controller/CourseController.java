package com.project.professor.allocation.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/courses")
@Tag(name="Courses")
public class CourseController {

	private CourseService courseServ;

	public CourseController(CourseService courseServ) {
		super();
		this.courseServ = courseServ;
	}
	
	@Operation(summary="Getting all courses from database")
	@ApiResponses({
		@ApiResponse(responseCode="404",description="Courses doesn't have any record.",content=@Content)
	})
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> findAllCourse(){
		
		List<Course> course = courseServ.findAll();
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@Operation(summary="Getting a course by ID from database")
	@ApiResponses({
		@ApiResponse(responseCode="404",description="Course not founded",content=@Content)
	})
	@GetMapping(path="/{course_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> findByIdDpt(@PathVariable(name="course_id") Long id){
		
		Course course = courseServ.findById(id);
		
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(course, HttpStatus.OK);
		
	}
	
	@Operation(summary="Inserting a course into database")
	@ApiResponses({
		@ApiResponse(responseCode="201",description="Created sucessfully"),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content)
	})
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> create(@RequestBody Course course){
		
		try {
			
			Course course2 = courseServ.create(course);
			return new ResponseEntity<>(course2, HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@Operation(summary="Updating an existing course in database")
	@PutMapping(path="/course_id",
			    consumes=MediaType.APPLICATION_JSON_VALUE,
			    produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> update(@PathVariable(name="course_id")Long id, @RequestBody Course course){
		
		
		try {
			course.setId(id);
			
			Course course2 = courseServ.update(course);
			
			if(course2 == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(course2, HttpStatus.OK);
	
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@Operation(summary="Deleting an existing course in database")
	@DeleteMapping(path="/{course_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="course_id") Long id){
		
		courseServ.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@Operation(summary="Deleting all courses in database")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		
		courseServ.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
