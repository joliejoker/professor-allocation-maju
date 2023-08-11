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
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/professors")
@Tag(name="Professors")
public class ProfessorController {

	private ProfessorService profService;

	public ProfessorController(ProfessorService profService) {
		super();
		this.profService = profService;
	}

	@Operation(summary="Getting all professors from database")
	@ApiResponses({
		@ApiResponse(responseCode="200",content=@Content),
		@ApiResponse(responseCode="400",description="Error:",content=@Content)
	})
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Professor>> findAllProf(){
		
		List<Professor> prof = profService.findAll();
		
		return new ResponseEntity<>(prof, HttpStatus.OK);
		
	}
	
	@Operation(summary="Getting a professor by department ID from database")
	@GetMapping(path="/dpt/{department_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(responseCode="200",content=@Content),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content),
		@ApiResponse(responseCode="404",description="Professor not founded",content=@Content)
	})
	public ResponseEntity<List<Professor>> findByDeptId(@PathVariable(name="department_id")Long id){
		
		List<Professor> prof = profService.findByDeptId(id);
		
		return new ResponseEntity<>(prof, HttpStatus.OK);
		
	}
	
	@Operation(summary="Getting a professor by ID from database")
	@ApiResponses({
		@ApiResponse(responseCode="200",content=@Content),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content),
		@ApiResponse(responseCode="404",description="Professor not founded",content=@Content)
	})
	@GetMapping(path="/{professor_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> findByIdProf(@PathVariable(name="professor_id") Long id){
		
		Professor prof = profService.findById(id);
		
		if(prof == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(prof, HttpStatus.OK);
		
	}
	
	@Operation(summary="Inserting a professor into database")
	@ApiResponses({
		@ApiResponse(responseCode="201",description="Created sucessfully"),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content)
	})
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> create(@RequestBody Professor prof){
		
		try {
			
			Professor profa = profService.create(prof);
			return new ResponseEntity<>(profa, HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@Operation(summary="Updating an existing professor in database")
	@PutMapping(path="/{professor_id}",
			    consumes=MediaType.APPLICATION_JSON_VALUE,
			    produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> update(@PathVariable(name="professor_id")Long id, @RequestBody Professor prof){
		
		
		try {
			prof.setId(id);
			
			Professor Profa = profService.update(prof);
			
			if(Profa == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(Profa, HttpStatus.OK);
	
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@Operation(summary="Deleting an existing professor in database")
	@DeleteMapping(path="/{professor_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="professor_id") Long id){
		
		profService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@Operation(summary="Deleting all professors in database")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		
		profService.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
