package bank;

public class IncomingTransfer extends Transfer{

    public IncomingTransfer(String date, double amount, String description) {
        super(date, amount, description);
    }

    public IncomingTransfer(String date, double amount, String description, String sender, String recipient) {
        super(date, amount, description, sender, recipient);
    }

    //????
    @Override
    public double calculate() {
        return super.calculate();
    }
}
