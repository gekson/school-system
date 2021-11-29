package com.metadata.schoolsystem.repository;

import com.metadata.schoolsystem.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("SELECT c FROM Course c JOIN Registration r ON c.id = r.course.id WHERE r.student.name = :name")
    List<Course> findCoursesByStudent(@Param("name") String name);

    @Query("SELECT count (c) FROM Course c JOIN Registration r ON c.id = r.course.id WHERE r.student.name = :name")
    Integer findSizeCoursesByStudent(@Param("name") String name);

    @Query("SELECT c FROM Course c WHERE NOT EXISTS (SELECT r FROM Registration r WHERE r.course.id = c.id)")
    List<Course> findCoursesWithoutStudent();
}
