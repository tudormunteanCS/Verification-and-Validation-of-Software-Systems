package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import static org.junit.jupiter.api.Assertions.*;
class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentRepository paymentRepo;
    private PaymentValidator paymentValidator;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepo = new MenuRepository();
        this.paymentRepo = new PaymentRepository();
        this.paymentValidator = new PaymentValidator();
        this.paymentService = new PaymentService(menuRepo, paymentRepo, paymentValidator);

        paymentRepo.getAll().clear();
        paymentRepo.writeAll();
    }

    @AfterEach
    void tearDown() {
        paymentRepo.getAll().clear();
        paymentRepo.writeAll();
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("EC Test 1")
    @ValueSource(doubles = {10.50, 50.00, 850.21})
    void addValidPaymentTestEC(double amount) {
        paymentService.addPayment(3, PaymentType.Card, amount);

        assertEquals(1, paymentRepo.getAll().size());
    }

    @Test
    @Order(2)
    @DisplayName("EC Test 2")
    void addInvalidPaymentTestEC() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(10, PaymentType.Card, 50.00);
        }, "Expected addPayment to throw, but it didn't");

        assertEquals("Table number must be between 1 and 8.", thrown.getMessage());
    }


    @Test
    @Order(3)
    @DisplayName("EC Test 3")
    void addInvalidPaymentTestEC2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(5, PaymentType.Card, 1250.33);
        }, "Expected addPayment to throw, but it didn't");

        assertEquals("Amount must be between 0.0 and 1000.0.", thrown.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("EC Test 4")
    void addInvalidPaymentTestEC3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(5, PaymentType.Card, -23.01);
        }, "Expected addPayment to throw, but it didn't");

        assertEquals("Amount must be between 0.0 and 1000.0.", thrown.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("EC Test 5")
    void addInvalidPaymentTestEC4() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(10, PaymentType.Card, -23.01);
        }, "Expected addPayment to throw, but it didn't");

        assertEquals("Table number must be between 1 and 8. Amount must be between 0.0 and 1000.0.", thrown.getMessage());

    }

    @Test
    @Order(6)
    @DisplayName("BVA Test 1")
    //@Disabled
    void addValidPaymentTestBva() {
        paymentService.addPayment(1, PaymentType.Card, 0.00);

        assertEquals(1, paymentRepo.getAll().size());
    }

    @Test
    @Order(7)
    @DisplayName("BVA Test 2")
    void addValidPaymentTestBva2() {
        paymentService.addPayment(8, PaymentType.Card, 999.99);
    }

    @Test
    @Order(8)
    @DisplayName("BVA Test 3")
    void addInvalidPaymentTestBva3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(9, PaymentType.Card, 33.3);
        }, "Expected addPayment to throw, but it didn't");

        assertEquals("Table number must be between 1 and 8.", thrown.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("BVA Test 4")
    void addInvalidPaymentTestBva4() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(5, PaymentType.Card, -0.01);
        }, "Expected addPayment to throw, but it didn't");

        assertEquals("Amount must be between 0.0 and 1000.0.", thrown.getMessage());
    }




}