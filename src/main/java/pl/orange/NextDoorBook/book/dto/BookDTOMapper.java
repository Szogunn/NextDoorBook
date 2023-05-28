package pl.orange.NextDoorBook.book.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class BookDTOMapper implements Function<Book, BookDTO> {

    private final BookRepository bookRepository;


    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTittle(),
                book.getIsbn(),
                book.getNumPages(),
                book.getLanguage(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getBookGenre(),
                (bookRepository
                        .getBookByID(book.getId())
                        .orElseThrow(() -> new BookNotFoundException(""))
                        .getAuthors())
        );
    }

    public Book apply(BookDTO book) {
        return Book.builder()
                .id(book.id())
                .tittle(book.tittle())
                .isbn(book.isbn())
                .numPages(book.numPages())
                .language(book.language())
                .publisher(book.publisher())
                .publishedYear(book.publishedYear())
                .bookGenre(book.bookGenre())
                .authors(book.authors())
                .owner(bookRepository
                        .getBookByID(book.id())
                        .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + book.id()))
                        .getOwner())
                .build();
    }


}
