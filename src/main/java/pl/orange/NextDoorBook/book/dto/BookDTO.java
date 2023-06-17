package pl.orange.NextDoorBook.book.dto;

import pl.orange.NextDoorBook.author.dto.AuthorDTO;
import pl.orange.NextDoorBook.book.BookGenre;
import pl.orange.NextDoorBook.user.dto.UserDTO;

import java.time.LocalDate;
import java.util.Set;

public record BookDTO(
        Long id,
        String tittle,
        long isbn,
        int numPages,
        String language,
        String publisher,
        LocalDate publishedYear,
        BookGenre bookGenre,
        Set<AuthorDTO> authors,
        UserDTO owner
){
}
