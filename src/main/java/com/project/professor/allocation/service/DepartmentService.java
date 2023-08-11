package com.project.professor.allocation.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private DepartmentRepository departmentRepo;

	public DepartmentService(DepartmentRepository departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
	}
	
	public Department findById(Long id) {
		
		return departmentRepo.findById(id).orElse(null);
	}
	
	public List<Department> findAll(String name){
		
		if(name == null) {
			return departmentRepo.findAll();
		}
		
		return departmentRepo.findByNameContaining(name);
		
	}
	
	public Department create(Department department) {
		
		department.setId(null);
		
		return departmentRepo.save(department);
		
	}
	
	public Department update(Department department) {
		
		Long dptId = department.getId();
		
		if(!departmentRepo.existsById(dptId)) {
			return null;
		}
		
		return departmentRepo.save(department);
		
	}
	
	public void deleteById(Long id) {
		
		if(departmentRepo.existsById(id)) {
			departmentRepo.deleteById(id);
		}
		
	}
	
	public void deleteAll() {
		
		departmentRepo.deleteAllInBatch();
		
	}
	
}
