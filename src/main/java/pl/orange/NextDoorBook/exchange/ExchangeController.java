package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTOMapper;

@Controller
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final ExchangeDTOMapper exchangeDTOMapper;

    @PostMapping("/exchanges")
    public ResponseEntity<ExchangeDTO> addExchange(@RequestBody ExchangeDTO exchangeDTO){
        return ResponseEntity
                .status(200)
                .body(exchangeDTOMapper.mapToDTO(exchangeService.addExchange(exchangeDTOMapper.mapToEntity(exchangeDTO))) );
    }

    @DeleteMapping("/exchanges/{id}")
    public ResponseEntity<ExchangeDTO> deleteExchangeById(@PathVariable Long id){
        exchangeService.deleteExchangeById(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping("/exchanges/{id}")
    public ResponseEntity<ExchangeDTO> getExchangeById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(exchangeDTOMapper.mapToDTO(exchangeService.getExchangeById(id)));
    }

    @PutMapping("/echanges")
    public ResponseEntity<ExchangeDTO> updateExchange(@RequestBody ExchangeDTO exchangeDTO){
        return ResponseEntity
                .status(200)
                .body(exchangeDTOMapper.mapToDTO(exchangeService.updateExchange(exchangeDTOMapper.mapToEntity(exchangeDTO))));
    }
}
