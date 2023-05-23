package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.List;


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
    }
    public List<Book> getBooksByGenre(BookGenre bookGenre){
        return bookRepository
                .getBooksByGenre(bookGenre)
                .orElseThrow(() ->
                        new BookNotFoundException
                                ("Books with Genre " + bookGenre.name() + " does not exist"));

//                .map(value->ResponseEntity
//                        .status(200)
//                        .body(value))
//                .orElseGet(()->ResponseEntity
//                        .status(404)
//                        .build());
    }

}
