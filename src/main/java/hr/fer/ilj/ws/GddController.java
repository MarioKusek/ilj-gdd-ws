package hr.fer.ilj.ws;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.ilj.gdd.DDValue;
import hr.fer.ilj.gdd.GddRequest;
import hr.fer.ilj.gdd.GddService;

@RestController
public class GddController {
  private final GddService service;

  public GddController(GddService service) {
    this.service = service;
  }

  @PostMapping("/search")
  ResponseEntity<List<DDValue>> search(@RequestBody GddRequest request) {
    validate(request);

    List<DDValue> result = service.search(request);

    if(result.isEmpty())
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(result);
  }

  private void validate(GddRequest request) {
    if(request.sensorId() == null)
      throw new GddRequestValidationException("Sensor id must not bee null.");

  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public static class GddRequestValidationException extends RuntimeException {

    public GddRequestValidationException(String message) {
      super(message);
    }

  }
}
