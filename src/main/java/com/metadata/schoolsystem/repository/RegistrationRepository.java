package com.metadata.schoolsystem.repository;

import com.metadata.schoolsystem.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
