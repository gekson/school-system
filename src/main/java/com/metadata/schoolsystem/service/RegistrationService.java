package com.metadata.schoolsystem.service;

import com.metadata.schoolsystem.exception.SizeCourseAndStudentException;
import com.metadata.schoolsystem.exception.EntityNotFoundException;
import com.metadata.schoolsystem.model.Course;
import com.metadata.schoolsystem.model.Registration;
import com.metadata.schoolsystem.model.Student;
import com.metadata.schoolsystem.repository.CourseRepository;
import com.metadata.schoolsystem.repository.RegistrationRepository;
import com.metadata.schoolsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    /**
     *
     * @param registration
     * @return
     */
    public Registration registerStudent(Registration registration) {

        validateSizeCoursesByStudent(registration.getStudent());
        validateSizeStudentsByCourse(registration.getCourse());

        registrationRepository.save(registration);

        return registration;
    }

    private boolean validateSizeStudentsByCourse(Course course) {
        try {
            if (studentRepository.findSizeStudentsByCourse(courseRepository.findById(course.getId()).get().getTitle()) < 50) {
                return true;
            }

            throw new SizeCourseAndStudentException("A course has 50 students maximum");
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("This Course not found");
        }
    }

    /**
     *
     * @param student
     * @return
     */
    private boolean validateSizeCoursesByStudent(Student student) {
        try {
            if (courseRepository.findSizeCoursesByStudent(studentRepository.findById(student.getId()).get().getName()) < 5) {
                return true;
            }

            throw new SizeCourseAndStudentException("A student can register to 5 course maximum");
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("This student not found");
        }
    }

//    public List<Registration> findRegistrationsByStudent(Student student) {
//        try {
//            return registrationRepository.findRegistrationsByStudent(studentRepository.findById(student.getId()).get());
//        } catch (NoSuchElementException e) {
//            throw new EntityNotFoundException("This student not found");
//        }
//    }
}
