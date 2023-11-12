package hr.fer.ilj.ws;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    List<DDValue> result = service.search(request);

    if(result.isEmpty())
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(result);
  }
}
