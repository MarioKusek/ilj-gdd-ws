package hr.fer.ilj.ws;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hr.fer.ilj.gdd.GddService;
import hr.fer.ilj.gdd.common.DataLoader;
import hr.fer.ilj.gdd.infrastructure.FileDataLoader;

@Configuration
public class GddConfiguration {

  @Bean
  DataLoader loader(@Value("${sensorData}") String sensorData) {
    return new FileDataLoader(Path.of(sensorData));
  }

  @Bean
  GddService gddService(DataLoader loader) {
    // TODO update
    return new GddService(loader);
  }

}
