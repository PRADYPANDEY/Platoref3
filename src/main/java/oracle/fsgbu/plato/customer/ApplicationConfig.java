package oracle.fsgbu.plato.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import oracle.fsgbu.plato.core.interceptor.PlatoInterceptor;;

@Configuration
@EnableTransactionManagement
public class ApplicationConfig extends WebMvcConfigurerAdapter {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

  @Bean
  public MappedInterceptor gemInterceptor(PlatoInterceptor platoInterceptor) {
    LOG.info("Added interceptor for fetching the application headers");
    return new MappedInterceptor(new String[] {"/**"}, platoInterceptor);
  }
}
