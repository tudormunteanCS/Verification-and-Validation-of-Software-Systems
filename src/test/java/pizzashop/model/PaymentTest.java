package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {


    @Test
    void getTableNumber() {
        Payment payment = new Payment(1, PaymentType.Card, 50.00);
        assertEquals(1, payment.getTableNumber());
    }

    @Test
    void setTableNumber() {
        Payment payment = new Payment(1, PaymentType.Card, 50.00);
        payment.setTableNumber(2);
        assertEquals(2, payment.getTableNumber());
    }

    @Test
    void getType() {
        Payment payment = new Payment(1, PaymentType.Card, 50.00);
        assertEquals(PaymentType.Card, payment.getType());
    }

    @Test
    void setType() {
        Payment payment = new Payment(1, PaymentType.Card, 50.00);
        payment.setType(PaymentType.Cash);
        assertEquals(PaymentType.Cash, payment.getType());
    }

    @Test
    void getAmount() {
        Payment payment = new Payment(1, PaymentType.Card, 50.00);
        assertEquals(50.00, payment.getAmount());
    }

    @Test
    void setAmount() {
        Payment payment = new Payment(1, PaymentType.Card, 50.00);
        payment.setAmount(100.00);
        assertEquals(100.00, payment.getAmount());
    }
}