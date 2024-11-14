package com.understanding.spring.data.spring_data.repo;

import com.understanding.spring.data.spring_data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
}