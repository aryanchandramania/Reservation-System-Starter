package flight.reservation.payment;

import java.util.Date;

public class TopUpAdapter implements PaypalInterface {
    CreditCard creditCard;

    public TopUpAdapter(String number, Date date, String cvv) {
        creditCard = new CreditCard(number, date, cvv);
    }

    public void topUpWallet(Paypal paypal, double topUp) {
        double new_cc_amount = creditCard.getAmount() - topUp;
        creditCard.setAmount(new_cc_amount);
        double new_pp_amount = paypal.getAmount() + topUp;
        paypal.setAmount(new_pp_amount);
    }
}
