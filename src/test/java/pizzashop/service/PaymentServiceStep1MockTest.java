package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentServiceStep1MockTest {
    private MenuRepository menuRepo;
    private PaymentRepository paymentRepo;
    private PaymentValidator paymentValidator;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        menuRepo = mock(MenuRepository.class);
        paymentRepo = mock(PaymentRepository.class);
        paymentValidator = mock(PaymentValidator.class);
        paymentService = new PaymentService(menuRepo, paymentRepo, paymentValidator);
    }

    @Test
    void testAddPayment_validInput_addsPayment() {
        // Arrange
        int table = 1;
        PaymentType type = PaymentType.Cash;
        double amount = 100.0;
        paymentService.addPayment(table, type, amount);

        // Assert
        verify(paymentRepo, times(1)).add(any(Payment.class));
    }

    @Test
    void testGetTotalAmount_returnsCorrectSum() {
        // Arrange
        List<Payment> mockPayments = Arrays.asList(
                new Payment(1, PaymentType.Card, 50.0),
                new Payment(2, PaymentType.Cash, 30.0),
                new Payment(3, PaymentType.Card, 20.0)
        );

        when(paymentRepo.getAll()).thenReturn(mockPayments);

        // Act
        double totalCard = paymentService.getTotalAmount(PaymentType.Card);

        // Assert
        assertEquals(70.0, totalCard, 0.001);
    }
}
