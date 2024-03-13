package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.order.handlers.OrderHandler;
import flight.reservation.order.handlers.OrderValidationHandler;
import flight.reservation.order.handlers.PaymentHandler;
import flight.reservation.order.handlers.OrderCompletionHandler;
import flight.reservation.payment.PaymentDetails;
import flight.reservation.payment.CreditCard;
import flight.reservation.payment.Paypal;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;
    static List<String> noFlyList = Arrays.asList("Peter", "Johannes");

    // constructor for flight order

    public FlightOrder(List<ScheduledFlight> flights, Customer customer, List<String> passengerNames) {
        this.flights = flights;
        this.setCustomer(customer);
        this.setPassengerNames(passengerNames);
    }

    public static List<String> getNoFlyList() {
        return noFlyList;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    public boolean processOrder(String method, String number, String cvv, Date expiry, String email, String password) {
        // make a parameter object
        PaymentDetails paymentDetails = new PaymentDetails(method, number, cvv, expiry, email, password);
        
        OrderHandler orderValidationHandler = new OrderValidationHandler();
        OrderHandler paymentHandler = new PaymentHandler();
        OrderHandler orderCompletionHandler = new OrderCompletionHandler();

        orderValidationHandler.setNextHandler(paymentHandler);
        paymentHandler.setNextHandler(orderCompletionHandler);

        return orderValidationHandler.processOrder(this, paymentDetails);
    }
}
