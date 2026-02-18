package kz.hawk.payment_integration_back.controller;


import kz.hawk.payment_integration_back.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 13:23
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @PutMapping("/{paymentId}/wait-client-payment")
  public void waitClientPayment(@PathVariable Long paymentId) {
    paymentService.eventWaitClientPayment(paymentId);
  }

  @PutMapping("/{paymentId}/client-payed")
  public void clientPayed(@PathVariable Long paymentId) {
    paymentService.eventClientPayed(paymentId);
  }

}
