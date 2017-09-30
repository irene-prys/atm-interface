package demo.atm.exceptions;

public class MoneyOperationException extends Exception{
    public MoneyOperationException() {
        super();
    }

    public MoneyOperationException(String message) {
        super(message);
    }
}
