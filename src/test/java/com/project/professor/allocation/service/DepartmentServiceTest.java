package com.project.professor.allocation.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

	
	@Autowired
	DepartmentService deptService;
	
	@Test
	public void create() {
		
		Department dept = new Department();
		
		dept.setName("Biológicas - Graduação");
		
		dept = deptService.create(dept);
		
		System.out.println(dept);
		
	}
	
	@Test
	public void findAll()
	{
		System.out.println(deptService.findAll(null));
		
	}	
	
	public void findById() {
		
		Long id = 1L;
		
		Department dept = deptService.findById(id);
		
		System.out.println(dept);
		
	}
}
