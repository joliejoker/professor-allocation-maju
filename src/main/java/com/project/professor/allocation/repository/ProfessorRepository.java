package com.project.professor.allocation.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.professor.allocation.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	Professor findByCpf(String mamute);
	
	List<Professor> findByDepartmentId(Long dptId);
	
	List<Professor> findByNameContaining(String name);
	
}
