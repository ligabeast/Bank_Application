package bank;

public class OutgoingTransfer extends Transfer{

    public OutgoingTransfer(String date, double amount, String description) {
        super(date, amount, description);
    }

    public OutgoingTransfer(String date, double amount, String description, String sender, String recipient) {
        super(date, amount, description, sender, recipient);
    }

    //????
    @Override
    public double calculate() {
        return -super.calculate();
    }
}
