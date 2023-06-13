package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.exception.AddressNotFoundException;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.author.dto.AuthorAddDTO;
import pl.orange.NextDoorBook.author.dto.AuthorDTOMapper;
import pl.orange.NextDoorBook.book.dto.BookAddDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.dto.BookInfoDTO;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.comment.CommentRepository;
import pl.orange.NextDoorBook.comment.dto.CommentDTOMapper;
import pl.orange.NextDoorBook.exchange.ExchangeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ExchangeRepository exchangeRepository;
    private final CommentRepository commentRepository;
    private final BookDTOMapper bookDTOMapper;
    private final AuthorDTOMapper authorDTOMapper;
    private final CommentDTOMapper commentDTOMapper;


    public BookAddDTO addBook(BookAddDTO bookAddDTO, Long userId) {
        Set<AuthorAddDTO> authorsToAdd = new HashSet<>(bookAddDTO.authors());
        bookAddDTO.authors().clear();
        Book bookToAdd = bookDTOMapper.BookAddDTOToBookMap(bookAddDTO);
        Book addedBook = bookRepository.addBook(bookToAdd, userId);

        for (AuthorAddDTO authorToAdd : authorsToAdd) {
            if (authorToAdd.id() != null) {
                authorRepository.getAuthorByID(authorToAdd.id())
                        .map(author -> {
                            addedBook.addAuthor(author);
                            return author;
                        })
                        .orElseThrow(() ->
                                new AddressNotFoundException("Author with id " + authorToAdd.id() + " does not exist"));
            } else {
                addedBook.addAuthor(authorDTOMapper.authorAddDTOToAuthorMap(authorToAdd));
            }
        }
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

    public List<BookAddDTO> getBooksByGenre(BookGenre bookGenre) {

        List<Book> books = bookRepository.getBooksByGenre(bookGenre);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Books from category " + bookGenre + " doesn't exist.");
        }
        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
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
    public BookAddDTO getBookByTitle(String title){
        return bookRepository.getBookByTitle(title)
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with title " + title + " doesn't exist."));
    }
    public List<BookAddDTO> getBooksByLanguage(String language){
        List<Book> books = bookRepository.getBooksByLanguage(language);

        if (books.isEmpty()) {
            throw new BookNotFoundException("There is no book in " + language);
        }

        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }
    public List<BookAddDTO>getBooksByPublisher(String publisher){
        List<Book> books = bookRepository.getBooksByPublisher(publisher);

        if (books.isEmpty()) {
            throw new BookNotFoundException("There is no book from " + publisher);
        }

        return books.stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }
    public Set<BookAddDTO>getBooksByCommentRateAverage(Double rateDouble){
        Set<Book> books = bookRepository.getBooksByCommentRateAverage(rateDouble);
        if(books.isEmpty()){
            throw new BookNotFoundException("Books with average rate "+rateDouble+" doesn't exist");
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

    public BookInfoDTO getBookInfo(Long bookId) {
        Book book = bookRepository.getBookByID(bookId)
                .orElseThrow(() ->
                        new BookNotFoundException("Books with id " + bookId + " does not exist"));
        return new BookInfoDTO(
                bookDTOMapper.BookToBookDTOMap(book),
                exchangeRepository.checkBookAvailability(bookId),
                commentRepository.averageBookRate(book),
                null,
                null,
                commentRepository.getCommentsByBookID(bookId)
                        .stream()
                        .map(commentDTOMapper::commentMapToDTO)
                        .collect(Collectors.toSet()));
    }
}
