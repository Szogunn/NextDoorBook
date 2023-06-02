package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/exchanges")

public class ExchangeController {
    private final ExchangeService exchangeService;


    @PostMapping(path = "")
    public ResponseEntity<ExchangeDTO> addExchange(@RequestBody ExchangeDTO exchangeDTO) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.addExchange(exchangeDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ExchangeDTO> deleteExchangeById(@PathVariable Long id) {
        exchangeService.deleteExchangeById(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ExchangeDTO> getExchangeById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.getExchangeById(id));
    }

    @PatchMapping(path = "")
    public ResponseEntity<ExchangeDTO> updateExchange(@RequestBody ExchangeDTO exchangeDTO) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.updateExchange(exchangeDTO));
    }
}
