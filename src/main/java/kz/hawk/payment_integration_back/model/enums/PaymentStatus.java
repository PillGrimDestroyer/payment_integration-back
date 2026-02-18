package kz.hawk.payment_integration_back.model.enums;


import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 10:38
 */
@RequiredArgsConstructor
public enum PaymentStatus {
  CREATED,
  CLIENT_PAYMENT_WAITING,
  BANK_APPROVAL_WAITING,
  SUCCESSFUL,
  NOT_ENOUGH_MONEY,
  CLIENT_CANCELED,
  BANK_REJECTED;

  private static Map<PaymentStatus, Set<PaymentStatus>> acceptableStatuses = Map.of(
    CREATED, Set.of(CLIENT_PAYMENT_WAITING, CLIENT_CANCELED),
    CLIENT_PAYMENT_WAITING, Set.of(BANK_APPROVAL_WAITING, NOT_ENOUGH_MONEY, CLIENT_CANCELED),
    BANK_APPROVAL_WAITING, Set.of(SUCCESSFUL, BANK_REJECTED, NOT_ENOUGH_MONEY, CLIENT_CANCELED),
    NOT_ENOUGH_MONEY, Set.of(CLIENT_PAYMENT_WAITING, CLIENT_CANCELED),
    SUCCESSFUL, Set.of(),
    CLIENT_CANCELED, Set.of(),
    BANK_REJECTED, Set.of()
  );

  public boolean canProceedTo(PaymentStatus nextStatus) {
    return acceptableStatuses.getOrDefault(this, Set.of()).contains(nextStatus);
  }

}
