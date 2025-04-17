package exception;

public class TeacherNotFoundException extends Exception {
	public TeacherNotFoundException(String message) {
        super("TeacherNotFound Error: "+message);
    }

}