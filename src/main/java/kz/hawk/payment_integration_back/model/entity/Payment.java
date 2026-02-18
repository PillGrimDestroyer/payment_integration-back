package kz.hawk.payment_integration_back.model.entity;

import jakarta.persistence.*;
import kz.hawk.payment_integration_back.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
  @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "amount", nullable = false, precision = 19, scale = 2)
  @JdbcTypeCode(SqlTypes.NUMERIC)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private PaymentStatus status;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "created_at", nullable = false)
  @JdbcTypeCode(SqlTypes.TIMESTAMP)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  @JdbcTypeCode(SqlTypes.TIMESTAMP)
  private LocalDateTime updatedAt;

  @Version
  private int version;

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

}
