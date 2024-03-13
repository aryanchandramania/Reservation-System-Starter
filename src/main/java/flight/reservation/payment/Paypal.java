package flight.reservation.payment;

public class Paypal implements PaypalInterface{

    private double amount;
    private String email;
    private String password;
    private boolean valid;

    public Paypal(String email, String password) {
        this.amount = 100000;
        this.email = email;
        this.password = password;
        this.setValid();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid() {
        // Dummy validation
        this.valid = !email.isEmpty() && password.length() > 8;
    }

    public static boolean payWithPayPal(Paypal paypal, double amount) throws IllegalStateException {
        if (paypal.isValid()) {
            System.out.println("Paying " + amount + " using PayPal.");
            double remainingAmount = paypal.getAmount() - amount;
            if (remainingAmount < 0) {
                System.out.printf("PayPal limit reached - Balance: %f%n", remainingAmount);
                throw new IllegalStateException("PayPal limit reached");
            }
            paypal.setAmount(remainingAmount);
            return true;
        } else {
            return false;
        }
    }

    public void topUpWallet(Paypal paypal, double topUp) {
        double new_amount = paypal.getAmount() + topUp;
        paypal.setAmount(new_amount);
    }
}
