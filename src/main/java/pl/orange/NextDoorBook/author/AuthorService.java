package pl.orange.NextDoorBook.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public ResponseEntity<?> addAuthor(Author author) {
        if (author == null) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        authorRepository.addAuthor(author);
        return ResponseEntity
                .status(201)
                .build();
    }

    public ResponseEntity<?> deleteAuthorByID(Long id) {
        Optional<Author> toDelete = authorRepository.getAuthorByID(id);
        if (toDelete.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        authorRepository.deleteAuthorByID(toDelete.get().getId());
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<Author> getAuthorByID(Long id) {
        return authorRepository.getAuthorByID(id).map(value -> ResponseEntity
                        .status(200)
                        .body(value))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .build());
    }

    public ResponseEntity<?> updateAuthor(Long id, Author author) {
        Optional<Author> toUpdate = authorRepository.getAuthorByID(id);
        if (toUpdate.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        authorRepository.updateAuthor(id, author.getFirstName(), author.getLastName(), author.getNationality());
        return ResponseEntity
                .status(200)
                .build();
    }
}
