package bank;

import bank.*;
import bank.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * bank.Main Methode
 */
public class Main {
    /**
     * main
     * @param args as
     */
    public static void main(String [] args){
        /**
         * Repräsentiert die Ein und Auszahlungen
         */
       /* Payment pay;

        pay = new Payment("12.10.2022" , 1000, "Einzahlung");
        pay = new Payment(pay.getDate(),pay.getAmount(), pay.getDescription(), 0.05, 0.1);
        pay = new Payment(pay);
       // pay.printObject();
        System.out.println(pay + "\n"  +"Calculate-Bill: " + pay.calculate());

        System.out.println("");
*/
        /**
         * Wird im Kontext von Überweisungen verwendet
         */
       /* Transfer trans;

        trans = new Transfer("12.10.2022" , 1000, "Einzahlung");
        trans = new Transfer(trans.getDate(),trans.getAmount(),trans.getDescription(),"0.05","0.1");
        trans = new Transfer(trans);
        //trans.printObject();
        System.out.println(trans + "\n" +"Calculate-Bill: " + trans.calculate());

        Transfer trans2;
        trans2 = new Transfer("12.10.2022" , 1000, "Einzahlung");
        trans2 = new Transfer(trans.getDate(),trans.getAmount(),trans.getDescription(),"0.05","0.1");
        trans2 = new Transfer(trans);
        // equals Method
        System.out.println("\nVergleicht Objekt: " + trans.equals(trans2));
        trans.setDate("13.10.2013");
        System.out.println("\nVergleicht Objekt: " + trans.equals(trans2));
*/

        /**
         *
         */
        Transfer trans2 = new Transfer("Konto 1 ", 0.11, "Einzdahlung", "Baris", "Baran");
        Transfer trans = new Transfer("Konto 1 ", 0.13, "Einzahlung", "Baris", "Baran");
        Payment pay = new Payment("Konto 1 ", -0.10, "Einzahlung", 0.12, 0.15);


        PrivateBank privateBank = new PrivateBank("Konto 1 ", 0.05, 0.1);
        PrivateBank privateBank1 = new PrivateBank(privateBank);

        /*privateBank.setName("Konto 1");
        privateBank.setIncomingInterest(pay.getIncomingInterest());
        privateBank.setOutgoingInterest(pay.getOutgoingInterest());
*/

        PrivateBankAlt privateBankAlt = new PrivateBankAlt("Konto 1 ", 0.05, 0.1);
        PrivateBankAlt privateBankAlt1 = new PrivateBankAlt(privateBankAlt);

       /* privateBank.setName("Konto 2");
        privateBank.setIncomingInterest(0.06);
        privateBank.setOutgoingInterest(0.2);*/

        /*try {
            privateBank.createAccount("Konto 1 ");
        }catch (AccountAlreadyExistsException e){
            e.getMessage();
        }*/

        try{
            List<Transaction> t = new ArrayList<>();
            t.add(trans);
            t.add(pay);
            privateBank.createAccount("Konto 1 ",t);

            List<Transaction> t2 = new ArrayList<>();
            t2.add(trans);
            t2.add(pay);
            privateBank.createAccount("Konto 1 ", t2);
        }catch (AccountAlreadyExistsException e){
            e.printStackTrace();
        }catch (TransactionAlreadyExistException et){
            et.printStackTrace();
        }catch (TransactionAttributeException ett){
            ett.printStackTrace();
        }


        try{
            // privateBank.addTransaction("Konto 1 ", trans);
            //  privateBank.addTransaction("Konto 1 ", pay);
            privateBank.addTransaction("Konto 1 ", trans2);
        }catch (TransactionAlreadyExistException e){
            e.getMessage();
        }catch (AccountDoesNotExistException et){
            et.getMessage();
        }catch (TransactionAttributeException ett){
            ett.getMessage();
        }

        try{
            privateBank.removeTransaction(privateBank.getName(),trans2);
        }catch (AccountDoesNotExistException e){
            e.getMessage();
        }catch (TransactionDoesNotExistException  et){
            et.getMessage();
        }

        System.out.println("Contains Transaktion: " + privateBank.containsTransaction("Konto 1 ", trans));
        System.out.println("Get Account Balance: " + privateBank.getAccountBalance("Konto 1 "));

        System.out.println("\nGetTransactions : \n"+privateBank.getTransactions("Konto 1 ") + "\n");
        System.out.println("\nGetTransactionsSorted : \n"+privateBank.getTransactionsSorted("Konto 1 ", true) + "\n");


        try {
            privateBank.writeAccount("Konto 1 ");
            privateBank.readAccounts();
        }catch(IOException e){
            e.printStackTrace();
        }

        /**
         * gibt negative und positive werte aus  if true then negative values if false then positiv values
         */
        System.out.println("\nGetTransactionByType : " + privateBank.getTransactionsByType("Konto 1 ", false) + "\n");
        /**
         * Equals um Objekte zu vergleichen
         */
        System.out.println(privateBank.equals(privateBank1));

        System.out.println(privateBank.toString());
        System.out.println(privateBankAlt1.toString());
    }
}
