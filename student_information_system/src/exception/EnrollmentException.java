package exception;

public class EnrollmentException extends Exception {
    public EnrollmentException(String message) {
        super("Enrollment error: " + message);
    }
}