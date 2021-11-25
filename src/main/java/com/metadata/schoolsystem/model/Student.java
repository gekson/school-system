package com.metadata.schoolsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity to Students
 */
@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Registration> registrations;

    public Student(String name) {
        this.name = name;
    }

//    public Set<Registration> getRegistrations() {
//        if (registrations == null) {
//            registrations = new HashSet<>();
//            return registrations;
//        }
//        return registrations;
//    }

}
