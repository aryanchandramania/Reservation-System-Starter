package flight.reservation.payment.strategies;

public class CreditCardStrategy implements PaymentStrategy{
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Credit Card");
    }
}
