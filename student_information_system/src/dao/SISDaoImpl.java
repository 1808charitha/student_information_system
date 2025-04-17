package dao;

import dao.SISDao;
import db.DBUtil;
import entity.*;
import exception.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SISDaoImpl implements SISDao {

    @Override
    public void addStudent(Student student) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "INSERT INTO students (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int studentId) throws StudentNotFoundException {
        Student student = null;
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
            } else {
                throw new StudentNotFoundException("with ID " + studentId);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "UPDATE students SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ? WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhoneNumber());
            ps.setInt(6, student.getStudentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ----------------- COURSE -------------------
    @Override
    public void addCourse(Course course) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "INSERT INTO courses (course_name, course_code, instructor_name) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getInstructorName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourseByCode(String courseCode) throws CourseNotFoundException {
        Course course = null;
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM courses WHERE course_code = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getString("instructor_name")
                );
            }else {
                throw new CourseNotFoundException("with Code " + courseCode);}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "UPDATE courses SET course_name = ?, course_code = ?, instructor_name = ? WHERE course_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getInstructorName());
            ps.setInt(4, course.getCourseId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM courses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getString("instructor_name")
                );
                list.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "INSERT INTO teacher (first_name, last_name, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws TeacherNotFoundException{
        Teacher teacher = null;
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM teacher WHERE teacher_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("department")
                );
            }else {
                throw new TeacherNotFoundException("ERROR: " + teacherId);}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "UPDATE teacher SET first_name = ?, last_name = ?, email = ? WHERE teacher_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getEmail());
            ps.setInt(5, teacher.getTeacherId());
            ps.executeUpdate();
        } 
           catch (SQLException e) {
            e.printStackTrace();
        }
     }
    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM teacher";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("department")
                );
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void enrollStudentInCourse(Enrollment enrollment) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) throws EnrollmentException {
        List<Enrollment> list = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM enrollments WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date").toLocalDate()
                );
                list.add(enrollment);
            }
           
            if (list.isEmpty()) {
                throw new EnrollmentException("No enrollments found for student ID: " + studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Enrollment> getEnrollmentsByCourseName(String courseName) {
        List<Enrollment> list = new ArrayList<>();
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT e.* FROM enrollments e JOIN course c ON e.course_id = c.course_id WHERE c.course_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date").toLocalDate()
                );
                list.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payments (student_id, amount, payment_date) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getStudentId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getPaymentsByStudentId(int studentId) throws PaymentException {
        String sql = "SELECT payment_id, student_id, amount, payment_date FROM payments WHERE student_id = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("student_id"),
                    rs.getDouble("amount"),
                    rs.getDate("payment_date").toLocalDate()
                );
                payments.add(payment);
            }

            if (payments.isEmpty()) {
                throw new PaymentException("No payments found for student ID: " + studentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    @Override
    public void assignTeacherToCourse(int courseId, int teacherId) {
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "UPDATE courses SET instructor_name = (SELECT CONCAT(first_name, ' ', last_name) FROM teacher WHERE teacher_id = ?) WHERE course_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, teacherId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enrollment> generateEnrollmentReport(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT * FROM enrollments WHERE course_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                    rs.getInt("enrollment_id"),
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getDate("enrollment_date").toLocalDate()
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    
    @Override
    public List<Payment> generatePaymentReport(int studentId) {
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT payment_id, student_id, amount, payment_date FROM payments WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("student_id"),
                    rs.getDouble("amount"),
                    rs.getDate("payment_date").toLocalDate()
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }


    @Override
    public double calculateTotalPaymentsForCourse(String courseName) {
        double total = 0;
        try (Connection conn = DBUtil.getDBConn()) {
            String sql = "SELECT SUM(p.amount) AS total FROM payments p " +
                         "JOIN enrollments e ON p.student_id = e.student_id " +
                         "JOIN courses c ON e.course_id = c.course_id " +
                         "WHERE c.course_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}