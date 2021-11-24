package com.metadata.schoolsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity to Students
 */
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @OneToMany(mappedBy = "student")
    Set<Registration> registrations;
}
