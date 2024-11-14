package com.understanding.spring.data.spring_data.repo;

import com.understanding.spring.data.spring_data.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {
}
