package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.user.User;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(Book book, Long id) {
        return bookRepository.addBook(book, id);
    }

    public void deleteBook(Long id) {
        bookRepository
                .getBookByID(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + id + " does not exist"));

        bookRepository.deleteBookByID(id);
    }

    public List<Book> getBooksByGenre(BookGenre bookGenre) {
        return bookRepository
                .getBooksByGenre(bookGenre)
                .orElseThrow(() ->
                        new BookNotFoundException
                                ("Books with Genre " + bookGenre.name() + " does not exist"));
    }


    public Book updateBook(Long id , Map<Object, Object> fields) {
        Book book = bookRepository
                .getBookByID(id)
                .orElseThrow(() ->
                        new BookNotFoundException
                                ("Books with id " + id + " does not exist"));

        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Book.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,book,value);
        });
        return bookRepository.updateBook(book);
    }
}
