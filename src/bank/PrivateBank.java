package bank;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

import bank.exceptions.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import static com.google.gson.JsonParser.parseReader;

/**
 * Verwaltet und verarbeitet Konten und Transaktionen
 */
public class PrivateBank implements Bank{
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
    protected Map<String, List<Transaction>> konten = new HashMap<String, List<Transaction>>();

    /**
     * Konstruktor
     * @param name Dieses Attribut soll den Namen der privaten Bank repräsentieren
     * @param incomingInterest Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen
     * @param outgoingInterest Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen
     */
    public PrivateBank(String name, double incomingInterest,double outgoingInterest)
    {
        this.name = name;
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;

    }

    /**
     * Copy Konstruktor
     * @param obj mit zustand
     */
    public PrivateBank(PrivateBank obj){
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

  /*  @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        boolean test = false;
        if(obj instanceof PrivateBank){
            PrivateBank p = (PrivateBank) obj;
            if( this.name.equals(p.name) && this.konten.equals(p.konten) && this.getIncomingInterest() == p.incomingInterest && this.getOutgoingInterest() == p.outgoingInterest){
                return test;
            }
        }
        return false;
    }*/
    /**
     * Vergleicht Objekte und die Zustände der Objekte miteinander
     * @param o der Klasse Transaction
     * @returns true wenn Objekte gleich sind und false wenn sie nicht gleich sind
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateBank that = (PrivateBank) o;
        return Double.compare(that.incomingInterest, incomingInterest) == 0 && Double.compare(that.outgoingInterest, outgoingInterest) == 0 && Objects.equals(name, that.name) && Objects.equals(konten, that.konten);
    }


    /**
     * Setter
     * @param name Dieses Attribut soll den Namen der privaten Bank repräsentieren
     */
    public void setName(String name) {this.name = name;}

    /**
     * Setter
     * @param incomingInterest Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen
     */
    public void setIncomingInterest(double incomingInterest) throws BetweenOneZero{
        if(incomingInterest >= 0 && incomingInterest <= 1 ){this.incomingInterest = incomingInterest;}
        else{throw new BetweenOneZero();}
    }

    /**
     * Setter
     * @param outgoingInterest  Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen
     */
    public void setOutgoingInterest(double outgoingInterest) throws BetweenOneZero{
        if(outgoingInterest >= 0 && outgoingInterest <= 1 ){this.outgoingInterest = outgoingInterest;}
        else{throw new BetweenOneZero();}
    }

    /**
     * Getter
     * @returns Dieses Attribut soll den Namen der privaten Bank repräsentieren
     */
    public String getName() {return name;}

    /**
     * Getter
     * @returns Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Einzahlung (Deposit) anfallen
     */
    public double getIncomingInterest() {return incomingInterest;}

    /**
     * Getter
     * @returns  Dieses Attribut gibt die Zinsen bzw. den Zinssatz (positiver Wert in Prozent, 0 bis 1) an, die bei einer Auszahlung (Withdrawal) anfallen
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
        konten.put(account,new ArrayList<Transaction>());

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
        } else {
            konten.put(account, new ArrayList());
            for (Transaction t : transactions) {
                if (konten.get(account).contains(t)) {
                    throw new TransactionAlreadyExistException();
                }
                konten.get(account).add(t);
            }
        }

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
        if(containsTransaction(account,transaction)){
            throw new TransactionAlreadyExistException();
        }
        if(transaction instanceof Payment){
            Payment p = (Payment)transaction;
            try {
                p.setIncomingInterest(this.incomingInterest);
            } catch (BetweenOneZero e) {
                e.getMessage();
            }
            try{
                p.setOutgoingInterest(this.outgoingInterest);
            }catch (BetweenOneZero e){
                e.getMessage();
            }
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
        if(!containsTransaction(account,transaction)){
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
        if(konten.get(account) == null)return false;
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
            sum += t.calculate();
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
        return this.konten.get(account);
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
        konten.get(account).sort(Comparator.comparingDouble(Transaction::getAmount));
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
        List<Transaction> ls = konten.get(account);
        if(positive){
            ls.removeIf(p ->
                    p.calculate() < 0
            );
        }else{
            ls.removeIf(p -> p.calculate() >= 0);
        }
        return ls;
    }
    public void writeAccount(String name) throws IOException{

        List<Transaction> allTransactions = this.getTransactions(name);
        File file = new File("D:/Java Projekte/OOS_Praktikum_1/Praktikum1/SerializedObjects/" + name + ".json");
        FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);

        Gson JsonBuilder = new GsonBuilder().
        registerTypeAdapter(Payment.class, new SerializeJson()).
        registerTypeAdapter(OutgoingTransfer.class, new SerializeJson()).
        registerTypeAdapter(IncomingTransfer.class, new SerializeJson()).
        setPrettyPrinting().create();


        writer.write(String.valueOf(JsonBuilder.toJson(allTransactions)));
        writer.close();
    }
    public void readAccounts() throws IOException{

        // liste für klassentypen
        Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
        String directoryName = "D:/Java Projekte/OOS_Praktikum_1/Praktikum1/SerializedObjects";

        // MyDeserialize, da von gespeicherten daten gelesen wird
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(listType,new SerializeJson());

        //Creates a Gson instance based on current configuration
        Gson gson = builder.create();

        // einlesen
        File directory = new File(directoryName);

        //dateien im ordner
        String[] pfadnamen = directory.list();

        List<Transaction> list;

        // iterator geht Array 'pfadnamen' durch
        for(String iterator : pfadnamen) {

            if(iterator.endsWith(".json")) { //wenn json datei

                FileReader myreader = new FileReader(
                        String.format("%s/%s",directoryName,iterator));

                // speichere objekt als array in 'list'
                list = gson.fromJson(parseReader(myreader).getAsJsonArray(), listType);

                myreader.close();
                try {
                    // lese kontoname ohne ".json"
                    this.createAccount(iterator.substring(0, iterator.length() - 5), list);  //list = liste der transaktion
                }
                catch (Exception e) {
                    System.out.println("Fehler bei readAccounts funktion");
                }
            }
        }
    }

}
