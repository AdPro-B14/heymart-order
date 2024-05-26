package id.ac.ui.cs.advprog.heymartorder.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Transaction cannot be processed due to insufficient balance.");
    }
}
