package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<List<Book>> getBooksByGenre(BookGenre bookGenre){
        return bookRepository.getBooksByGenre(bookGenre).map(value->ResponseEntity
                        .status(200)
                        .body(value))
                .orElseGet(()->ResponseEntity
                        .status(404)
                        .build());
    }

}
