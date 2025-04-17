package exception;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super("StudentNotFound Error: "+message);
    }
}