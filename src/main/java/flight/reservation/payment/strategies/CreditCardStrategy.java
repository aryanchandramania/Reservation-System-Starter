package flight.reservation.payment.strategies;

import flight.reservation.payment.CreditCard;

import java.util.Date;

public class CreditCardStrategy implements PaymentStrategy {
    
    public boolean pay(String number, String cvv, Date expiry, double amount, String email, String password) {
        CreditCard creditCard = new CreditCard(number, expiry, cvv);

        return processOrderWithCreditCard(creditCard, amount);
    }

    private boolean processOrderWithCreditCard(CreditCard creditCard, double amount) throws IllegalStateException {
        // validate payment information
        if (!cardIsPresentAndValid(creditCard)) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = payWithCreditCard(creditCard, amount);
        return isPaid;
    }

    private boolean cardIsPresentAndValid(CreditCard card) {
        return card != null && card.isValid();
    }

    private boolean payWithCreditCard(CreditCard card, double amount) throws IllegalStateException {
        if (cardIsPresentAndValid(card)) {
            System.out.println("Paying " + amount + " using Credit Card.");
            double remainingAmount = card.getAmount() - amount;
            if (remainingAmount < 0) {
                System.out.printf("Card limit reached - Balance: %f%n", remainingAmount);
                throw new IllegalStateException("Card limit reached");
            }
            card.setAmount(remainingAmount);
            return true;
        } else {
            return false;
        }
    }
}
