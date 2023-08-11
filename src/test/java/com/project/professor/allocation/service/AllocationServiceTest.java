package com.project.professor.allocation.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.DayOfWeek;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

    @Autowired
    AllocationService allocationService;

    @Test
    public void create() throws ParseException {
        
    	Allocation aloc = new Allocation();
    	
    	aloc.setDay(DayOfWeek.Thursday);
    	aloc.setStart(sdf.parse("06:00-0300"));
    	aloc.setEnd(sdf.parse("07:00-0300"));
    	aloc.setProfessorId(1L);
    	aloc.setCourseId(1L);
    	
    	Allocation alloc = allocationService.create(aloc);
    	
    	System.out.println(alloc);
    	
    }
    
    @Test
    public void findAllAlloc() {
    	
    	List<Allocation> allocations = allocationService.findAll();
    	
    	System.out.println(allocations);
    	
    }
    
    @Test
    public void findById() {
    	
    	Long id = 1L;
    	
    	Allocation allocation = allocationService.findById(id);
    	
    	System.out.println(allocation);
    	
    }
    
    @Test
    public void findByProfessor() {
    	
    	Long profId = 1L;
    	
    	List<Allocation> allocations = allocationService.findByProfessorId(profId);
    	
    	System.out.println(allocations);
    	
    }
    
    @Test
    public void findByCourseId() {
    	
    	Long courseId = 1L;
    	
    	List<Allocation> allocs = allocationService.findByCourseId(courseId);
    	
    	System.out.println(allocs);
    	
    }
    
    @Test
    public void update() throws ParseException {
    	
    	Allocation aloc = new Allocation();
    	
    	aloc.setId(1L);
    	aloc.setDay(DayOfWeek.Monday);
    	aloc.setStart(sdf.parse("19:00-0300"));
    	aloc.setEnd(sdf.parse("22:00-0300"));
    	aloc.setProfessorId(1L);
    	aloc.setCourseId(3L);
    	
    	aloc = allocationService.update(aloc);
    	
    	System.out.println(aloc);
    	
    }
    /*
    @Test
    public void deleteById() {

        Long id = 1L;

        allocationService.deleteById(id);
    }

    @Test
    public void deleteAll() {

        allocationService.deleteAll();
    }
    */
}
