package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
