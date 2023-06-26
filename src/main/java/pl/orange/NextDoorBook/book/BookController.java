package pl.orange.NextDoorBook.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.book.dto.BookAddDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookInfoDTO;
import pl.orange.NextDoorBook.security.payloads.MessageResponse;
import pl.orange.NextDoorBook.user.User;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookService bookService;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookAddDTO> addBook(@RequestBody BookAddDTO requestBook
            , UsernamePasswordAuthenticationToken user) {
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        return ResponseEntity
                .status(200)
                .body(bookService.addBook(requestBook, userFromObjectMapper.getId()));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteBook(@PathVariable Long id, UsernamePasswordAuthenticationToken user) {
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);

        if (!bookService.isBookOwnedByUser(id,userFromObjectMapper)){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Book has another owner"));
        }

        bookService.deleteBook(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping(path = "/{bookGenre}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable BookGenre bookGenre) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByGenre(bookGenre));
    }

    @GetMapping(path = "/authors/lastName/{lastName}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookAddDTO>> getBooksByAuthorLastName(@PathVariable String lastName) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByAuthorsLastName(lastName));
    }

    @GetMapping(path = "/authors/nationality/{nationality}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookAddDTO>> getBooksByAuthorNationality(@PathVariable String nationality) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByAuthorsNationality(nationality));
    }

    @GetMapping(path = "/isbn/{isbn}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookAddDTO> getBookByAuthorISBN(@PathVariable Long isbn) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBookByISBN(isbn));
    }

    @GetMapping(path = "/title/{title}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookAddDTO> getBookByTitle(@PathVariable String title) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBookByTitle(title));
    }

    @GetMapping(path = "/language/{language}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookAddDTO>> getBooksByLanguage(@PathVariable String language) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByLanguage(language));
    }

    @GetMapping(path = "/publisher/{publisher}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookAddDTO>> getBooksByPublisher(@PathVariable String publisher) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByPublisher(publisher));
    }

    @GetMapping(path = "/averageRate/{rateDouble}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<BookAddDTO>> getBooksByCommentRateAverage(@PathVariable Double rateDouble) {
        return ResponseEntity
                .status(200)
                .body(bookService.getBooksByCommentRateAverage(rateDouble));
    }

    @GetMapping(path = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity
                .status(200)
                .body(bookService.getAllBooks());
    }

    @GetMapping(path = "/user")
    public ResponseEntity<List<BookDTO>> findBooksByOwnerId(UsernamePasswordAuthenticationToken user){
        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        return ResponseEntity
                .status(200)
                .body(bookService.findBooksByOwnerId(userFromObjectMapper.getId()));
    }

    @PatchMapping(path = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateBook(@RequestBody BookDTO requestBook
            , UsernamePasswordAuthenticationToken user) {

        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);
        if (!bookService.isBookOwnedByUser(requestBook.id(), userFromObjectMapper)){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Book has another owner"));
        }
        return ResponseEntity
                .status(200)
                .body(bookService.updateBook(requestBook));
    }

    @GetMapping("info/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookInfoDTO> getBookInfo(@PathVariable Long bookId) {

        return ResponseEntity
                .status(200)
                .body(bookService.getBookInfo(bookId));
    }
}

