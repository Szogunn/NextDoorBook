package pl.orange.NextDoorBook.exchange;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.util.Optional;
import java.util.Set;

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

    public boolean checkBookAvailability(Long bookId){
        return iExchangeRepository.findExchangeByBookIdAndConfirmExchangeTrueAndConfirmReturnFalse(bookId).isEmpty();
    }
    public Set<Exchange> getBooksReservationListByOwner(User owner){
        return iExchangeRepository.findExchangesByOwnerAndConfirmExchangeFalse(owner);
    }

    public Set<Exchange> getExchangesByOwner(User owner){
        return iExchangeRepository.findExchangesByOwner(owner);
    }
     public Set<Exchange> getExchangesByRenter(User renter){
        return iExchangeRepository.findExchangesByRenter(renter);
     }

    public Set<Exchange> getExchangesByBook(Book book){
        return iExchangeRepository.findExchangesByBook(book);
    }

    public Set<Exchange> getExchangesByUser(User user){
        return iExchangeRepository.findExchangesByOwnerOrRenter(user,user);
    }


}
