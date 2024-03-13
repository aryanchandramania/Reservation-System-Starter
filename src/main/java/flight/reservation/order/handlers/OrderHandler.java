package flight.reservation.order.handlers;

public interface OrderHandler {
    void setNextHandler(OrderHandler nextHandler);
    boolean processOrder(FlightOrder order, PaymentDetails paymentDetails);
}