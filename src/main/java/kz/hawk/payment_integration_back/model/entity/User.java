package kz.hawk.payment_integration_back.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
  @SequenceGenerator(name = "user_id_seq", sequenceName = "user_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  @Length(max = 255)
  @Column(name = "name", nullable = false)
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private String name;

  @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, orphanRemoval = true)
  private List<Payment> payments = new ArrayList<>();

}
