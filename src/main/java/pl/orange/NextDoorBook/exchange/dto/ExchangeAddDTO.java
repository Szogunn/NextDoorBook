package pl.orange.NextDoorBook.exchange.dto;

import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.dto.UserDTO;

import java.time.LocalDate;

public record ExchangeAddDTO(
        LocalDate startRent,
        LocalDate endRent,
        UserDTO owner,
        UserDTO renter,
        BookDTO book) {

}
