package flight.reservation.order.handlers;

import java.util.List;

import flight.reservation.order.FlightOrder;
import flight.reservation.order.*;

public class OrderValidationHandler implements OrderHandler {

    private OrderHandler nextHandler;
    private Customer customer;
    private List<Passenger> passengers;
    private List<ScheduledFlight> flights;
    private List<String> noFlyList;

    @Override
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean processOrder(FlightOrder order, PaymentDetails paymentDetails) {
        this.customer = order.getCustomer();
        this.passengers = order.getPassengers();
        this.flights = order.getScheduledFlights();
        this.noFlyList = FlightOrder.getNoFlyList();
        
        boolean valid = true;
        valid = valid && !noFlyList.contains(customer.getName());
        valid = valid && passenger.stream().noneMatch(passenger -> noFlyList.contains(passenger));
        valid = valid && flights.stream().allMatch(scheduledFlight -> {
        try {
            return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
        });

        if(valid) {
            return nextHandler.processOrder(order, paymentDetails);
        }
        else {
            return false;
        }
    }
}