package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.UserRepostiory;

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final IBookRepository iBookRepository;
    private final UserRepostiory userRepostiory;
    private final AuthorRepository authorRepository;


    public Book addBook(Book book, Long id) {
        User userById = userRepostiory.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        book.setOwner(userById);

        return iBookRepository.save(book);
    }

    public void deleteBookByID(Long id) {
        iBookRepository.deleteById(id);
    }

    public Optional<Book> getBookByID(Long id) {
        return iBookRepository.findById(id);
    }
    public List<Book> getAllBooks() {
        return iBookRepository.findAll();
    }


    public Book updateBook(Book book) {
        return iBookRepository.save(book);
    }


    public List<Book> getBooksByGenre(BookGenre bookGenre) {
        return iBookRepository.findByBookGenre(bookGenre);
    }


}
