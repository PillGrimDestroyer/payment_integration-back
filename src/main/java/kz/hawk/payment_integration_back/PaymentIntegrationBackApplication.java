package kz.hawk.payment_integration_back;

import kz.hawk.payment_integration_back.config.DbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = DbConfig.class)
public class PaymentIntegrationBackApplication {

  static void main(String[] args) {
    SpringApplication.run(PaymentIntegrationBackApplication.class, args);
  }

}
