package com.understanding.spring.data.spring_data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "id")
    String id;
    String name;
    String age;
    String sex;
    @OneToOne(mappedBy = "student",cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    Enrollment enrollment;
    @ManyToOne
    @JoinColumn(name = "fk_dept_id")
    Department department;
}
