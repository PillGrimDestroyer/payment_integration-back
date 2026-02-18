package kz.hawk.payment_integration_back.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.hawk.payment_integration_back.model.entity.Payment;
import kz.hawk.payment_integration_back.model.enums.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link Payment}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentDto(
  Long id,
  BigDecimal amount,
  PaymentStatus status,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) implements Serializable {
}
