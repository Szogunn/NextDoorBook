package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;

    //TODO trzeba numer id zmienić tak aby był przekazywany przez Spring Security jako ID zalogowanego użytkownika
    @PostMapping(path = "{id}")
    public ResponseEntity<BookDTO> addBook(@RequestBody Book requestBook, @PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(bookService.addBook(requestBook,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping(path = "/{bookGenre}")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable BookGenre bookGenre) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByGenre(bookGenre));
    }
    @GetMapping(path = "")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity
                .status(200)
                .body(bookService.getAllBooks());
    }

    @PatchMapping(path = "")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO requestBook) {
        return ResponseEntity
                .status(200)
                .body(bookService.updateBook(requestBook));
    }
}

