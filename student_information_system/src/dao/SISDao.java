package dao;

import entity.*;
import exception.*;
import java.util.Date;
import java.util.List;

public interface SISDao {
   
    void addStudent(Student student);
    Student getStudentById(int studentId) throws StudentNotFoundException;
    void updateStudent(Student student);
    List<Student> getAllStudents();

    void addCourse(Course course);
    Course getCourseByCode(String courseCode) throws CourseNotFoundException;
    void updateCourse(Course course);
    List<Course> getAllCourses();

    void addTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId) throws TeacherNotFoundException;
    void updateTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();

    void enrollStudentInCourse(Enrollment enrollment);
    List<Enrollment> getEnrollmentsByStudentId(int studentId) throws EnrollmentException;
    List<Enrollment> getEnrollmentsByCourseName(String courseName);

    void addPayment(Payment payment);
    List<Payment> getPaymentsByStudentId(int studentId) throws PaymentException;

    void assignTeacherToCourse(int courseId, int teacherId);
    List<Enrollment> generateEnrollmentReport(int courseId);
    List<Payment> generatePaymentReport(int studentId);
    double calculateTotalPaymentsForCourse(String courseName);
}