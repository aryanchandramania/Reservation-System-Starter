package flight.reservation.payment.strategies;

import java.util.Date;

public interface PaymentStrategy {
    public boolean pay(String number, String cvv, Date expiry, double amount, String email, String password);
}