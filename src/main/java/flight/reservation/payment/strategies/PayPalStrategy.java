package flight.reservation.payment.strategies;

import java.util.Date;

public class PayPalStrategy implements PaymentStrategy {

    @Override
    public boolean pay(String number, String cvv, Date expiry, double amount, String email, String password) {
        System.out.println("Paying " + amount + " using PayPal");

        return true;
    }
}