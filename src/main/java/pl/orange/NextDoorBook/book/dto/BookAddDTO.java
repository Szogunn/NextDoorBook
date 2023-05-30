package pl.orange.NextDoorBook.book.dto;

import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.dto.AuthorDTO;
import pl.orange.NextDoorBook.book.BookGenre;

import java.time.LocalDate;
import java.util.Set;

public record BookAddDTO(
        String tittle,
        long isbn,
        int numPages,
        String language,
        String publisher,
        LocalDate publishedYear,
        BookGenre bookGenre,
        Set<AuthorDTO> authors
){
}