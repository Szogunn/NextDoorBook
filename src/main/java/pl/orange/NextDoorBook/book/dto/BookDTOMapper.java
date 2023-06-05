package pl.orange.NextDoorBook.book.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.dto.AuthorDTOMapper;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookDTOMapper {

    private final BookRepository bookRepository;
    private final AuthorDTOMapper authorDTOMapper;


    public BookDTO BookToBookDTOMap(Book book) {
        log.info("Book provide to map into BookDTO: " + book);
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getNumPages(),
                book.getLanguage(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getBookGenre(),
                bookRepository.getBookByID(book.getId())
                        .orElseThrow(() -> new BookNotFoundException(""))
                        .getAuthors()
                        .stream()
                        .map(authorDTOMapper::authorTOAuthorDTOMap)
                        .collect(Collectors.toSet())
        );
    }

    public Book BookDTOToBookMap(BookDTO book) {
        return Book.builder()
                .id(book.id())
                .title(book.tittle())
                .isbn(book.isbn())
                .numPages(book.numPages())
                .language(book.language())
                .publisher(book.publisher())
                .publishedYear(book.publishedYear())
                .bookGenre(book.bookGenre())
                .authors(book.authors()
                        .stream()
                        .map(authorDTOMapper::authorDTOToAuthorMap)
                        .collect(Collectors.toSet()))
                .owner(bookRepository
                        .getBookByID(book.id())
                        .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + book.id()))
                        .getOwner())
                .build();
    }

    public BookAddDTO BookToBookAddDTOMap(Book book) {
        return new BookAddDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getNumPages(),
                book.getLanguage(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getBookGenre(),
                bookRepository.getBookByID(book.getId())
                        .orElseThrow(() -> new BookNotFoundException(""))
                        .getAuthors()
                        .stream()
                        .map(authorDTOMapper::authorTOAuthorAddDTOMap)
                        .collect(Collectors.toSet())
        );
    }

    public Book BookAddDTOToBookMap(BookAddDTO book) {
        return Book.builder()
                .title(book.title())
                .isbn(book.isbn())
                .numPages(book.numPages())
                .language(book.language())
                .publisher(book.publisher())
                .publishedYear(book.publishedYear())
                .bookGenre(book.bookGenre())
                .authors(book.authors()
                        .stream()
                        .map(authorDTOMapper::authorAddDTOToAuthorMap)
                        .collect(Collectors.toSet()))
                //TODO implement also .owner() method which provides a user from logged account
                .build();
    }


}
