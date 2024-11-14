package com.understanding.spring.data.spring_data;

import com.understanding.spring.data.spring_data.model.Department;
import com.understanding.spring.data.spring_data.model.Enrollment;
import com.understanding.spring.data.spring_data.model.Student;
import com.understanding.spring.data.spring_data.model.Subject;
import com.understanding.spring.data.spring_data.repo.DepartmentRepository;
import com.understanding.spring.data.spring_data.repo.StudentRepository;
import com.understanding.spring.data.spring_data.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping(path = "/subject")
    public Subject saveSubject(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    @PostMapping(path = "/dept")
    public Department saveDepartment(@RequestBody Department department){
        return departmentRepository.save(department);
    }

    @PostMapping(path = "/student")
    public Student saveStudent(@RequestBody Student student){
        Enrollment enrollment = new Enrollment();
        enrollment.setCreated(LocalDateTime.now());
        student.setEnrollment(enrollment);
        enrollment.setStudent(student);
        return studentRepository.save(student);
    }

    @GetMapping(path = "/subject")
    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    @GetMapping(path = "/dept")
    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    @GetMapping(path = "/student")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
}
