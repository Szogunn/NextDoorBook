package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ResponseEntity<Book> addBook(Book book){
        if (book == null){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        bookRepository.addBook(book);
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

}
