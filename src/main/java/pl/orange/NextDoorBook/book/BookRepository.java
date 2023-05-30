package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.UserRepostiory;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final IBookRepository iBookRepository;
    private final UserRepostiory userRepostiory;


    public Book addBook(Book book, Long id) {
        log.info("[HIBERNATE] Checking if user exist in database");
        User userById = userRepostiory.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        book.setOwner(userById);

        log.info("[HIBERNATE] Saving book to database from addBook method");
        Book result = iBookRepository.save(book);
        log.info("Book authors to save: " + book.getAuthors());
        return result;
    }

    public void deleteBookByID(Long id) {
        log.info("[HIBERNATE] deleting from database book with id: " +id);
        iBookRepository.deleteById(id);
    }

    public Optional<Book> getBookByID(Long id) {
        log.info("[HIBERNATE] looking in database for book with id: " + id);
        return iBookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        log.info("[HIBERNATE] looking in database for all books existing in db");
        List<Book> all = iBookRepository.findAll();

        for (Book book : all) {
            log.info("Authors from findAll method: " + book.getAuthors());
        }

        return all;
    }


    public Book updateBook(Book book) {
        log.info("[HIBERNATE] saving book: " + book  + " into database");
        return iBookRepository.save(book);
    }


    public List<Book> getBooksByGenre(BookGenre bookGenre) {
        log.info("[HIBERNATE] looking in database for all books with genre: " + bookGenre);
        return iBookRepository.findByBookGenre(bookGenre);
    }


}
