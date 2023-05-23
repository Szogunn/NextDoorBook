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

    public ResponseEntity<Book> addBook(Book book, Long id){
        if (book == null){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        bookRepository.addBook(book, id);
        return ResponseEntity
                .status(201)
                .build();
    }

    public ResponseEntity<Book> deleteBook(Long id){
        Optional<Book> bookByID = bookRepository.getBookByID(id);

        if (bookByID.isPresent()){
            bookRepository.deleteBookByID(id);
            return ResponseEntity
                    .status(200)
                    .build();
        }
        return ResponseEntity
                .status(404)
                .build();
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
