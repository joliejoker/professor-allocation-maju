package com.project.professor.allocation.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private AllocationRepository allocationRepo;
	private ProfessorService profServ;
	private CourseService courseServ;

	public AllocationService(AllocationRepository allocationRepo, 
							 ProfessorService profServ_, 
							 CourseService courseServ_) {
		super();
		this.allocationRepo = allocationRepo;
		this.profServ = profServ_;
		this.courseServ = courseServ_;
	}

	public Allocation findById(Long id) {

		return allocationRepo.findById(id).orElse(null);

	}
	
	public List<Allocation> findAll(){
		
		return allocationRepo.findAll();
		
	}
	
	public List<Allocation> findByProfessorId(Long profId){
		
		return allocationRepo.findByProfessorId(profId);
		
	}
	
	public List<Allocation> findByCourseId(Long courseId){
		
		return allocationRepo.findByCourseId(courseId);
		
	}

	public Allocation create(Allocation allocation) {

		allocation.setId(null);
		
		return saveAllocation(allocation);
		
		

	}
	
	public Allocation update(Allocation allocation) {
		
		if(!allocationRepo.existsById(allocation.getId())) {
			
			return null;
				
		}
		
		return saveAllocation(allocation);
		
	}
	
	private Allocation saveAllocation(Allocation allocation) {
		
		Allocation aloc = allocationRepo.save(allocation);
		
		Professor prof = profServ.findById(allocation.getProfessorId());
		
		aloc.setProfessor(prof);
		
		Course cour = courseServ.findById(allocation.getCourseId());
		
		aloc.setCourse(cour);
		
		return aloc;
		
	}
	
	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepo.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}

	
	public void deleteById(Long id) {
		
		if(!allocationRepo.existsById(id)) {
			allocationRepo.deleteById(id);
		}
	}
	
	public void deleteAll() {
		
		allocationRepo.deleteAllInBatch();
		
	}

}
