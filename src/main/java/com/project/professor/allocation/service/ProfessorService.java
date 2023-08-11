package com.project.professor.allocation.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private ProfessorRepository professorRepo;
	private DepartmentService dptService;

	public ProfessorService(ProfessorRepository professorRepo,
							DepartmentService dptService) {
		super();
		this.professorRepo = professorRepo;
		this.dptService = dptService;
	}
	
	public Professor findById(Long id) {
		
		return professorRepo.findById(id).orElse(null);
		
	}
	
	public List<Professor> findByDeptId(Long id) {
		
		return professorRepo.findByDepartmentId(id);
	}
	
	public List<Professor> findByNameContaining(String name){
		
		return professorRepo.findByNameContaining(name);
		
	}
	
	public List<Professor> findAll() {
		
		return professorRepo.findAll();
	}
	
	
	public Professor create(Professor professor) {
		
		professor.setId(null);
		
		return saveProfessor(professor);
		
		
	}
	
	public Professor update(Professor professor) {
		
		if(!professorRepo.existsById(professor.getId())) {
			return null;
		}
		
		return saveProfessor(professor);
		
	}
	
	private Professor saveProfessor(Professor professor) {
		
		Professor prof = professorRepo.save(professor);
		
		Long dptId = professor.getDepartmentId();
		
		Department dpt = dptService.findById(dptId);
		
		prof.setDepartment(dpt);
		
		return prof;
		
	}
	
	public void deleteById(Long id) {
		
		if(professorRepo.existsById(id)) {
			professorRepo.deleteById(id);
		}
		
	}
	
	public void deleteAll() {
		
		professorRepo.deleteAllInBatch();
		
	}
	
	
}
