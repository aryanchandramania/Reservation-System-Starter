package flight.reservation.order.handlers;

import flight.reservation.order.FlightOrder;
import flight.reservation.order.handlers.OrderHandler;
import flight.reservation.payment.PaymentDetails;

public class OrderCompletionHandler implements OrderHandler{
    
    private OrderHandler nextHandler;

    @Override
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean processOrder(FlightOrder order, PaymentDetails paymentDetails) {
        order.setClosed();
        System.out.println("Order fulfilled!");
        return true;
    }
}
