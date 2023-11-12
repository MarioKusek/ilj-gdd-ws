package hr.fer.ilj.ws;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.fer.ilj.gdd.DDValue;
import hr.fer.ilj.gdd.GddRequest;
import hr.fer.ilj.gdd.GddService;

@WebMvcTest(GddController.class)
class GddControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private GddService service;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void callController() throws Exception {
    GddRequest request = new GddRequest(
        "fakeSensorId",
        LocalDate.of(2023, 5, 1),
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        10,
        30,
        false);

    given(service.search(request)).willReturn(List.of(
        new DDValue(LocalDate.of(2023, 10, 1), 0.1)
        ));

    mvc.perform(post("/search")
        .content(asJsonString(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().json("""
              [
                {
                  "date":"2023-10-01",
                  "value":0.1
                }
              ]
              """));
  }

  @Test
  void noSensor() throws Exception {
    GddRequest request = new GddRequest(
        "fakeSensorId",
        LocalDate.of(2023, 5, 1),
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        10,
        30,
        false);

    given(service.search(request)).willReturn(List.of());

    mvc.perform(post("/search")
        .content(asJsonString(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound());
  }

  @Test
  void sensorIdIsNull() throws Exception {
    GddRequest request = new GddRequest(
        null,
        LocalDate.of(2023, 5, 1),
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        10,
        30,
        false);

    mvc.perform(post("/search")
        .content(asJsonString(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  @Test
  void plantingDateIsNull() throws Exception {
    GddRequest request = new GddRequest(
        "fakeSensorId",
        null,
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        10,
        30,
        false);

    mvc.perform(post("/search")
        .content(asJsonString(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }


  @Test
  void startDateIsNull() throws Exception {
    GddRequest request = new GddRequest(
        "fakeSensorId",
        LocalDate.of(2023, 10, 1),
        null,
        LocalDate.of(2023, 11, 1),
        10,
        30,
        false);

    mvc.perform(post("/search")
        .content(asJsonString(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  @Test
  void endDateIsNull() throws Exception {
    GddRequest request = new GddRequest(
        "fakeSensorId",
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        null,
        10,
        30,
        false);

    mvc.perform(post("/search")
        .content(asJsonString(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }





  public String asJsonString(final Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
