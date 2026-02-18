package kz.hawk.payment_integration_back.mapper;

import kz.hawk.payment_integration_back.model.dto.PaymentDto;
import kz.hawk.payment_integration_back.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
  PaymentDto toDto(Payment payment);
}
