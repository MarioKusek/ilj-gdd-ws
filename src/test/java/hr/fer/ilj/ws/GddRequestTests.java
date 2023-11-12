package hr.fer.ilj.ws;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import hr.fer.ilj.gdd.GddRequest;

@JsonTest
class GddRequestTests {

  @Autowired
  private JacksonTester<GddRequest> json;

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
