package com.metadata.schoolsystem.repository;

import com.metadata.schoolsystem.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    @Query("SELECT s FROM Student s JOIN Registration r ON s.id = r.student.id WHERE r.course.title = :title")
    List<Student> findStudentsByCourse(@Param("title") String title);

    @Query("SELECT count(s) FROM Student s JOIN Registration r ON s.id = r.student.id WHERE r.course.title = :title")
    Integer findSizeStudentsByCourse(@Param("title") String title);

    @Query("SELECT s FROM Student s WHERE NOT EXISTS (SELECT r FROM Registration r WHERE r.student.id = s.id)")
    List<Student> findStudentsWithoutCourse();
}
