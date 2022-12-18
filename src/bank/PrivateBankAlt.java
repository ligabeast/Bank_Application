package bank;

import java.util.*;

import bank.exceptions.*;

/**
 * Verwaltet und verarbeitet Konten und Transaktionen
 */
public class PrivateBankAlt implements Bank{
    /**
     * Dieses Attribut soll den Namen der privaten Bank repräsentieren
     */
    private String name;
    /**
     *  Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen
     */
    private double incomingInterest;
    /**
     * Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen
     */
    private double outgoingInterest;

    /**
     * Maped die Transaktionen zu den jeweiligen Konten
     */
    Map<String, List<Transaction>> konten = new HashMap<String, List<Transaction>>();
    /**
     * Konstruktor
     * @param name Dieses Attribut soll den Namen der privaten Bank repräsentieren
     * @param incomingInterest Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen
     * @param outgoingInterest Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen
     */
    public PrivateBankAlt(String name, double incomingInterest,double outgoingInterest)
    {
        this.name = name;
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;

    }
    /**
     * Copy Konstruktor
     * @param obj mit zustand
     */
    public PrivateBankAlt(PrivateBankAlt obj){
        this(obj.name, obj.incomingInterest, obj.outgoingInterest);
    }
    /**
     * Überschreibung der toString Method um ein Objekt mit den initialisierten Werten auszugeben
     * @returns name, incomingInterest und outgoingInterest
     */
    @Override
    public String toString() {
        return "\nName: " + this.name +
                "\nIncomingInterest: " + this.incomingInterest +
                "\nOutgoingInterest: " + this.outgoingInterest ;
    }
    /**
     * Vergleicht Objekte und die Zustände der Objekte miteinander
     * @param obj der Klasse Transaction
     * @returns true wenn Objekte gleich sind und false wenn sie nicht gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        boolean test = false;
        if(obj instanceof PrivateBankAlt){
            PrivateBankAlt p = (PrivateBankAlt) obj;
            if( this.name.equals(p.name) && konten.equals(p.konten) && this.getIncomingInterest() == p.incomingInterest && this.getOutgoingInterest() == p.outgoingInterest){
                return test;
            }
        }
        return false;
    }

    /**
     * Setter um den Namen der Privaten Bank zu initialisieren
     * @param name dieses Attribut soll den Namen der privaten Bank repräsentieren
     */
    public void setName(String name) {this.name = name;}

    /**
     * Setter um die Zinsen zu berechnen und zu initialisieren für Einzahlung
     * @param incomingInterest  Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen.
     */
    public void setIncomingInterest(double incomingInterest) {
        if(incomingInterest >= 0 && incomingInterest <= 1 ){this.incomingInterest = incomingInterest;}
        else{System.out.println("Fehlerhafte Eingabe (Deposit)! Es werden nur positive Werte zwischen 0 und 1 akzeptiert.");}
    }

    /**
     * Setter um die Zinsen zu berechnen und zu initialisieren für Auszahlung
     * @param outgoingInterest Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen
     */
    public void setOutgoingInterest(double outgoingInterest) {
        if(outgoingInterest >= 0 && outgoingInterest <= 1 ){this.outgoingInterest = outgoingInterest;}
        else{System.out.println("Fehlerhafte Eingabe (Withdrawal)! Es werden nur positive Werte zwischen 0 und 1 akzeptiert.");}
    }

    /**
     * Getter
     * @returns namen von der privaten Bank
     */
    public String getName() {return name;}

    /**
     * Getter
     * @returns gibt die Zinsen für Einzahlung zurück
     */
    public double getIncomingInterest() {return incomingInterest;}

    /**
     * Getter
     * @returns gibt die Zinsen für Auszahlung zurück
     */
    public double getOutgoingInterest() {return outgoingInterest;}

    /**
     * Adds an account to the bank.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    @Override
    public void createAccount(String account) throws AccountAlreadyExistsException {
        if(konten.containsKey(account)){
            throw new AccountAlreadyExistsException();
        }
        konten.put(account,null);

    }

    // hier fragen wegen transactionattribute exception
    /**
     * Adds an account (with specified transactions) to the bank.
     * Important: duplicate transactions must not be added to the account!
     *
     * @param account      the account to be added
     * @param transactions a list of already existing transactions which should be added to the newly created account
     * @throws AccountAlreadyExistsException    if the account already exists
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws TransactionAttributeException    if the validation check for certain attributes fail
     */
    @Override
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException, TransactionAlreadyExistException, TransactionAttributeException {
        if(konten.containsKey(account)){
            throw new AccountAlreadyExistsException();
        }
        if(konten.get(account).contains(transactions)){
            throw new TransactionAlreadyExistException();
        }

        konten.put(account,transactions);

    }
    /**
     * Adds a transaction to an already existing account.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which should be added to the specified account
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws AccountDoesNotExistException     if the specified account does not exist
     * @throws TransactionAttributeException    if the validation check for certain attributes fail
     */

    @Override
    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException {
        if(!konten.containsKey(account)){
            throw new AccountDoesNotExistException();
        }
        if(konten.get(account).contains(transaction)){
            throw new TransactionAlreadyExistException();
        }
        konten.get(account).add(transaction);

    }
    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is removed from the specified account
     * @throws AccountDoesNotExistException     if the specified account does not exist
     * @throws TransactionDoesNotExistException if the transaction cannot be found
     */
    @Override
    public void removeTransaction(String account, Transaction transaction) throws AccountDoesNotExistException, TransactionDoesNotExistException {
        if(!konten.containsKey(account)){
            throw new AccountDoesNotExistException();
        }
        if(!konten.containsValue(transaction)){
            throw new TransactionDoesNotExistException();
        }
        konten.get(account).remove(transaction);
    }
    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction to search/look for
     */
    @Override
    public boolean containsTransaction(String account, Transaction transaction) {
        return konten.get(account).contains(transaction);
    }
    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    @Override
    public double getAccountBalance(String account) {
        double sum = 0;

        for(Transaction t : konten.get(account)){
            if(t instanceof Transfer){
                if(((Transfer) t).getSender().equals(account)){
                    sum -= t.calculate();
                }
                else{
                    sum += t.getAmount();
                }
            }
            else if(t instanceof Payment){
                sum += ((Payment) t).calculate();
            }
        }

        return sum;
    }

    /**
     * Returns a list of transactions for an account.
     *
     * @param account the selected account
     * @return the list of all transactions for the specified account
     */
    @Override
    public List<Transaction> getTransactions(String account) {
        return konten.get(account);
    }
    /**
     * Returns a sorted list (-> calculated amounts) of transactions for a specific account. Sorts the list either in ascending or descending order
     * (or empty).
     *
     * @param account the selected account
     * @param asc     selects if the transaction list is sorted in ascending or descending order
     * @return the sorted list of all transactions for the specified account
     */
    @Override
    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
        Collections.sort(konten.get(account), new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return (o2.getAmount() < o1.getAmount()) ? 0 : (o2.getAmount() == o1.getAmount()) ? 0 : 1;
            }
        });
        if(!asc){{
            Collections.reverse(konten.get(account));
        }}
        return konten.get(account);
    }

    /**
     * Returns a list of either positive or negative transactions (-> calculated amounts).
     *
     * @param account  the selected account
     * @param positive selects if positive or negative transactions are listed
     * @return the list of all transactions by type
     */
    @Override
    public List<Transaction> getTransactionsByType(String account, boolean positive) {
        List<Transaction> ls = Arrays.asList();

        for(Transaction t : konten.get(account)){
            if(positive){
                if(t instanceof Transfer && ((Transfer) t).getRecipient() == account){
                    ls.add(t);
                }
                else if (t instanceof Payment && t.amount > 0){
                    ls.add(t);
                }
            }
            else {
                if (t instanceof Transfer && ((Transfer) t).getSender() == account) {
                    ls.add(t);
                }
                else if (t instanceof Payment && t.amount < 0){
                    ls.add(t);
                }
            }
        }
        return ls;
    }
}
