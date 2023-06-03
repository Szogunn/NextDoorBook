package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.exchange.dto.ExchangeAddDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeReservationDTO;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/exchanges")

public class ExchangeController {
    private final ExchangeService exchangeService;


    @PostMapping(path = "")
    public ResponseEntity<ExchangeAddDTO> addExchange(@RequestBody ExchangeAddDTO exchangeAddDTO) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.addExchange(exchangeAddDTO));
    }

    @PostMapping(path = "/reservation")
    public ResponseEntity<ExchangeDTO> addBookReservation(@RequestBody ExchangeReservationDTO exchangeReservationDTO) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.addBookReservation(exchangeReservationDTO));
    }

    @PostMapping(path = "/confirm/{exchangeId}/{ownerId}")
    public ResponseEntity<ExchangeDTO> confirmBookExchange(@PathVariable Long exchangeId, @PathVariable Long ownerId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.confirmBookExchange(exchangeId,ownerId));
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
