package com.project.professor.allocation.service;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService profService;
	
	@Test
	public void create() {
		
		Professor prof = new Professor();
		
		prof.setName("Keven");
		prof.setCpf("345.678.910-23");
		prof.setDepartmentId(4L);
	
		Professor profe = profService.create(prof);
		
		System.out.println(profe);
	}
	
	@Test
	public void findAll() {
		
		List<Professor> prof = profService.findAll();
		
		System.out.println(prof);	
	}
	
	@Test
	public void findByDptId() {
		
		Long id = 1L;
		
		List<Professor> prof = profService.findByDeptId(id);
		
		System.out.println(prof);
		
	}
	
	@Test
	public void findByNameContaining() {
		
		String name = "Breno";
		
		List<Professor> prof = profService.findByNameContaining(name);
		
		System.out.println(prof);
		
	}
	
	@Test
	public void findById() {
		
		Long id = 2L;
		
		Professor prof = profService.findById(id);
		
		System.out.println(prof);
		
	}
	
	@Test
	public void update() {
		
		Professor prof = new Professor();
		
		prof.setId(1L);
		prof.setName("Amirton Chagas");
		
		prof = profService.update(prof);
		
		System.out.println(prof);
		
	}
	
}
