package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;

    //TODO trzeba numer id zmienić tak aby był przekazywany przez Spring Security jako ID zalogowanego użytkownika
    @PostMapping(path = "{id}")
    public ResponseEntity<Book> addBook
    (@RequestBody Book book, @PathVariable Long id) {
        bookService.addBook(book, id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status(200)
                .build();
    }
    @GetMapping(path = "/{bookGenre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable BookGenre bookGenre){
        return bookService.getBooksByGenre(bookGenre);}
}

