package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookDTOMapper bookDTOMapper;

    public BookDTO addBook(Book book, Long id) {
        Book bookToAdd = bookRepository.addBook(book, id);
        return bookDTOMapper.apply(bookToAdd);
    }

    public void deleteBook(Long id) {
        bookRepository
                .getBookByID(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + id + " does not exist"));

        bookRepository.deleteBookByID(id);
    }

    public List<BookDTO> getBooksByGenre(BookGenre bookGenre) {
        return bookRepository
                .getBooksByGenre(bookGenre)
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks() {

        return bookRepository.getAllBooks()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }


    public BookDTO updateBook(BookDTO book) {
        bookRepository
                .getBookByID(bookDTOMapper.apply(book).getId())
                .orElseThrow(() ->
                        new BookNotFoundException
                                ("Books with id " + book.id() + " does not exist"));

        Book result = bookRepository.updateBook(bookDTOMapper.apply(book));

        authorRepository.deleteAuthorsByIDList(authorRepository
                .checkIfAuthorsAreInUse()
                .stream()
                .mapToLong(Author::getId)
                .boxed()
                .collect(Collectors.toSet()));


        return bookDTOMapper.apply(result);
    }
}
