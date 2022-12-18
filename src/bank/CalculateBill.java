package bank;

/**
 * Interface to calculate the total amount, which is used in class Transfer and Payment
 * @author Baran Özbey
 * @see Bank.Transfer
 * @see Bank.Payment
 */
public interface CalculateBill {

    /**
     * Calculate the total amount Transaction
     * @return total amount of Transaction(with fees,...)
     */
    public double calculate();
}
