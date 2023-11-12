package hr.fer.ilj.ws;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.ilj.gdd.DDValue;
import hr.fer.ilj.gdd.GddRequest;

@RestController
public class GddController {

  @PostMapping("/search")
  List<DDValue> search(@RequestBody GddRequest request) {
    // TODO implement
    return List.of();
  }
}
