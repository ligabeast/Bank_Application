package bank.exceptions;

public class AccountDoesNotExistException extends Exception{

    @Override
    public String getMessage() {
        return ("AccountDoesNotExist");
    }
}
