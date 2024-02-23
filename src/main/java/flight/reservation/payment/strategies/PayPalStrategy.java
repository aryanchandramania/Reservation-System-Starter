package flight.reservation.payment.strategies;

public class PayPalStrategy implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using PayPal");
    }
}
