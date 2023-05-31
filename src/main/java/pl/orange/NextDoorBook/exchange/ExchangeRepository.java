package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExchangeRepository {
private final IExchangeRepository iExchangeRepository;

    public Exchange addExchange(Exchange exchange){
       return iExchangeRepository.save(exchange);
    }
    public void deleteExchangeById(Long id){
        iExchangeRepository.deleteById(id);
    }
    public Optional<Exchange> getExchangeById(Long id){
        return iExchangeRepository.findById(id);
    }
    public void updateExchange(Long id, LocalDate startRent, LocalDate endRent, User owner, User renter, Book book){
        iExchangeRepository.updateExchange(id,startRent,endRent,owner,renter,book);
    }

    public Exchange save(Exchange exchange) {
        return iExchangeRepository.save(exchange);
    }
}
