package bank;

import bank.exceptions.IllegalAmount;

/**
 * Class Transfer which inherits from the abstract class Transaction and implements Interface CalculateBill
 * @see bank.CalculateBill
 * @see bank.Transaction
 * @author Baran Özbey
 */
public class Transfer extends Transaction{

    /**
     * sender of transfer
     */
    private String sender = "EMPTY";

    /**
     * recipient of transfer
     */
    private String recipient = "EMPTY";

    /**
     * Constructor with 3 parameter
     * @param date Date in form DD:MM:YYYY
     * @param amount Amount of Transaction
     * @param description small description of Transaction
     */
    public Transfer(String date, double amount, String description){
        this.date = date;
        this.description = description;
        try {
            this.setAmount(amount);
        } catch(IllegalAmount e){
            e.printStackTrace();
        }
    }

    /**
     * Constructor with all attributes as parameter
     * @param date Date in form DD:MM:YYYY
     * @param amount Amount of Transaction
     * @param description small description of Transaction
     * @param sender sender of transfer
     * @param recipient recipient of transfer
     */
    public Transfer(String date, double amount, String description, String sender, String recipient){
        this(date, amount, description);
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * Copy constructor
     * @param copy Object of same type
     */
    public Transfer(Transfer copy){
        this(copy.date, copy.amount, copy.description, copy.sender, copy.recipient);
    }

    /**
     * Getter of sender
     * @return attribute sender of Object
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter of recipient
     * @return attribute recipient of Object
     */
    public String getRecipient() {  //Getter of Recipient
        return recipient;
    }

    /**
     * Setter of amount, which is been overrided. Only if Amount is greater than zero the value will be accepted
     * @param amount numerical value of Transaction
     */

    @Override
    public void setAmount(double amount) throws IllegalAmount{
        if(amount > 0) {
            this.amount = amount;
        }
        else{
            throw new IllegalAmount();
        }
    }

    /**
     * Setter of sender
     * @param sender Sender of Transaction
     */
    public void setSender(String sender) {  //Setter of sender
        this.sender = sender;
    }

    /**
     * Setter of recipient
     * @param recipient recipient of Transaction
     */
    public void setRecipient(String recipient) {    //Setter of recipient
        this.recipient = recipient;
    }

    /**
     * Calculates the amount of Transaction
     * @return amount of object
     */
    @Override
    public double calculate(){
        return amount;
    }

    /**
     * Override the toString() in Base Class Object
     * @return a String wich represents the Object
     */
    @Override
    public String toString(){
        return (super.toString() + " Sender: " + this.sender + " Recipient: " + this.recipient);
    }

    /**
     * Checks if two Objects have the same attribute values
     * @param o Object from same Class
     * @return returns true if Objects have the same attributes, else false
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Transfer) {
            Transfer tmp = (Transfer) o;
            return (super.equals(o) && this.sender.equals(tmp.sender) && this.recipient.equals(tmp.recipient));
        }
        return false;
    }
}
