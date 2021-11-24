package com.metadata.schoolsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


/**
 * Entity to Courses
 */
@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;

    @OneToMany(mappedBy = "courses")
    Set<Registration> registrations;
}
