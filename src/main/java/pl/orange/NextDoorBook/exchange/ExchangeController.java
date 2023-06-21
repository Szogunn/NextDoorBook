package pl.orange.NextDoorBook.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.exchange.dto.ExchangeAddDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeReservationDTO;
import pl.orange.NextDoorBook.user.User;

import java.util.Set;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/exchanges")

public class ExchangeController {
    private final ExchangeService exchangeService;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExchangeAddDTO> addExchange(@RequestBody ExchangeAddDTO exchangeAddDTO) {
        return ResponseEntity
                .status(200)
                .body(exchangeService.addExchange(exchangeAddDTO));
    }

    @PostMapping(path = "/reservation/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ExchangeDTO> addBookReservation(@PathVariable Long bookId, UsernamePasswordAuthenticationToken user) {
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        return ResponseEntity
                .status(200)
                .body(exchangeService.addBookReservation(bookId,userFromObjectMapper.getId()));
    }

    @PostMapping(path = "/confirm/exchange/{exchangeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ExchangeDTO> confirmBookExchange(@PathVariable Long exchangeId, UsernamePasswordAuthenticationToken user){
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        return ResponseEntity
                .status(200)
                .body(exchangeService.confirmBookExchange(exchangeId, userFromObjectMapper.getId()));
    }

    @PostMapping(path = "/reject/exchange/{exchangeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ExchangeDTO> rejectBookReservation(@PathVariable Long exchangeId, UsernamePasswordAuthenticationToken user){
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        return ResponseEntity
                .status(200)
                .body(exchangeService.rejectBookReservation(exchangeId, userFromObjectMapper.getId()));
    }

    @PostMapping(path = "/confirm/return/{exchangeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ExchangeDTO> confirmBookReturn(@PathVariable Long exchangeId,UsernamePasswordAuthenticationToken user){
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        return ResponseEntity
                .status(200)
                .body(exchangeService.confirmBookReturn(exchangeId, userFromObjectMapper.getId()));
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
