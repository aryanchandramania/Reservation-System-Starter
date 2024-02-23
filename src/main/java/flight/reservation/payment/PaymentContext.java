package flight.reservation.payment;

import flight.reservation.payment.strategies.PaymentStrategy;

import java.util.Date;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean execute(String number, String cvv, Date expiry, double amount, String email, String password) {
        return strategy.pay(number, cvv, expiry, amount, email, password);
    }
}
