package main;
import java.time.LocalDate;
import java.util.*;
import entity.*;
import exception.*;
import dao.*;
public class mainModule {
	private static final Scanner sc = new Scanner(System.in);
    private static final SISDao dao = new SISDaoImpl(); 

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n***Student Information System***");
            System.out.println("1. Add Student");
            System.out.println("2. Get Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Get All Students");
            System.out.println("5. Add Course");
            System.out.println("6. Get Course by Code");
            System.out.println("7. Update Course");
            System.out.println("8. Get All Courses");
            System.out.println("9. Add Teacher");
            System.out.println("10. Get Teacher by ID");
            System.out.println("11. Update Teacher");
            System.out.println("12. Get All Teachers");
            System.out.println("13. Enroll Student in Course");
            System.out.println("14. Get Enrollments by Student ID");
            System.out.println("15. Get Enrollments by Course Name");
            System.out.println("16. Add Payment");
            System.out.println("17. Get Payments by Student ID");
            System.out.println("18. Assign Teacher to Course");
            System.out.println("19. Generate Enrollment Report");
            System.out.println("20. Generate Payment Report");
            System.out.println("21. Calculate Total Payments for Course");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            try {
                switch (choice) {
                case 1: {
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter First Name: ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
                    LocalDate dob = LocalDate.parse(sc.nextLine());
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    Student student = new Student(studentId, firstName, lastName, dob, email, phone);
                    dao.addStudent(student);
                    System.out.println(" Student added successfully.");
                    break;
                    }
                
                case 2: {
                   
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();

                    try {
                        Student s = dao.getStudentById(sid);
                        System.out.println("Student Details: " + s);
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage()); 
                    }
                    break;
                }
                    
                    case 3: {
                       
                    	System.out.print("Enter Student ID to update: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Student student = dao.getStudentById(id);
                   

                        System.out.print("Enter new First Name (or press Enter to keep unchanged): ");
                        String fname = sc.nextLine();
                        if (!fname.isEmpty()) student.setFirstName(fname);

                        System.out.print("Enter new Last Name (or press Enter to keep unchanged): ");
                        String lname = sc.nextLine();
                        if (!lname.isEmpty()) student.setLastName(lname);

                        System.out.print("Enter new DOB (yyyy-mm-dd) (or press Enter to keep unchanged): ");
                        String dobInput = sc.nextLine();
                        if (!dobInput.isEmpty()) student.setDateOfBirth(LocalDate.parse(dobInput));

                        System.out.print("Enter new Email (or press Enter to keep unchanged): ");
                        String email = sc.nextLine();
                        if (!email.isEmpty()) student.setEmail(email);

                        System.out.print("Enter new Phone Number (or press Enter to keep unchanged): ");
                        String phone = sc.nextLine();
                        if (!phone.isEmpty()) student.setPhoneNumber(phone);

                        dao.updateStudent(student);
                        System.out.println(" Student updated successfully.");
                        break;
                    }
                    case 4: {
                     
                        List<Student> students = dao.getAllStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student student : students) {
                                System.out.println("Student ID: " + student.getStudentId());
                                System.out.println("Name: " + student.getFirstName());
                                System.out.println("Email: " + student.getEmail());
                                System.out.println("Phone: " + student.getPhoneNumber());
                                System.out.println("-----");
                            }
                        }
                        break;
                    }
                    case 5: {
                      
                        System.out.print("Enter Course ID: ");
                        int courseId = sc.nextInt();
                        System.out.print("Enter Course Name: ");
                        String courseName = sc.nextLine();
                        System.out.print("Enter Course Code: ");
                        String courseCode = sc.nextLine();
                        System.out.print("Enter Instructor Name ): ");
                        String instructorName = sc.nextLine();
                        sc.nextLine(); 

                        Course course = new Course(courseId, courseName, courseCode, instructorName);
                        dao.addCourse(course);
                        System.out.println("Course added successfully.");
                        break;
                    }
                    case 6: {
                     
                    	System.out.print("Enter Course Code: ");
                        String courseCode = sc.nextLine();

                        try {
                            Course course = dao.getCourseByCode(courseCode);
                            System.out.println("Course Id: " + course.getCourseId());
                            System.out.println("Course Name: " + course.getCourseName());
                            System.out.println("Course Code: " + course.getCourseCode());
                            System.out.println("Instructor Name: " + course.getInstructorName());
                        
                        } catch (CourseNotFoundException e) {
                            System.out.println("Course not found with Code: " + courseCode + ". " + e.getMessage());
                        }
                        break;
                    }
                    case 7: {
                       
                        System.out.print("Enter Course Code to update: ");
                        String courseCode = sc.nextLine();

                        Course course = dao.getCourseByCode(courseCode);
                        if (course != null) {
                            System.out.println("Enter new Course Name (or press Enter to keep the current): ");
                            String name = sc.nextLine();
                            if (!name.isEmpty()) course.setCourseName(name);

                            System.out.println("Enter instructor name (or press Enter to keep the current): ");
                            String instructorName = sc.nextLine();
                            if (!instructorName.isEmpty()) course.setInstructorName(instructorName);

                            System.out.println("Enter new CourseId (or press Enter to keep the current): ");
                            int CourseId = sc.nextInt();
                            sc.nextLine(); 
                            if (CourseId > 0) course.setCourseId(CourseId);

                            dao.updateCourse(course);
                            System.out.println("Course updated successfully.");
                        } else {
                            System.out.println("Course not found.");
                        }
                        break;
                    }
                    case 8: {
                     
                        List<Course> courses = dao.getAllCourses();
                        if (courses.isEmpty()) {
                            System.out.println("No courses found.");
                        } else {
                            for (Course course : courses) {
                                System.out.println("Course Id: " + course.getCourseId());
                                System.out.println("Course Name: " + course.getCourseName());
                                System.out.println("Course Code: " + course.getCourseCode());
                                System.out.println("instructorName: " + course.getInstructorName());
                                System.out.println("-----");
                            }
                        }
                        break;
                    }
                    case 9: {
                     
                        System.out.print("Enter Teacher ID: ");
                        int teacherId = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Expertise: ");
                        String expertise = sc.nextLine();

                        Teacher teacher = new Teacher(teacherId, firstName, lastName, email, expertise);
                        dao.addTeacher(teacher);
                        System.out.println("Teacher added successfully.");
                        break;
                    }

                    case 10: {
                      
                        System.out.print("Enter Teacher ID: ");
                        int teacherId = sc.nextInt();
                        sc.nextLine(); 

                        try {
                            Teacher teacher = dao.getTeacherById(teacherId);
                            System.out.println("Teacher ID: " + teacher.getTeacherId());
                            System.out.println("Name: " + teacher.getFirstName() + " " + teacher.getLastName());
                            System.out.println("Email: " + teacher.getEmail());
                            System.out.println("Expertise: " + teacher.getExpertise());
                        } catch (TeacherNotFoundException e) {
                            System.out.println("Teacher not found with ID: " + teacherId + ". " + e.getMessage());
                        }
                        break;
                    }

                    case 11: {
                       
                        System.out.print("Enter Teacher ID to update: ");
                        int teacherId = sc.nextInt();
                        sc.nextLine(); 

                            Teacher teacher = dao.getTeacherById(teacherId);

                            System.out.print("Enter new first name (press Enter to keep current): ");
                            String fname = sc.nextLine();
                            if (!fname.isEmpty()) teacher.setFirstName(fname);

                            System.out.print("Enter new last name (press Enter to keep current): ");
                            String lname = sc.nextLine();
                            if (!lname.isEmpty()) teacher.setLastName(lname);

                            System.out.print("Enter new email (press Enter to keep current): ");
                            String email = sc.nextLine();
                            if (!email.isEmpty()) teacher.setEmail(email);

                            System.out.print("Enter new expertise (press Enter to keep current): ");
                            String expertise = sc.nextLine();
                            if (!expertise.isEmpty()) teacher.setExpertise(expertise);

                            dao.updateTeacher(teacher);
                            System.out.println("Teacher updated successfully.");
                       
                        }
                        break;
                    

                    case 12: {
                      
                        List<Teacher> teachers = dao.getAllTeachers();
                        if (teachers.isEmpty()) {
                            System.out.println("No teachers found.");
                        } else {
                            for (Teacher teacher : teachers) {
                                System.out.println("Teacher ID: " + teacher.getTeacherId());
                                System.out.println("Name: " + teacher.getFirstName() + " " + teacher.getLastName());
                                System.out.println("Email: " + teacher.getEmail());
                                System.out.println("Expertise: " + teacher.getExpertise());
                                System.out.println("-----");
                            }
                        }
                        break;
                    }

                    
                    case 13: {
                    
                        System.out.print("Enter Enrollment ID: ");
                        int enrollmentId = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter Student ID: ");
                        int studentId = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter Course ID: ");
                        int courseId = sc.nextInt(); sc.nextLine();

                        LocalDate enrollmentDate = LocalDate.now(); 
                        Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId, enrollmentDate);
                        dao.enrollStudentInCourse(enrollment);
                        System.out.println("Student enrolled in course successfully.");
                        break;
                    }

                    case 14: {
                     
                        System.out.print("Enter Student ID: ");
                        int studentId = sc.nextInt(); sc.nextLine();

                        try {
                            List<Enrollment> enrollments = dao.getEnrollmentsByStudentId(studentId); // No need to check for emptiness here
                            for (Enrollment enrollment : enrollments) {
                                System.out.println("Enrollment ID: " + enrollment.getEnrollmentId());
                                System.out.println("Course ID: " + enrollment.getCourseId());
                                System.out.println("Enrollment Date: " + enrollment.getEnrollmentDate());
                                System.out.println("-----");
                            }
                        } catch (EnrollmentException e) {
                            System.out.println(e.getMessage()); 
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;
                    }


                    case 15: {
                       
                        System.out.print("Enter Course ID: ");
                        String courseName = sc.nextLine(); sc.nextLine();

                        List<Enrollment> enrollments = dao.getEnrollmentsByCourseName(courseName);
                        if (enrollments.isEmpty()) {
                            System.out.println("No enrollments found for this course.");
                        } else {
                            for (Enrollment enrollment : enrollments) {
                                System.out.println("Enrollment ID: " + enrollment.getEnrollmentId());
                                System.out.println("Student ID: " + enrollment.getStudentId());
                                System.out.println("Enrollment Date: " + enrollment.getEnrollmentDate());
                                System.out.println("-----");
                            }
                        }
                        break;
                    }

                    case 16: {
                   
                    	System.out.print("Enter payment ID for payment: ");
                        int paymentId = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Enter Student ID for payment: ");
                        int studentId = sc.nextInt();
                        sc.nextLine(); 

                        System.out.print("Enter Amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine(); 
                        
                        LocalDate paymentDate = LocalDate.now();

                        Payment payment = new Payment(paymentId, studentId, amount, paymentDate);

                        dao.addPayment(payment);
                        System.out.println("Payment added successfully.");
                        break;
                    }


                    case 17: {
                   
                        System.out.print("Enter Student ID: ");
                        int studentId = sc.nextInt();
                        sc.nextLine(); 

                        try {
                            List<Payment> payments = dao.getPaymentsByStudentId(studentId);

           
                            for (Payment payment : payments) {
                                System.out.println("Payment ID: " + payment.getPaymentId());
                                System.out.println("Student ID: " + payment.getStudentId());
                                System.out.println("Amount: " + payment.getAmount());
                                System.out.println("Payment Date: " + payment.getPaymentDate());
                                System.out.println("-----");
                            }
                        } catch (PaymentException e) {
                            System.out.println("Error: " + e.getMessage()); 
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;
                    }


                    case 18: {
                      
                        System.out.print("Enter Course ID: ");
                        int courseId = sc.nextInt();
                        sc.nextLine(); 
                        System.out.print("Enter Teacher ID: ");
                        int teacherId = sc.nextInt();

                        dao.assignTeacherToCourse(courseId, teacherId);
                        System.out.println("Teacher assigned to course successfully.");
                        break;
                    }
                    case 19: {
                      
                        System.out.print("Enter Course ID: ");
                        int courseId = sc.nextInt();
                        sc.nextLine(); 

                        List<Enrollment> enrollments = dao.generateEnrollmentReport(courseId);

                        if (enrollments.isEmpty()) {
                            System.out.println("No enrollments found for Course ID: " + courseId);
                        } else {
                            for (Enrollment enrollment : enrollments) {
                                System.out.println("Enrollment ID: " + enrollment.getEnrollmentId());
                                System.out.println("Student ID: " + enrollment.getStudentId());
                                System.out.println("Course ID: " + enrollment.getCourseId());
                                System.out.println("Enrollment Date: " + enrollment.getEnrollmentDate());
                                System.out.println("-----");
                            }
                        }
                        break;
                    }

                    case 20: {
                        
                        System.out.print("Enter Student ID: ");
                        int studentId = sc.nextInt();
                        sc.nextLine(); 

                        List<Payment> payments = dao.generatePaymentReport(studentId);

                        if (payments.isEmpty()) {
                            System.out.println("No payments found for Student ID: " + studentId);
                        } else {
                            for (Payment payment : payments) {
                                System.out.println("Payment ID: " + payment.getPaymentId());
                                System.out.println("Student ID: " + payment.getStudentId());
                                System.out.println("Amount: " + payment.getAmount());
                                System.out.println("Payment Date: " + payment.getPaymentDate());
                                System.out.println("-----");
                            }
                        }
                        break;
                    }

                    case 21: {
                       
                        System.out.print("Enter Course Code: ");
                        String courseCode = sc.nextLine();

                        double total = dao.calculateTotalPaymentsForCourse(courseCode);
                        System.out.println("Total Payments for Course " + courseCode + ": " + total);
                        break;
                    }
                    case 0:
                        System.out.println("Exiting system...");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
	

}