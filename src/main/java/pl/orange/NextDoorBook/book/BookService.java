package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ResponseEntity<Book> addBook(Book book){
        if (book == null){
            System.out.println("cos");
            return ResponseEntity
                    .status(404)
                    .build();

        }
        bookRepository.addBook(book);
        return ResponseEntity
                .status(201)
                .build();
    }

}
