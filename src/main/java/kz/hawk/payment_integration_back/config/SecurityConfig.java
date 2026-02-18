package kz.hawk.payment_integration_back.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @param username gets app username. Typically read from the environment variable "SECURITY_USERNAME"
 * @param password gets app password. Typically read from the environment variable "SECURITY_PASSWORD"
 * @author Work (Hawk)
 * @since 18.02.2026 15:03
 */
@ConfigurationProperties(prefix = "security")
public record SecurityConfig(
  String username,
  String password
) {
}
