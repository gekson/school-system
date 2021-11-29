package com.metadata.schoolsystem.repository;

import com.metadata.schoolsystem.model.Registration;
import com.metadata.schoolsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

//    List<Registration> findRegistrationsByStudent(Student student);
}
