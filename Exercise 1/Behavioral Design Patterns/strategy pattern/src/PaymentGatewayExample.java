package paymentgateway;

public class PaymentGatewayExample {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Set strategy to Credit Card payment
        context.setPaymentStrategy(new CreditCardPayment("1223 4577 4568 4567"));
        context.executePayment(20000);

        // Change strategy to gpay payment
        context.setPaymentStrategy(new gpay("dharsoff@gmail.com"));
        context.executePayment(200);
    }
}
