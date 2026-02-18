package kz.hawk.payment_integration_back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest
@ContextConfiguration(classes = {BeanConfigTests.class})
@SuppressWarnings("NewClassNamingConvention")
public class ParentTestNG extends AbstractTestNGSpringContextTests {

  @Test
  void contextLoads() {
  }

}
