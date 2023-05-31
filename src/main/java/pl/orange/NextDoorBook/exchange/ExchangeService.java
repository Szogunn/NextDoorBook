package pl.orange.NextDoorBook.exchange;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.exchange.exception.ExchangeNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;

    public Exchange addExchange(Exchange exchange) {
        if (exchange == null) {
            throw new IllegalStateException("Can't save null exchange");
        }
        return exchangeRepository.addExchange(exchange);

    }

    public void deleteExchangeById(Long id) {
        if (exchangeRepository.getExchangeById(id).isEmpty()) {
            throw new ExchangeNotFoundException("Exchange with id " + id + " doesn't exist");
        }
        exchangeRepository.deleteExchangeById(id);

    }

    public Exchange getExchangeById(Long id) {
        return exchangeRepository.getExchangeById(id)
                .orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + id + " doesn't exist"));
    }

    public Exchange updateExchange(Exchange exchange) {
        if (exchangeRepository.getExchangeById(exchange.getId()).isEmpty()) {
            throw new ExchangeNotFoundException("Exchange with id " + exchange.getId() + " doesn't exist");
        }
        Exchange exchangeToUpdate = exchangeRepository.getExchangeById(exchange.getId()).get();
        exchangeToUpdate.setBook(exchange.getBook());
        exchangeToUpdate.setOwner(exchange.getOwner());
        exchangeToUpdate.setRenter(exchange.getRenter());
        exchangeToUpdate.setStartRent(exchange.getStartRent());
        exchangeToUpdate.setEndRent(exchange.getEndRent());
        return exchangeRepository.save(exchangeToUpdate);

    }
}
