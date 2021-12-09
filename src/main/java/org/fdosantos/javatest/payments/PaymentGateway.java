package org.fdosantos.javatest.payments;

public interface PaymentGateway {

    PaymentResponse requestPayment(PaymentRequest request);
}
