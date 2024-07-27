package paymentgateway;

public class gpay implements PaymentStrategy {
    private String email;

    public gpay(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using gpay: " + email);
    }
}
