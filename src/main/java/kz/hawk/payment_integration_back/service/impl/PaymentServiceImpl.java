package kz.hawk.payment_integration_back.service.impl;


import jakarta.persistence.EntityNotFoundException;
import kz.hawk.payment_integration_back.mapper.PaymentMapper;
import kz.hawk.payment_integration_back.model.entity.Payment;
import kz.hawk.payment_integration_back.model.enums.PaymentStatus;
import kz.hawk.payment_integration_back.repository.PaymentRepository;
import kz.hawk.payment_integration_back.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 13:49
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper     paymentMapper;

  @Override
  @Transactional
  public void eventWaitClientPayment(UUID paymentId) {
    proceedPaymentToNextStatus(paymentId, PaymentStatus.CLIENT_PAYMENT_WAITING);
  }

  @Override
  @Transactional
  public void eventClientPayed(UUID paymentId) {
    proceedPaymentToNextStatus(paymentId, PaymentStatus.BANK_APPROVAL_WAITING);
  }

  private void proceedPaymentToNextStatus(UUID paymentId, PaymentStatus nextStatus) {
    Payment payment = paymentRepository.findById(paymentId)
                                       .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

    if (!payment.getStatus().canProceedTo(nextStatus)) {
      return;
    }

    payment.setStatus(nextStatus);

    paymentRepository.save(payment);
  }

}
