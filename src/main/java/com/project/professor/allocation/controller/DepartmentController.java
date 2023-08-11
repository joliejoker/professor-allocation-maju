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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/departments")
@Tag(name="Departments")
public class DepartmentController {

	private DepartmentService dptServ;

	public DepartmentController(DepartmentService dptServ) {
		super();
		this.dptServ = dptServ;
	}
	
	@Operation(summary="Getting all departments from database")
	@ApiResponses({
		@ApiResponse(responseCode="404",description="Professors doesn't have any record.",content=@Content)
	})
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> findAllDpt(@RequestParam(name="name",required=false) String name){
		
		List<Department> dpt = dptServ.findAll(name);
		
		return new ResponseEntity<>(dpt, HttpStatus.OK);
	}
	
	@Operation(summary="Getting a department by ID from database")
	@ApiResponses({
		@ApiResponse(responseCode="404",description="Professor not founded",content=@Content)
	})
	@GetMapping(path="/{department_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> findByIdDpt(@PathVariable(name="department_id") Long id){
		
		Department dpt = dptServ.findById(id);
		
		if(dpt == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(dpt, HttpStatus.OK);
		
	}
	
	@ApiResponses({
		@ApiResponse(responseCode="201",description="Created sucessfully"),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content)
	})
	@Operation(summary="Inserting a department into database")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> create(@RequestBody Department dpt){
		
		try {
			
			Department dpt2 = dptServ.create(dpt);
			return new ResponseEntity<>(dpt2, HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@Operation(summary="Updating an existing professor in database")
	@PutMapping(path="/department_id",
			    consumes=MediaType.APPLICATION_JSON_VALUE,
			    produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> update(@PathVariable(name="department_id")Long id, @RequestBody Department dpt){
		
		
		try {
			dpt.setId(id);
			
			Department dpt2 = dptServ.update(dpt);
			
			if(dpt2 == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(dpt2, HttpStatus.OK);
	
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@Operation(summary="Deleting an existing department in database")
	@DeleteMapping(path="/{department_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="department_id") Long id){
		
		dptServ.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@Operation(summary="Deleting all professors in database")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		
		dptServ.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
