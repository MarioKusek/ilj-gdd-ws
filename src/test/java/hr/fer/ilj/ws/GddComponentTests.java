package hr.fer.ilj.ws;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.fer.ilj.gdd.GddRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GddComponentTests {
  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void callGddSearchWithRealData() throws Exception {
    GddRequest request = new GddRequest(
        "0004A30B0021EF31",
        LocalDate.of(2022, 8, 6),
        LocalDate.of(2022, 9, 1),
        LocalDate.of(2022, 9, 2),
        10,
        30,
        false);

    String body = restTemplate.postForObject("/search", request, String.class);

    assertThat(mapper.readTree(body)).isEqualTo(mapper.readTree("""
            [
              {
                "date":"2022-09-01",
                "value":14.650000000000006
              },
              {
                "date":"2022-09-02",
                "value":0.0
              }
            ]
            """));



  }

}
