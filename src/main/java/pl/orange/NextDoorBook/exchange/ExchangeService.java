package pl.orange.NextDoorBook.exchange;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.exchange.dto.ExchangeAddDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTOMapper;
import pl.orange.NextDoorBook.exchange.dto.ExchangeReservationDTO;
import pl.orange.NextDoorBook.exchange.exception.ExchangeImpossibleException;
import pl.orange.NextDoorBook.exchange.exception.ExchangeNotFoundException;
import pl.orange.NextDoorBook.exchange.exception.ExchangeOwnerException;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.UserRepository;
import pl.orange.NextDoorBook.user.exceptions.UserNotFoundException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeDTOMapper exchangeDTOMapper;

    public ExchangeAddDTO addExchange(ExchangeAddDTO exchangeAddDTO) {
        Exchange exchangeToAdd = exchangeDTOMapper.mapToAddEntity(exchangeAddDTO);

        return exchangeDTOMapper.mapToAddDTO(exchangeRepository.addExchange(exchangeToAdd));

    }

    public ExchangeDTO addBookReservation(ExchangeReservationDTO exchangeReservationDTO) {
        Book bookToExchange = bookRepository.getBookByID(exchangeReservationDTO.bookId())
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + exchangeReservationDTO.bookId() + " does not exist"));
        User renter = userRepository.getUserById(exchangeReservationDTO.renterId())
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + exchangeReservationDTO.renterId() + " does not exist"));
        Exchange reservationToSave = createReservation(bookToExchange, renter);
        return exchangeDTOMapper.mapToDTO(exchangeRepository.saveExchange(reservationToSave));
    }

    public ExchangeDTO confirmBookExchange(Long exchangeId, Long ownerId){
        return exchangeRepository.getExchangeById(exchangeId)
                .map(exchange -> {
                    if(exchange.getOwner().getId() != ownerId){
                        throw new ExchangeOwnerException("User with id " + ownerId + " is not this book owner");
                    }
                    if (exchangeRepository.checkBookAvailability(exchange.getBook().getId())){
                        throw new ExchangeImpossibleException("Exchange is impossible.Book is already borrowed.");
                    }
                    exchange.setConfirmExchange(true);
                    exchange.setStartRent(LocalDate.now());
                    return exchangeDTOMapper.mapToDTO(exchangeRepository.saveExchange(exchange));
                }).orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + exchangeId + " does not exist"));
    }

    public ExchangeDTO confirmBookReturn(Long exchangeId, Long ownerId){
        return exchangeRepository.getExchangeById(exchangeId)
                .map(exchange -> {
                    if(exchange.getOwner().getId() != ownerId){
                        throw new ExchangeOwnerException("User with id " + ownerId + " is not this book owner");
                    }
                    exchange.setConfirmReturn(true);
                    exchange.setEndRent(LocalDate.now());
                    return exchangeDTOMapper.mapToDTO(exchangeRepository.saveExchange(exchange));
                }).orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + exchangeId + " does not exist"));
    }

    private Exchange createReservation(Book bookToExchange, User renter) {
        return new Exchange(
                null,
                null,
                null,
                bookToExchange.getOwner(),
                renter,
                bookToExchange,
                false,
                false
        );
    }

    public void deleteExchangeById(Long id) {
        if (exchangeRepository.getExchangeById(id).isEmpty()) {
            throw new ExchangeNotFoundException("Exchange with id " + id + " doesn't exist");
        }
        exchangeRepository.deleteExchangeById(id);

    }

    public ExchangeDTO getExchangeById(Long id) {
        return exchangeRepository
                .getExchangeById(id)
                .map(exchangeDTOMapper::mapToDTO)
                .orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + id + " doesn't exist"));


    }

    public ExchangeDTO updateExchange(ExchangeDTO exchangeDTO) {

        exchangeRepository
                .getExchangeById(exchangeDTOMapper.mapToEntity(exchangeDTO).getId())
                .orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + exchangeDTO.id() + " doesn't exist"));


        Exchange exchange = exchangeRepository.updateExchange(exchangeDTOMapper.mapToEntity(exchangeDTO));

        return exchangeDTOMapper.mapToDTO(exchange);

    }
}
