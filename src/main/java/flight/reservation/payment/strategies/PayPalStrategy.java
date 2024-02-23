package flight.reservation.payment.strategies;

import flight.reservation.payment.Paypal;

import java.util.Date;

public class PayPalStrategy implements PaymentStrategy {

    @Override
    public boolean pay(String number, String cvv, Date expiry, double amount, String email, String password) {
        
        return processOrderWithPayPal(email, password, amount);
    }

    public boolean processOrderWithPayPal(String email, String password, double amount) throws IllegalStateException {
        // validate payment information
        if (email == null || password == null || !email.equals(Paypal.DATA_BASE.get(password))) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = payWithPayPal(email, password, amount);
        return isPaid;
    }

    private boolean payWithPayPal(String email, String password, double amount) throws IllegalStateException {
    if (email.equals(Paypal.DATA_BASE.get(password))) {
        System.out.println("Paying " + amount + " using PayPal.");
        return true;
    } else {
        return false;
    }
    }
}