package com.metadata.schoolsystem.repository;

import com.metadata.schoolsystem.model.Course;
import com.metadata.schoolsystem.model.Registration;
import com.metadata.schoolsystem.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void should_find_no_students_if_studentRepository_is_empty() {
        Iterable<Student> students = studentRepository.findAll();

        assertThat(students).isEmpty();
    }

    @Test
    public void should_store_a_student() {
        Student student = new Student();
        student.setName("Tom");
        studentRepository.save(student);

        assertThat(student).hasFieldOrPropertyWithValue("name", "Tom");
    }

    @Test
    public void should_find_all_students() {
        Student student1 = new Student("Student#1");
        entityManager.persist(student1);

        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student student3 = new Student("Student#3");
        entityManager.persist(student3);

        Iterable<Student> students = studentRepository.findAll();

        assertThat(students).hasSize(3).contains(student1, student2, student3);
    }

    @Test
    public void should_find_student_by_id() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Student foundStudent = studentRepository.findById(student.getId()).get();

        assertThat(foundStudent).isEqualTo(student);
    }

    @Test
    public void should_update_student_by_id() {
        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student updatedStudent = new Student("updated Student#2");

        Student student = studentRepository.findById(student2.getId()).get();
        student.setName(updatedStudent.getName());
        studentRepository.save(student);

        Student checkStudent = studentRepository.findById(student2.getId()).get();

        assertThat(checkStudent.getId()).isEqualTo(student2.getId());
        assertThat(checkStudent.getName()).isEqualTo(updatedStudent.getName());
    }

    @Test
    public void should_delete_student_by_id() {
        Student student1 = new Student("Student#1");
        entityManager.persist(student1);

        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student student3 = new Student("Student#3");
        entityManager.persist(student3);

        studentRepository.deleteById(student2.getId());

        Iterable<Student> students = studentRepository.findAll();

        assertThat(students).hasSize(2).contains(student1, student3);
    }

    @Test
    public void should_store_integration() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Course course = new Course("Course#1");
        entityManager.persist(course);

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setGrade(5);
        registration.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration);

        Registration checkRegistration = registrationRepository.findById(registration.getId()).get();

        assertThat(checkRegistration.getStudent()).isEqualTo(student);
        System.out.println(checkRegistration.getStudent());
    }

    @Test
    public void should_find_integrations_by_student() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Course course = new Course("Course#1");
        entityManager.persist(course);

        Course course2 = new Course("Course#2");
        entityManager.persist(course2);

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setGrade(5);
        registration.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration);

        Registration registration2 = new Registration();
        registration2.setStudent(student);
        registration2.setCourse(course2);
        registration2.setGrade(10);
        registration2.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration2);

        List<Registration> checkRegistration = registrationRepository.findRegistrationsByStudent(student);

        assertThat(checkRegistration).hasSize(2).contains(registration);
    }

    @Test
    public void should_find_students_by_course() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student student3 = new Student("Student#3");
        entityManager.persist(student3);

        Course course = new Course("Course#1");
        entityManager.persist(course);

        Course course2 = new Course("Course#2");
        entityManager.persist(course2);

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setGrade(5);
        registration.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration);

        Registration registration2 = new Registration();
        registration2.setStudent(student2);
        registration2.setCourse(course);
        registration2.setGrade(10);
        registration2.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration2);

        Registration registration3 = new Registration();
        registration3.setStudent(student);
        registration3.setCourse(course2);
        registration3.setGrade(7);
        registration3.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration3);

        List<Student> checkStudents = studentRepository.findStudentsByCourse(course.getTitle());

        assertThat(checkStudents).hasSize(2).contains(student);
    }

    @Test
    public void should_find_courses_by_students() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student student3 = new Student("Student#3");
        entityManager.persist(student3);

        Course course = new Course("Course#1");
        entityManager.persist(course);

        Course course2 = new Course("Course#2");
        entityManager.persist(course2);

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setGrade(5);
        registration.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration);

        Registration registration2 = new Registration();
        registration2.setStudent(student2);
        registration2.setCourse(course);
        registration2.setGrade(10);
        registration2.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration2);

        Registration registration3 = new Registration();
        registration3.setStudent(student);
        registration3.setCourse(course2);
        registration3.setGrade(7);
        registration3.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration3);

        List<Course> checkCourses = courseRepository.findCoursesByStudent(student.getName());

        assertThat(checkCourses).hasSize(2).contains(course);
    }

    @Test
    public void should_find_courses_without_students() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student student3 = new Student("Student#3");
        entityManager.persist(student3);

        Course course = new Course("Course#1");
        entityManager.persist(course);

        Course course2 = new Course("Course#2");
        entityManager.persist(course2);

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setGrade(5);
        registration.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration);

        Registration registration2 = new Registration();
        registration2.setStudent(student2);
        registration2.setCourse(course);
        registration2.setGrade(10);
        registration2.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration2);

        Registration registration3 = new Registration();
        registration3.setStudent(student3);
        registration3.setCourse(course);
        registration3.setGrade(7);
        registration3.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration3);

        List<Course> checkCourses = courseRepository.findCoursesWithoutStudent();

        assertThat(checkCourses).hasSize(1).contains(course2);
    }

    @Test
    public void should_find_students_without_courses() {
        Student student = new Student("Student#1");
        entityManager.persist(student);

        Student student2 = new Student("Student#2");
        entityManager.persist(student2);

        Student student3 = new Student("Student#3");
        entityManager.persist(student3);

        Course course = new Course("Course#1");
        entityManager.persist(course);

        Course course2 = new Course("Course#2");
        entityManager.persist(course2);

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setGrade(5);
        registration.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration);

        Registration registration2 = new Registration();
        registration2.setStudent(student2);
        registration2.setCourse(course);
        registration2.setGrade(10);
        registration2.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration2);

        Registration registration3 = new Registration();
        registration3.setStudent(student2);
        registration3.setCourse(course2);
        registration3.setGrade(7);
        registration3.setRegisteredAt(LocalDateTime.now());
        entityManager.persist(registration3);

        List<Student> checkStudents = studentRepository.findStudentsWithoutCourse();

        assertThat(checkStudents).hasSize(1).contains(student3);
    }
}
