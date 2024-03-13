package flight.reservation.order.handlers;

import java.util.Date;

import flight.reservation.order.FlightOrder;
import flight.reservation.order.*;

public class PaymentHandler implements OrderHandler{
    
    private OrderHandler nextHandler;

    @Override
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean processOrder(FlightOrder order, PaymentDetails paymentDetails) {
        
        if(paymentDetails.method == "CreditCard") {
            CreditCard creditCard = new CreditCard(paymentDetails.number, paymentDetails.expirationDate, paymentDetails.cvv);
            valid = processOrderWithCreditCard(creditCard);
        }
        else if(paymentDetails.method == "PayPal") {
            valid = processOrderWithPayPal(paymentDetails.email, paymentDetails.password);
        }
        else {
            throw new IllegalStateException("Payment method is not valid.");
        }
        if(valid) {
            return nextHandler.processOrder(order, paymentDetails);
        }
        else {
            return false;
        }
    }

    public boolean processOrderWithCreditCard(CreditCard creditCard) throws IllegalStateException {
        if (isClosed()) {
            // Payment is already proceeded
            return true;
        }
        // validate payment information
        if (!cardIsPresentAndValid(creditCard)) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = payWithCreditCard(creditCard, this.getPrice());
        return isPaid;
    }

    private boolean cardIsPresentAndValid(CreditCard card) {
        return card != null && card.isValid();
    }

    public boolean processOrderWithPayPal(String email, String password) throws IllegalStateException {
        if (isClosed()) {
            // Payment is already proceeded
            return true;
        }
        // validate payment information
        if (email == null || password == null || !email.equals(Paypal.DATA_BASE.get(password))) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }
        boolean isPaid = payWithPayPal(email, password, this.getPrice());
        return isPaid;
    }

    public boolean payWithCreditCard(CreditCard card, double amount) throws IllegalStateException {
        if (cardIsPresentAndValid(card)) {
            System.out.println("Paying " + getPrice() + " using Credit Card.");
            double remainingAmount = card.getAmount() - getPrice();
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

    public boolean payWithPayPal(String email, String password, double amount) throws IllegalStateException {
        if (email.equals(Paypal.DATA_BASE.get(password))) {
            System.out.println("Paying " + getPrice() + " using PayPal.");
            return true;
        } else {
            return false;
        }
    }
}
