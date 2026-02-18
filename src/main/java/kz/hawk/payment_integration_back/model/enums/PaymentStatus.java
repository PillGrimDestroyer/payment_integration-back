package kz.hawk.payment_integration_back.model.enums;


/**
 * @author Work (Hawk)
 * @since 18.02.2026 10:38
 */
public enum PaymentStatus {
  CREATED,
  CLIENT_PAYMENT_WAITING,
  BANK_APPROVAL_WAITING,
  SUCCESSFUL,
  NOT_ENOUGH_MONEY,
  CLIENT_CANCELED,
  BANK_REJECTED
}
