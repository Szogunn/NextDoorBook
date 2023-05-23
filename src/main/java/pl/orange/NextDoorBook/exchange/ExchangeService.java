package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;

    public ResponseEntity<?> addExchange(Exchange exchange){
        if (exchange == null){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        exchangeRepository.addExchange(exchange);
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> deleteExchangeById(Long id){
        if (exchangeRepository.getExchangeById(id).isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        exchangeRepository.deleteExchangeById(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> getExchangeById(Long id){
        Optional<Exchange> exchange = exchangeRepository.getExchangeById(id);
        if (exchange.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(exchange);
    }

    public ResponseEntity<?> updateExchange(Long id, Exchange toUpdate){
        if (exchangeRepository.getExchangeById(id).isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        exchangeRepository.updateExchange(id, toUpdate.getStartRent(),toUpdate.getEndRent(),toUpdate.getOwner(),toUpdate.getRenter(),toUpdate.getBook());
        return ResponseEntity
                .status(200)
                .build();
    }


}
