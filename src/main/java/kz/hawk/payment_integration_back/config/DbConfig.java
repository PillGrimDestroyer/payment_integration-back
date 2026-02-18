package kz.hawk.payment_integration_back.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @param host        Retrieves the host address for the database. Typically read from the environment variable "DB_HOST"
 * @param port        Retrieves the port number for the database connection. Typically read from the environment variable "DB_PORT"
 * @param scheme      Retrieves the database name. Typically read from the environment variable "DB_NAME"
 * @param user        Retrieves the username for the database connection. Typically read from the environment variable "DB_USER"
 * @param password    Retrieves the password for the database connection. Typically read from the environment variable "DB_PASSWORD"
 * @param maxPoolSize Retrieves the maximum number of connections in the connection pool. Typically read from the environment variable "DB_MAX_POOL_SIZE"
 * @author Work (Hawk)
 * @since 18.02.2026 9:26
 */
@ConfigurationProperties(prefix = "db")
public record DbConfig(
  String host,
  int port,
  String scheme,
  String user,
  String password,
  int maxPoolSize
) {
}
