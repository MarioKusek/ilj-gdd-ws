package hr.fer.ilj.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hr.fer.ilj.gdd.GddService;

@Configuration
public class GddConfiguration {

  @Bean
  GddService gddService() {
    // TODO update
    return new GddService(null);
  }

}
