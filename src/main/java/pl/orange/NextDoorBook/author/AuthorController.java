package pl.orange.NextDoorBook.author;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.author.dto.AuthorAddDTO;
import pl.orange.NextDoorBook.author.dto.AuthorDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/books/{idBook}/authors")
    public ResponseEntity<AuthorAddDTO> addAuthor(@PathVariable Long idBook, @RequestBody AuthorAddDTO authorAddDTO) {
        return ResponseEntity
                .status(200)
                .body(authorService.addAuthor(idBook, authorAddDTO));
    }

    @DeleteMapping("/books/{bookId}/authors/{authorId}")
    public ResponseEntity<BookDTO> deleteAuthorByID(@PathVariable Long bookId, @PathVariable Long authorId) {
        return ResponseEntity
                .status(200)
                .body(authorService.deleteAuthorFromBook(bookId, authorId));
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthorByID(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(authorService.getAuthorByID(id));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity
                .status(200)
                .body(authorService.updateAuthor(id, authorDTO));
    }
}
