package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExchangeRepository {
    private final IExchangeRepository iExchangeRepository;

    public Exchange addExchange(Exchange exchange) {
        return iExchangeRepository.save(exchange);
    }

    public void deleteExchangeById(Long id) {
        iExchangeRepository.deleteById(id);
    }

    public Optional<Exchange> getExchangeById(Long id) {
        return iExchangeRepository.findById(id);
    }

    public Exchange updateExchange(Exchange exchange) {
        return iExchangeRepository.save(exchange);
    }

    public Exchange saveExchange(Exchange exchange){
        return iExchangeRepository.save(exchange);
    }

}
