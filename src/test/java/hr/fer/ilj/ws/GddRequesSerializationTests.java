package hr.fer.ilj.ws;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import hr.fer.ilj.gdd.GddRequest;

@JsonTest
class GddRequesSerializationTests {

  @Autowired
  private JacksonTester<GddRequest> json;

  @Test
  void serializtion() throws Exception {
    GddRequest request = new GddRequest(
        "fakeSensorId",
        LocalDate.of(2023, 5, 1),
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        10,
        30,
        false);

    String expectedContent = """
            {
              "sensorId":"fakeSensorId",
              "plantingDate":"2023-05-01",
              "startDate":"2023-10-01",
              "endDate":"2023-11-01",
              "minTemp":10.0,
              "maxTemp":30.0,
              "cumulative":false
            }
            """;
    assertThat(json.write(request)).isStrictlyEqualToJson(expectedContent);
  }

}
