package com.lsy.spring.data.jpatutorial.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lsy.spring.data.jpatutorial.entity.Course;
import com.lsy.spring.data.jpatutorial.entity.CourseMaterial;

@SpringBootTest
public class CourseMaterialRepositoryTest {
    
    
    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder()
                            .title("USA")
                            .credit(6)
                            .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()       
                                            .url("www.google.com")
                                            .course(course)
                                            .build();
        repository.save(courseMaterial);
                                            
    }

    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = 
                repository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);
    }
}
