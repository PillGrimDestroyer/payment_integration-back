package kz.hawk.payment_integration_back.bean;


import kz.hawk.payment_integration_back.config.SecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import tech.ailef.snapadmin.external.SnapAdminProperties;

/**
 * @author Work (Hawk)
 * @since 18.02.2026 14:50
 */
@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

  private final SecurityConfig      securityConfig;
  private final SnapAdminProperties snapAdminProperties;

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails securityUser = User.withUsername(securityConfig.username())
                                   .password(passwordEncoder.encode(securityConfig.password()))
                                   .roles("ADMIN")
                                   .build();

    return new InMemoryUserDetailsManager(securityUser);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    log.warn("snap base url:: {}", snapAdminProperties.getBaseUrl());

    http
      .httpBasic(Customizer.withDefaults())
      .csrf(x -> x
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .ignoringRequestMatchers(
          "/instances",
          "/actuator/**",
          "/api/**"
        ))
      .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
      .authorizeHttpRequests(x -> x
        .requestMatchers("/actuator/**").permitAll()
        .requestMatchers(HttpMethod.POST, "/" + snapAdminProperties.getBaseUrl() + "/**").hasRole("ADMIN")
        .requestMatchers("/" + snapAdminProperties.getBaseUrl() + "/**").authenticated()
        .anyRequest().authenticated()
      )
      .exceptionHandling(e -> e.accessDeniedHandler((req, res, ex) -> {
        AccessDeniedHandlerImpl defaultHandler = new AccessDeniedHandlerImpl();
        if (req.getServletPath().startsWith("/" + snapAdminProperties.getBaseUrl() + "/")) {
          res.sendRedirect("/" + snapAdminProperties.getBaseUrl() + "/forbidden");
        } else {
          defaultHandler.handle(req, res, ex);
        }
      }));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

}
