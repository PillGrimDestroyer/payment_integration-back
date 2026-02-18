package kz.hawk.payment_integration_back.bean;


import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import javax.sql.DataSource;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 9:25
 */
@Configuration
@RequiredArgsConstructor
public class LiquibaseConfiguration {

  private final DataSource dataSource;

  @Bean
  public SpringLiquibase liquibase() {
    var liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }

  @Bean
  public IdGenerator idGenerator() {
    return new AlternativeJdkIdGenerator();
  }

}
