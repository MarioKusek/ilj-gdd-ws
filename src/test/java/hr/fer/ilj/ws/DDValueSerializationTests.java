package hr.fer.ilj.ws;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import hr.fer.ilj.gdd.DDValue;

@JsonTest
class DDValueSerializationTests {

  @Autowired
  private JacksonTester<DDValue> json;

  @Test
  void serializtion() throws Exception {
    DDValue value = new DDValue(LocalDate.of(2023, 10, 5), 1.56);

    String expectedContent = """
            {
              "date":"2023-10-05",
              "value":1.56
            }
            """;
    assertThat(json.write(value)).isStrictlyEqualToJson(expectedContent);
  }

}
