package com.understanding.spring.data.spring_data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    @ManyToMany
            @JoinTable(
                    name = "dept_subjects",
                    joinColumns = @JoinColumn(name = "fk_dept_id"),
                    inverseJoinColumns = @JoinColumn(name="fk_subject_id")
            )
    List<Subject> subjects;

    @OneToMany(mappedBy = "department")
    List<Student> student=new ArrayList<>();
}
