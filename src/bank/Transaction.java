package bank;


import bank.exceptions.IllegalAmount;

/**
 * Abstract Class for Transactions
 * @author Baran Özbey
 */
public abstract class Transaction implements CalculateBill{    //Keine Sichtbarkeit, somit nur im gleichen Package Sichtbar

    /**
     * Date in form DD:MM:YYYY
     */
    public String date = "";

    /**
     * Amount of payment
     */
    protected double amount = 0.0;

    /**
     * Small description of payment
     */
    public String description = "";

    /**
     * Constructor with 3 parameter
     *
     * @param date Date in form DD:MM:YYYY
     * @param amount Amount of Transaction
     * @param description small description of Transaction
     */
    Transaction(String date, double amount, String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Default Constructor
     */
    Transaction(){ }

    /**
     * Override the toString() in Base Class Object
     * @return a String wich represents the Objects
     */
    @Override
    public String toString(){
        return("Date: " + this.date + ", Amount: " + this.amount + ", Description: " + this.description);
    }

    /**
     * Checks if two Objects have the same attribute values
     * @param o Object from same Class
     * @return returns true if Objects have the same attributes, else false
     */
    @Override
    public boolean equals(Object o){
        Transaction tmp = (Transaction)o;
        return (this.date.equals(tmp.date) && this.amount == tmp.amount && this.description.equals(tmp.description));
    }

    /**
     * Getter for date
     * @return attribute date
     */
    public String getDate() {   //Getter for date
        return date;
    }
    /**
     * Getter for amount
     * @return attribute amount
     */
    public double getAmount() { //Getter for amount
        return amount;
    }
    /**
     * Getter for description
     * @return attribute description
     */
    public String getDescription() {    //Getter for description
        return description;
    }
    /**
     * Setter for Date
     * @param date date in form DD:MM:YYYY
     */
    public void setDate(String date) {  //Setter for date
        this.date = date;
    }
    /**
     * Setter for amount
     * @param amount numerical value of Transaction
     */
    public void setAmount(double amount) throws IllegalAmount {  //Setter for amount
        this.amount = amount;
    }
    /**
     * Setter for description
     * @param description small description of Transaction
     */
    public void setDescription(String description) {    //Setter for description
        this.description = description;
    }


}

