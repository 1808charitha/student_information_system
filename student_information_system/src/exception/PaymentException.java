package exception;

public class PaymentException extends Exception {
    public PaymentException(String message) {
        super("Payment error: " + message);
    }
}