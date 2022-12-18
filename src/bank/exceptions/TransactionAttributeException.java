package bank.exceptions;

public class TransactionAttributeException extends Exception{

    @Override
    public String getMessage() {
        return ("TransactionAttribute");
    }
}
