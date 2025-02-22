package com.understanding.spring.data.spring_data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","student"})
public class Enrollment {
    @Id
    //@Column(name = "fk_student_id")
    String id;
    LocalDateTime created;
    @OneToOne
    @MapsId
    @JoinColumn(name = "fk_student_id")
    private Student student;
}
