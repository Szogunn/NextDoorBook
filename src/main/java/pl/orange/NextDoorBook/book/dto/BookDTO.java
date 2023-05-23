package pl.orange.NextDoorBook.book.dto;

import pl.orange.NextDoorBook.book.BookGenre;

import java.time.LocalDate;

public record BookDTO (
        String tittle,
        long isbn,
        int numPages,
        String language,
        String publisher,
        LocalDate publishedYear,
        BookGenre bookGenre
){
}
