package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @AfterEach
    void tearDown() {
        paymentRepository.getAll().clear();
        paymentRepository.writeAll();
    }

    @Test
    void size() {
        assertEquals(0, paymentRepository.size());
    }

    @Test
    void add() {
        Payment payment = getPayement(1, PaymentType.Card, 50.00);
        paymentRepository.add(payment);
        assertEquals(1, paymentRepository.size());
    }

    private Payment getPayement(int tableNumber, PaymentType type, double amount) {
        Payment payment = mock(Payment.class);
        when(payment.getTableNumber()).thenReturn(tableNumber);
        when(payment.getType()).thenReturn(type);
        when(payment.getAmount()).thenReturn(amount);
        return payment;
    }
}