package pl.orange.NextDoorBook.exchange.dto;

import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDate;

public record ExchangeDTO(
        Long id,
        LocalDate startRent,
        LocalDate endRent,
        User owner,
        User renter,
        Book book
) {
}
