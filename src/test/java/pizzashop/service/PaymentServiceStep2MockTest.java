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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PaymentServiceStep2MockTest {

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
    }


    @Test
    void addPayment() {
        // Cream un mock Payment
        Payment mockPayment = mock(Payment.class);

        // Simulăm adăugarea în lista internă
        doAnswer(invocation -> {
            payments.add(mockPayment); // adăugăm mock-ul manual
            return null;
        }).when(paymentRepository).add(any(Payment.class));

        // Apelăm metoda reală
        paymentService.addPayment(3, PaymentType.Cash, 22);

        // Verificăm că s-a apelat metoda add exact o dată
        verify(paymentRepository, times(1)).add(any(Payment.class));

        // Verificăm că lista conține exact mock-ul nostru
        assertEquals(1, payments.size());
        assertSame(mockPayment, payments.get(0)); // fără captor, doar referință directă
    }

    @Test
    void getPayments() {
        List<Payment> result = paymentService.getPayments();
        assertEquals(0, result.size());
        verify(paymentRepository, times(1)).getAll();
    }
}