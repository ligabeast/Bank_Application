package bank;

import bank.exceptions.BetweenOneZero;

/**
 * Class Payment which inherits from the abstract class Transaction and implements Interface CalculateBill
 * @author Baran Özbey
 * @see bank.CalculateBill
 * @see bank.Transaction
 */
public class Payment extends Transaction{

    /**
     * Interest charges of deposit
     */
    private double incomingInterest = 0.0;

    /**
     * Interest charges of withdraw
     */
    private double outgoingInterest = 0.0;

    /**
     * Constructor with 3 Parameter
     * @param date Date in form DD:MM:YYYY
     * @param amount Amount of payment
     * @param description Small description of payment
     */
    public Payment(String date, double amount, String description){ //Constructor with 3 parameter
        super(date,amount,description);
    }

    /**
     * Constructor with all attributes as Parameter
     * @param date Date in form DD:MM:YYYY
     * @param amount Amount of payment
     * @param description Small description of payment
     * @param incomingInterest Interest charges of deposit
     * @param outgoingInterest Interest charges of withdraw
     */
    public Payment(String date, double amount, String description, double incomingInterest, double outgoingInterest){ //Constructor with all attributes as parameter
        this(date, amount, description);
        try {
            this.setIncomingInterest(incomingInterest);
            this.setOutgoingInterest(outgoingInterest);
        }
        catch(BetweenOneZero e){
            e.printStackTrace();
        }
    }

    /**
     * Copy Constructor
     * @param copy Object of same type
     */
    public Payment(Payment copy){   //Copy constructor
        this(copy.date, copy.amount, copy.description, copy.incomingInterest, copy.outgoingInterest);
    }

    /**
     * Getter of IncomingInterest
     * @return attribute IncomingInterest
     */
    public double getIncomingInterest() {   //Getter for incomingInterest
        return incomingInterest;
    }

    /**
     * Getter of OutgoingInterest
     * @return attribute OutgoingInterest
     */
    public double getOutgoingInterest() {   //Getter for outgoingInterest
        return outgoingInterest;
    }

    /**
     * Setter of IncomingInterest. It has to be between 0-1, the value is a percentage
     * @param incomingInterest IncomingInterest which is being set
     */
    public void setIncomingInterest(double incomingInterest) throws BetweenOneZero{  //Setter for incomingInterest
        if(incomingInterest >= 0 && incomingInterest <= 1) {    //Interest has to be between 0-1 , 1 = 100%
            this.incomingInterest = incomingInterest;
        }
        else{
            throw new BetweenOneZero();
        }
    }

    /**
     * Setter of OutgoingInterest. It has to be between 0-1, the value is a percentage
     * @param outgoingInterest
     */
    public void setOutgoingInterest(double outgoingInterest) throws BetweenOneZero {  //Setter for outgoingInterest
        if (outgoingInterest >= 0 && outgoingInterest <= 1) {   //Interest has to be between 0-1, 1 = 100%
            this.outgoingInterest = outgoingInterest;
        }
        else{
            throw new BetweenOneZero();
        }
    }

    /**
     * Calculates the total amount of Payments( with fees, ...). This method overrides the old calculate methode in Interface CalculateBill
     * @return total amount of Payment
     */

    public double calculate(){
        return (amount > 0) ? (amount - (incomingInterest * amount)) : (amount + (outgoingInterest * amount));
    }

    /**
     * Override the toString() in Base Class Object
     * @return a String wich represents the Object
     */
    @Override
    public String toString(){
        return (super.toString() + " IncomingInterest: " + this.incomingInterest + " OutgointInterest: " + this.outgoingInterest);
    }

    /**
     * Checks if two Objects have the same attribute values
     * @param o Object from same Class
     * @return returns true if Objects have the same attributes, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.incomingInterest, incomingInterest) == 0 && Double.compare(payment.outgoingInterest, outgoingInterest) == 0;
    }
}
