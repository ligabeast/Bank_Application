package bank;

import java.io.IOException;

public class Singleton {

    public PrivateBank Bank;
    public String actualAccount;

    public Transaction actualTransaction;



    private static final Singleton instance = new Singleton();
    private Singleton(){
        this.Bank = new PrivateBank("Bank", 0, 0, "C:\\Users\\Baran\\Java Projekte\\Bank_Application\\SerializedObjects");
    }

    public static Singleton getInstance(){
        return instance;
    }

    public void deserialize() throws IOException {
        Bank.readAccounts();
    }






}
