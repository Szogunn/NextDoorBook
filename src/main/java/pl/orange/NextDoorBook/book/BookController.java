package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.book.dto.BookAddDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;

    //TODO trzeba numer id zmienić tak aby był przekazywany przez Spring Security jako ID zalogowanego użytkownika
    @PostMapping(path = "{id}")
    public ResponseEntity<BookAddDTO> addBook(@RequestBody BookAddDTO requestBook, @PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(bookService.addBook(requestBook, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping(path = "/{bookGenre}")
    public ResponseEntity<List<BookAddDTO>> getBooksByGenre(@PathVariable BookGenre bookGenre) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByGenre(bookGenre));
    }

    @GetMapping(path = "/authors/lastName/{lastName}")
    public ResponseEntity<List<BookAddDTO>> getBooksByAuthorLastName(@PathVariable String lastName) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByAuthorsLastName(lastName));
    }

    @GetMapping(path = "/authors/nationality/{nationality}")
    public ResponseEntity<List<BookAddDTO>> getBooksByAuthorNationality(@PathVariable String nationality) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByAuthorsNationality(nationality));
    }

    @GetMapping(path = "/isbn/{isbn}")
    public ResponseEntity<BookAddDTO> getBookByAuthorISBN(@PathVariable Long isbn) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBookByISBN(isbn));
    }

    @GetMapping(path = "/title/{title}")
    public ResponseEntity<BookAddDTO> getBookByTitle(@PathVariable String title) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBookByTitle(title));
    }

    @GetMapping(path = "/language/{language}")
    public ResponseEntity<List<BookAddDTO>> getBooksByLanguage(@PathVariable String language) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByLanguage(language));
    }
    @GetMapping(path = "/publisher/{publisher}")
    public ResponseEntity<List<BookAddDTO>> getBooksByPublisher(@PathVariable String publisher) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByPublisher(publisher));
    }
    @GetMapping(path="/averageRate/{rateDouble}")
    public ResponseEntity<Set<BookAddDTO>> getBooksByCommentRateAverage(@PathVariable Double rateDouble) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByCommentRateAverage(rateDouble));
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

