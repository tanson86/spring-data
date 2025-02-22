package com.understanding.spring.data.spring_data.repo;

import com.understanding.spring.data.spring_data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,String> {
}
