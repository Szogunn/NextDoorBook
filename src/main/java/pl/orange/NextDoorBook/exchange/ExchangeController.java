package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping("/exchanges")
    public ResponseEntity<?> addExchange(Exchange exchange){
        return exchangeService.addExchange(exchange);
    }

    @DeleteMapping("/exchanges/{id}")
    public ResponseEntity<?> deleteExchangeById(@PathVariable Long id){
        return exchangeService.deleteExchangeById(id);
    }

    @GetMapping("/exchanges/{id}")
    public ResponseEntity<?> getExchangeById(@PathVariable Long id){
        return exchangeService.getExchangeById(id);
    }

    @PutMapping("/echanges/{id}")
    public ResponseEntity<?> updateExchange(@PathVariable Long id,@RequestBody Exchange toUpdate){
        return exchangeService.updateExchange(id,toUpdate);
    }
}
