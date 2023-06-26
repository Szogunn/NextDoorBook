package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.dto.BookAddDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.dto.BookInfoDTO;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.comment.CommentRepository;
import pl.orange.NextDoorBook.comment.dto.CommentDTOMapper;
import pl.orange.NextDoorBook.exchange.ExchangeRepository;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTOMapper;
import pl.orange.NextDoorBook.user.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ExchangeRepository exchangeRepository;
    private final CommentRepository commentRepository;

    private final BookDTOMapper bookDTOMapper;
    private final CommentDTOMapper commentDTOMapper;
    private final ExchangeDTOMapper exchangeDTOMapper;


    public BookAddDTO addBook(BookAddDTO bookAddDTO, Long userId) {
        Book book = bookDTOMapper.BookAddDTOToBookMap(bookAddDTO);

        Book addedBook = bookRepository.addBook(book, userId);
        return bookDTOMapper
                .BookToBookAddDTOMap(addedBook);
    }

    public void deleteBook(Long id) {
        bookRepository
                .getBookByID(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + id + " does not exist"));

        bookRepository.deleteBookByID(id);
    }

    public List<BookDTO> getBooksByGenre(BookGenre bookGenre) {

        List<Book> books = bookRepository.getBooksByGenre(bookGenre);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Books from category " + bookGenre + " doesn't exist.");
        }
        return books.stream()
                .map(bookDTOMapper::BookToBookDTOMap)
                .collect(Collectors.toList());
    }

    public List<BookAddDTO> getBooksByAuthorsLastName(String lastName) {
        List<Book> books = bookRepository.getBooksByAuthorsLastName(lastName);

        if (books.isEmpty()) {
            throw new BookNotFoundException("Books with the author's  " + lastName + " doesn't exist.");
        }
        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public List<BookAddDTO> getBooksByAuthorsNationality(String nationality) {
        List<Book> books = bookRepository.getBooksByAuthorsNationality(nationality);

        if (books.isEmpty()) {
            throw new BookNotFoundException("Books with an author from  " + nationality + " doesn't exist.");
        }

        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public BookAddDTO getBookByISBN(Long isbn) {

        return bookRepository.getBookByISBN(isbn)
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with isbn " + isbn + " doesn't exist."));

    }

    public BookAddDTO getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title)
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with title " + title + " doesn't exist."));
    }

    public boolean isBookOwnedByUser(Long bookId, User user) {
        Book book = bookRepository.getBookByID(bookId)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + bookId + " doesn't exist."));
        if (book.getOwner().getId().equals(user.getId())) {
            return true; // Książka należy do użytkownika
        }
        return false; // Książka nie należy do użytkownika
    }

    public List<BookAddDTO> getBooksByLanguage(String language) {
        List<Book> books = bookRepository.getBooksByLanguage(language);

        if (books.isEmpty()) {
            throw new BookNotFoundException("There is no book in " + language);
        }

        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public List<BookAddDTO> getBooksByPublisher(String publisher) {
        List<Book> books = bookRepository.getBooksByPublisher(publisher);

        if (books.isEmpty()) {
            throw new BookNotFoundException("There is no book from " + publisher);
        }

        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public Set<BookAddDTO> getBooksByCommentRateAverage(Double rateDouble) {
        Set<Book> books = bookRepository.getBooksByCommentRateAverage(rateDouble);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Books with average rate " + rateDouble + " doesn't exist");
        }
        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toSet());
    }


    public List<BookDTO> getAllBooks() {

        return bookRepository.getAllBooks()
                .stream()
                .map(bookDTOMapper::BookToBookDTOMap)
                .collect(Collectors.toList());
    }


    public BookDTO updateBook(BookDTO bookDTO) {
        Book bookFromDB = bookRepository
                .getBookByID(bookDTOMapper.BookDTOToBookMap(bookDTO).getId())
                .orElseThrow(() -> new BookNotFoundException("Books with id " + bookDTO.id() + " does not exist"));


        Book result = bookRepository.updateBook(bookDTOMapper.updateBookMapper(bookDTO, bookFromDB));


        return bookDTOMapper.BookToBookDTOMap(result);
    }

    public BookInfoDTO getBookInfo(Long bookId) {
        Book book = bookRepository.getBookByID(bookId)
                .orElseThrow(() ->
                        new BookNotFoundException("Books with id " + bookId + " does not exist"));
        return new BookInfoDTO(
                bookDTOMapper.BookToBookDTOMap(book),
                exchangeRepository.checkBookAvailability(bookId),
                commentRepository.averageBookRate(book),
                exchangeRepository.getExchangesByBook(book).size(),
                exchangeRepository.getExchangesByBook(book)
                        .stream()
                        .map(exchangeDTOMapper::mapToDTO)
                        .collect(Collectors.toSet()),
                commentRepository.getCommentsByBookID(bookId)
                        .stream()
                        .map(commentDTOMapper::commentToCommentDTOMap)
                        .collect(Collectors.toSet()));
    }

    public List<BookDTO> findBooksByOwnerId(Long ownerId){
        return bookRepository.findBooksByOwnerId(ownerId)
                .stream()
                .map(bookDTOMapper::BookToBookDTOMap)
                .collect(Collectors.toList());
    }
}
