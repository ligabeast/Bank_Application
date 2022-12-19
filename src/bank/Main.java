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
    public static void main(String [] args) throws TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException, IOException, AccountAlreadyExistsException {
        IncomingTransfer a = new IncomingTransfer("10.1.1", 500, "Tranfer1");
        IncomingTransfer b = new IncomingTransfer("10.1.1", 500, "Tranfer2");
        IncomingTransfer c = new IncomingTransfer("10.1.1", 500, "Tranfer3");

        PrivateBank bank = new PrivateBank("Bank", 0.1,0.2, "C:\\Users\\Baran\\Java Projekte\\Bank_Application\\SerializedObjects");

        bank.createAccount("Baran");

        bank.addTransaction("Baran", a);
        bank.addTransaction("Baran", b);
        bank.addTransaction("Baran", c);

        bank.writeAccount("Baran");

        bank.deleteAccount("Baran");

        bank.readAccounts();




    }
}
