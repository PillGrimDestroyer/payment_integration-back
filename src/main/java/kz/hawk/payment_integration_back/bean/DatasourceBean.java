package kz.hawk.payment_integration_back.bean;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.hawk.payment_integration_back.config.DbConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 9:26
 */
@Configuration
@RequiredArgsConstructor
public class DatasourceBean {

  private final DbConfig dbConfig;

  @Bean
  public DataSource dataSource() {
    var url = "jdbc:postgresql://" + dbConfig.host() + ":" + dbConfig.port() + "/" + dbConfig.scheme();

    var config = new HikariConfig();

    config.setDriverClassName("org.postgresql.Driver");
    config.setJdbcUrl(url);
    config.setUsername(dbConfig.user());
    config.setPassword(dbConfig.password());
    config.setMaximumPoolSize(dbConfig.maxPoolSize());

    return new HikariDataSource(config);
  }

}
