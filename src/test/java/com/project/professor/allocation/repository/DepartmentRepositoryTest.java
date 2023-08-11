package com.project.professor.allocation.repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;
    
    @Test
    public void create() {
    	Department dpt = new Department();
		dpt.setName("Desenvolvimento de Software");
		
		Department dpt2 = departmentRepository.save(dpt);
		
		System.out.println(dpt2);
    }
    
    @Test
	public void readById() {
		Long dptId = 10L;
		
		Department dpt = departmentRepository.findById(dptId).orElse(null);
		
		System.out.println(dpt);
	}
	
	@Test
	public void update() {
		Department dpt = new Department();
		dpt.setId(10L);
		dpt.setName("Matemática Aplicada 2");
		
		Department dpt2 = departmentRepository.save(dpt);
		
		System.out.println(dpt2);
	}    
}