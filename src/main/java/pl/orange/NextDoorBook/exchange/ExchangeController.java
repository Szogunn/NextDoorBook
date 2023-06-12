package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.exchange.dto.ExchangeAddDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeReservationDTO;

import java.util.Set;


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

    @PostMapping(path = "/confirm/exchange/{exchangeId}/{ownerId}")
    public ResponseEntity<ExchangeDTO> confirmBookExchange(@PathVariable Long exchangeId, @PathVariable Long ownerId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.confirmBookExchange(exchangeId,ownerId));
    }

    @PostMapping(path = "/confirm/exchange/{exchangeId}/{ownerId}")
    public ResponseEntity<ExchangeDTO> rejectBookReservation(@PathVariable Long exchangeId, @PathVariable Long ownerId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.rejectBookReservation(exchangeId,ownerId));
    }

    @PostMapping(path = "/confirm/return/{exchangeId}/{ownerId}")
    public ResponseEntity<ExchangeDTO> confirmBookReturn(@PathVariable Long exchangeId,@PathVariable Long ownerId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.confirmBookReturn(exchangeId,ownerId));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ExchangeDTO> deleteExchangeById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.deleteExchangeById(id));
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

    @GetMapping(path = "/reservation/{ownerId}")
    public ResponseEntity<Set<ExchangeDTO>> getBookReservationByOwner(@PathVariable Long ownerId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.getBookReservationByOwner(ownerId));
    }

    @GetMapping(path = "/owner/{ownerId}")
    public ResponseEntity<Set<ExchangeDTO>> getExchangesByOwner(@PathVariable Long ownerId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.getExchangesByOwner(ownerId));
    }

    @GetMapping(path = "/renter/{renterId}")
    public ResponseEntity<Set<ExchangeDTO>> getExchangesByRenter(@PathVariable Long renterId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.getExchangesByRenter(renterId));
    }

    @GetMapping(path = "/book/{bookId}")
    public ResponseEntity<Set<ExchangeDTO>> getExchangesByBook(@PathVariable Long bookId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.getExchangesByBook(bookId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Set<ExchangeDTO>> getExchangesByUser(@PathVariable Long userId){
        return ResponseEntity
                .status(200)
                .body(exchangeService.getExchangesByUser(userId));
    }
}
