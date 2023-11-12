package hr.fer.ilj.ws;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import hr.fer.ilj.gdd.DDValue;

@JsonTest
class DDValueSerializationTests {

  @Autowired
  private JacksonTester<DDValue> json;

  @Autowired
  private JacksonTester<List<DDValue>> jsonList;

  @Test
  void serialization() throws Exception {
    DDValue value = new DDValue(LocalDate.of(2023, 10, 5), 1.56);

    String expectedContent = """
            {
              "date":"2023-10-05",
              "value":1.56
            }
            """;
    assertThat(json.write(value)).isStrictlyEqualToJson(expectedContent);
  }

  @Test
  void listSerialization() throws Exception {
    List<DDValue> list = List.of(
        new DDValue(LocalDate.of(2023, 10, 5), 1.56),
        new DDValue(LocalDate.of(2023, 10, 6), 2.83),
        new DDValue(LocalDate.of(2023, 10, 7), 3.21)
        );

    String expectedContent = """
            [
              {
                "date":"2023-10-05",
                "value":1.56
              },
              {
                "date":"2023-10-06",
                "value":2.83
              },
              {
                "date":"2023-10-07",
                "value":3.21
              }
            ]
            """;
    assertThat(jsonList.write(list)).isStrictlyEqualToJson(expectedContent);
  }

}
