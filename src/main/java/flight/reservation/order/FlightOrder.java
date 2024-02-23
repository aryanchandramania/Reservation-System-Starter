package flight.reservation.order;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.payment.CreditCard;
import flight.reservation.payment.PaymentContext;
import flight.reservation.payment.Paypal;
import flight.reservation.payment.strategies.CreditCardStrategy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlightOrder extends Order {
    private final List<ScheduledFlight> flights;
    static List<String> noFlyList = Arrays.asList("Peter", "Johannes");

    public FlightOrder(List<ScheduledFlight> flights) {
        this.flights = flights;
    }

    public static List<String> getNoFlyList() {
        return noFlyList;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    private boolean isOrderValid(Customer customer, List<String> passengerNames, List<ScheduledFlight> flights) {
        boolean valid = true;
        valid = valid && !noFlyList.contains(customer.getName());
        valid = valid && passengerNames.stream().noneMatch(passenger -> noFlyList.contains(passenger));
        valid = valid && flights.stream().allMatch(scheduledFlight -> {
            try {
                return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        });
        return valid;
    }

    public void processOrder(String method, String number, String cvv, Date expiry, String email, String password) {
        if (isClosed()) {
            // Payment is already proceeded
            return;
        }
        boolean isPaid = false;
        PaymentContext context = new PaymentContext();
        if (method.equals("creditCard")) {
            CreditCardStrategy creditCardStrategy = new CreditCardStrategy();
            context.setPaymentStrategy(creditCardStrategy);
            isPaid = context.execute(number, cvv, expiry, getPrice(), "", "");
        } else if (method.equals("paypal")) {
            CreditCardStrategy creditCardStrategy = new CreditCardStrategy();
            context.setPaymentStrategy(creditCardStrategy);
            isPaid = context.execute("", "", null, getPrice(), email, password);
        }

        if (isPaid) {
            this.setClosed();
        }
    }
}
