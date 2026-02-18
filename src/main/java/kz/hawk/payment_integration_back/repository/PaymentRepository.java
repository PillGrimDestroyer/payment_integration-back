package kz.hawk.payment_integration_back.repository;

import kz.hawk.payment_integration_back.model.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
