package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.book.dto.BookAddDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookDTOMapper bookDTOMapper;


    public BookAddDTO addBook(BookAddDTO book, Long id) {
        Book bookToAdd = bookDTOMapper.BookAddDTOToBookMap(book);
        return bookDTOMapper
                .BookToBookAddDTOMap(bookRepository.addBook(bookToAdd, id));
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
                .map(bookDTOMapper::BookToBookDTOMap)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks() {

        return bookRepository.getAllBooks()
                .stream()
                .map(bookDTOMapper::BookToBookDTOMap)
                .collect(Collectors.toList());
    }


    public BookDTO updateBook(BookDTO book) {
        bookRepository
                .getBookByID(bookDTOMapper.BookDTOToBookMap(book).getId())
                .orElseThrow(() ->
                        new BookNotFoundException
                                ("Books with id " + book.id() + " does not exist"));

        Book result = bookRepository.updateBook(bookDTOMapper.BookDTOToBookMap(book));

        authorRepository.deleteAuthorsByIDList(authorRepository
                .checkIfAuthorsAreInUse()
                .stream()
                .mapToLong(Author::getId)
                .boxed()
                .collect(Collectors.toSet()));


        return bookDTOMapper.BookToBookDTOMap(result);
    }
}
