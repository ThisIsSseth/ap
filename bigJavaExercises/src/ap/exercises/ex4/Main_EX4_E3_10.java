package ap.exercises.ex4;

import java.util.ArrayList;
import java.util.List;

public class Main_EX4_E3_10 {
    public static void main(String[] args) {

        CashRegister register = new CashRegister();

        register.recordPurchase(29.50);
        register.recordPurchase(9.25);
        register.receivePayment(50);

        double change = register.giveChange();

        System.out.println(change);
        System.out.println("Expected: 11.25");
        register.printRecipt();
    }

}

class CashRegister {
    private List<String> priceList = new ArrayList<String>();
    private double purchase = 0.0, payment = 0.0;


    /**
     * Constructs a cash register with no money in it.
     */
    public CashRegister() {
    }

    /**
     * Records the sale of an item.
     *
     * @param amount the price of the item
     */
    public void recordPurchase(double amount) {
        priceList.add(String.valueOf(amount));
        purchase += amount;
    }

    /**
     * Processes a payment received from the customer.
     *
     * @param amount the amount of the payment
     */
    public void receivePayment(double amount) {
        payment += amount;

    }

    /**
     * Computes the change due and resets the machine for the next customer.
     *
     * @return the change due to the customer
     */
    public double giveChange() {
        double change = payment - purchase;
        payment = 0;
        purchase = 0;
        return change;
    }

    /**
     *
     */
    public void printRecipt() {
        String recipt = "";
        for (String s : priceList) {
            recipt = recipt.concat(s + "\n");
        }
        System.out.println(recipt);
    }
}
