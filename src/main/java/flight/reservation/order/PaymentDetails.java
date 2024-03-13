package flight.reservation.order;

import java.util.Date;

public class PaymentDetails {
    public String method;
    public String number;
    public String cvv;
    public Date expiry;
    public String email;
    public String password;

    public PaymentDetails(String method, String number, String cvv, Date expiry, String email, String password) {
        this.method = method;
        this.number = number;
        this.cvv = cvv;
        this.expiry = expiry;
        this.email = email;
        this.password = password;
    }
}
