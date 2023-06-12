package pl.orange.NextDoorBook.book.dto;

import pl.orange.NextDoorBook.author.dto.AuthorAddDTO;
import pl.orange.NextDoorBook.book.BookGenre;

import java.time.LocalDate;
import java.util.Set;

public record BookAddDTO(
        String title,
        long isbn,
        int numPages,
        String language,
        String publisher,
        LocalDate publishedYear,
        BookGenre bookGenre,
        Set<AuthorAddDTO> authors
) {
}
