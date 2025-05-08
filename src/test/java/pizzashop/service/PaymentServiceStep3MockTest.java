package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceStep3MockTest {
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    MenuRepository menuRepository;
    @Mock
    PaymentValidator paymentValidator;

    @InjectMocks
    PaymentService paymentService;

    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        when(paymentRepository.getAll()).thenReturn(payments);
    }

    @Test
    void getPayments() {
        List<Payment> result = paymentService.getPayments();
        assertEquals(0, result.size());
        verify(paymentRepository, times(1)).getAll();
    }

    @Test
    void addPayment() {
        Payment payment = new Payment(3, PaymentType.Cash, 22);
        doAnswer((invocation) -> {
            payments.add(payment);
            return null;
        }).when(paymentRepository).add(any(Payment.class));

        paymentService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        verify(paymentRepository, times(1)).add(any(Payment.class));
        List<Payment> result = paymentService.getPayments();
        assertEquals(1, result.size());
        assertEquals(payment, result.get(0));
    }
}