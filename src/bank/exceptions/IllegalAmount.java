package bank.exceptions;

public class IllegalAmount extends Exception{

    @Override
    public String getMessage() {
        return ("IllegalAmount");
    }
}
