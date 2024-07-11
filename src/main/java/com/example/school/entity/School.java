package com.example.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class School {

    @Id
    @GeneratedValue
    private Integer id;
    private String schoolName;
    private String email;
}
