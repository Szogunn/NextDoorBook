package pl.orange.NextDoorBook.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;
    private final ObjectMapper objectMapper;


    //TODO trzeba numer id zmienić tak aby był przekazywany przez Spring Security jako ID zalogowanego użytkownika
    @PostMapping(path = "{id}")
    public ResponseEntity<Book> addBook(@RequestBody Book book, @PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(bookService.addBook(book, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status(200)
                .build();
    }
    @GetMapping(path = "/{bookGenre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable BookGenre bookGenre) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByGenre(bookGenre));
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Map<Object,Object> fields){

        return ResponseEntity
                .status(200)
                .body(bookService.updateBook(id, fields));
    }

    }

