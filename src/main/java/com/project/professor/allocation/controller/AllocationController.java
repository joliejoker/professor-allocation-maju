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
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/allocations")
@Tag(name="Allocations")
public class AllocationController {

	private AllocationService allocService;

	public AllocationController(AllocationService allocService) {
		super();
		this.allocService = allocService;
	}
	
	@Operation(summary="Getting all allocation from database")
	@ApiResponses({
		@ApiResponse(responseCode="200",content=@Content),
		@ApiResponse(responseCode="400",description="Error:",content=@Content)
	})
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAllAlloc(){
		
		List<Allocation> allocs = allocService.findAll();
		
		return new ResponseEntity<>(allocs, HttpStatus.OK);
		
	}
	
	@Operation(summary="Getting an allocation by ID from database")
	@ApiResponses({
		@ApiResponse(responseCode="200",content=@Content),
		@ApiResponse(responseCode="400",description="Error:",content=@Content),
		@ApiResponse(responseCode="404",description="Allocation not founded",content=@Content)
	})
	@GetMapping(path="/{allocation_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findByIdAlloc(@PathVariable(name="allocation_id") Long id){
		
		Allocation alloc = allocService.findById(id);
		
		if(alloc == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(alloc, HttpStatus.OK);
		
	}
	
	@ApiResponses({
		@ApiResponse(responseCode="201",description="Created sucessfully"),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content)
	})
	@Operation(summary="Inserting an allocation into database")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> create(@RequestBody Allocation alloc){
		
		try {
			
			Allocation alloca = allocService.create(alloc);
			return new ResponseEntity<>(alloca, HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@ApiResponses({
		@ApiResponse(responseCode="200",description="Updated sucessfully"),
		@ApiResponse(responseCode="404",description="Allocation not founded",content=@Content),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content)
	})
	@Operation(summary="Updating an existing allocation in database")
	@PutMapping(path="/{allocation_id}",
			    consumes=MediaType.APPLICATION_JSON_VALUE,
			    produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@PathVariable(name="allocation_id")Long id, @RequestBody Allocation alloc){
		
		
		try {
			alloc.setId(id);
			
			Allocation alloca = allocService.update(alloc);
			
			if(alloca == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(alloca, HttpStatus.OK);
	
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	@ApiResponses({
		@ApiResponse(responseCode="204",description="Deleted sucessfully"),
		@ApiResponse(responseCode="400",description="Error: ",content=@Content)
	})
	@Operation(summary="Deleting an existing allocation in database")
	@DeleteMapping(path="/{allocation_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="allocation_id") Long id){
		
		allocService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ApiResponses({
		@ApiResponse(responseCode="204",description="Deleted all professors sucessfully"),
	})
	@Operation(summary="Deleting all allocations in database")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		
		allocService.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
