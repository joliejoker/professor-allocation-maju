package com.project.professor.allocation.repository;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	@Autowired
    ProfessorRepository professorRepository;
	
	@Test
	public void create() {
		Professor prof = new Professor();
		
		prof.setName("Amirton");
		prof.setCpf("012.345.678.92");
		prof.setDepartmentId(1L);
		//012.345.678.91
		Professor prof2 = professorRepository.save(prof);
		
		System.out.println(prof2);
		
	}
	
	@Test
	public void findAll() {
		
		System.out.println(professorRepository.findAll());
		
	}
	
	@Test
	public void deleteById() {
		Long id = 1L;
		
		professorRepository.deleteById(id);
		
	}
	
	@Test
	public void findByCpf() {
		
		String cpf = "123.456.789.12";
		
		Professor prof = professorRepository.findByCpf(cpf);
		
		System.out.println(prof);
		
	}
	
	@Test
	public void findByDptId() {
		
		Long id = 1L;
		
		List<Professor> prof = professorRepository.findByDepartmentId(id);
		
		System.out.println(prof);
		
	}
	
}
