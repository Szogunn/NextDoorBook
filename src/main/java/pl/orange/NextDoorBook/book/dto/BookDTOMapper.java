package pl.orange.NextDoorBook.book.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.dto.AuthorDTOMapper;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.user.dto.UserDTOMapper;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookDTOMapper {

    private final BookRepository bookRepository;
    private final AuthorDTOMapper authorDTOMapper;
    private final UserDTOMapper userDTOMapper;


    public BookDTO BookToBookDTOMap(Book book) {
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
                        .collect(Collectors.toSet()),
                userDTOMapper.map(book.getOwner())
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
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getNumPages(),
                book.getLanguage(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getBookGenre()
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
                .build();
    }


    public Book updateBookMapper(BookDTO bookDTO, Book book) {

        if (bookDTO.tittle() != null) {
            book.setTitle(bookDTO.tittle());
        }
        if (bookDTO.isbn() != 0) {
            book.setIsbn(bookDTO.isbn());
        }
        if (bookDTO.numPages() != 0) {
            book.setNumPages(bookDTO.numPages());
        }
        if (bookDTO.bookGenre() != null) {
            book.setBookGenre(bookDTO.bookGenre());
        }
        if (bookDTO.publishedYear() != null) {
            book.setPublishedYear(bookDTO.publishedYear());
        }
        if (bookDTO.publisher() != null) {
            book.setPublisher(bookDTO.publisher());
        }
        if (bookDTO.authors() != null) {
            book.setAuthors(bookDTO.authors()
                    .stream()
                    .map(authorDTOMapper::authorDTOToAuthorMap)
                    .collect(Collectors.toSet()));
        }
        return book;
    }
}
