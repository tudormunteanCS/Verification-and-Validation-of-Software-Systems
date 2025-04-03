package pizzashop.validator;

import pizzashop.model.PaymentType;

public class PaymentValidator {
    public static void validatePayment(int table, PaymentType paymentType, double amount) {
        StringBuilder errorMessage = new StringBuilder();

        if (table < 1 || table > 8) {
            errorMessage.append("Table number must be between 1 and 8. ");
        }

        if (amount < 0.0 || amount > 1000.0) {
            errorMessage.append("Amount must be between 0.0 and 1000.0. ");
        }

        if (errorMessage.length() > 0) {
            throw new IllegalArgumentException(errorMessage.toString().trim());
        }
    }
}
