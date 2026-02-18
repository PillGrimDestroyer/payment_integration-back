package kz.hawk.payment_integration_back.service;


/**
 * @author Work (Hawk)
 * @since 18.02.2026 13:48
 */
public interface PaymentService {
  void eventClientPayed(Long paymentId);

  void eventWaitClientPayment(Long paymentId);
}
