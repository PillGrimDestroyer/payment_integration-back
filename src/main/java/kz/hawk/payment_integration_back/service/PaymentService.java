package kz.hawk.payment_integration_back.service;


import java.util.UUID;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 13:48
 */
public interface PaymentService {
  void eventClientPayed(UUID paymentId);

  void eventWaitClientPayment(UUID paymentId);
}
