package bank.exceptions;

public class TransactionDoesNotExistException extends Exception{

    @Override
    public String getMessage() {
        return ("TransactionDoesNotExist");
    }
}
