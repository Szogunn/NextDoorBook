package pl.orange.NextDoorBook.author;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthorByID(@PathVariable Long id) {
        return authorService.deleteAuthorByID(id);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorByID(@PathVariable Long id) {
        return authorService.getAuthorByID(id);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }
}
