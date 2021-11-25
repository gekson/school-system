package com.metadata.schoolsystem.repository;

import com.metadata.schoolsystem.model.Registration;
import com.metadata.schoolsystem.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "registrations", path = "registrations")
public interface RegistrationRepository extends PagingAndSortingRepository<Registration, Long>  {

    List<Registration> findRegistrationsByStudent(Student student);
}
