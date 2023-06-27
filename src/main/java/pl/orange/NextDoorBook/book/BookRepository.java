package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepository {

    private final IBookRepository iBookRepository;
    private final UserRepository userRepository;


    public Book addBook(Book book, Long id) {
        log.info("[HIBERNATE] Checking if user exist in database");
        User userById = userRepository.getUserById(id)
                .filter(user -> !user.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        book.setOwner(userById);

        log.info("[HIBERNATE] Saving book to database from addBook method");
        Book result = iBookRepository.save(book);
        return result;
    }

    public Book saveBook(Book book) {
        return iBookRepository.save(book);
    }

    public void deleteBookByID(Long id) {
        log.info("[HIBERNATE] deleting from database book with id: " + id);
        iBookRepository.deleteById(id);
    }

    public Optional<Book> getBookByID(Long id) {
        log.info("[HIBERNATE] looking in database for book with id: " + id);
        Optional<Book> bookById = iBookRepository.findById(id);
        if (bookById.isPresent() && bookById.get().getOwner().isDeleted()) {
            bookById = Optional.empty();
        }
        return bookById;
    }

    public List<Book> getAllBooks() {
        log.info("[HIBERNATE] looking in database for all books existing in db");
        List<Book> all = iBookRepository.findAll();

        for (Book book : all) {
            log.info("Authors from findAll method: " + book.getAuthors());
        }

        return all
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toList());
    }


    public Book updateBook(Book book) {
        log.info("[HIBERNATE] saving book: " + book + " into database");
        return iBookRepository.save(book);
    }


    public List<Book> getBooksByGenre(BookGenre bookGenre) {
        log.info("[HIBERNATE] looking in database for all books with genre: " + bookGenre);
        return iBookRepository.findByBookGenre(bookGenre)
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toList());
    }


    public List<Book> getBooksByAuthorsLastName(String lastName) {
        return iBookRepository.findBooksByAuthorsLastName(lastName)
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthorsNationality(String nationality) {
        return iBookRepository.findBooksByAuthorsNationality(nationality)
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toList());
    }

    public Optional<Book> getBookByISBN(Long isbn) {
        Optional<Book> bookByIsbn = iBookRepository.findBookByIsbn(isbn);
        if (bookByIsbn.isPresent() && bookByIsbn.get().getOwner().isDeleted()) {
            bookByIsbn = Optional.empty();
        }
        return bookByIsbn;
    }

    public Optional<Book> getBookByTitle(String title) {
        Optional<Book> bookByTitle = iBookRepository.findBookByTitleIgnoreCase(title);
        if (bookByTitle.isPresent() && bookByTitle.get().getOwner().isDeleted()) {
            bookByTitle = Optional.empty();
        }
        return bookByTitle;
    }

    public List<Book> getBooksByLanguage(String language) {
        return iBookRepository.findBooksByLanguageIgnoreCase(language)
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByPublisher(String publisher) {
        return iBookRepository.findBooksByPublisherIgnoreCase(publisher)
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toList());
    }

    public Set<Book> getBooksByCommentRateAverage(Double rateDouble) {
        return iBookRepository.findBooksByCommentRateAverage(rateDouble)
                .stream()
                .filter(book -> !book.getOwner().isDeleted())
                .collect(Collectors.toSet());
    }

    public List<Book> findBooksByOwnerId(Long ownerId){
        return iBookRepository.findBooksByOwnerId(ownerId);
    }
}
