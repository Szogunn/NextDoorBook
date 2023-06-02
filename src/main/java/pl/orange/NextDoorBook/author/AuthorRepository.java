package pl.orange.NextDoorBook.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final IAuthorRepository iAuthorRepository;

    public Author addAuthor(Author author) {
        return iAuthorRepository.save(author);
    }

    public void deleteAuthorByID(Long id) {
        iAuthorRepository.deleteById(id);
    }

    public Optional<Author> getAuthorByID(Long id) {
        return iAuthorRepository.findById(id);
    }

    public Optional<Author> getAuthor(Author author) {
        return iAuthorRepository
                .findByFirstNameAndLastNameAndNationality
                        (author.getFirstName()
                                , author.getLastName()
                                , author.getNationality());
    }

    public Author updateAuthor(Author authorToUpdate) {
        return iAuthorRepository.save(authorToUpdate);
    }

    public Set<Author> checkIfAuthorsAreInUse() {
        return iAuthorRepository.checkIfAuthorsAreInUse();
    }

    public void deleteAuthorsByIDList(Set<Long> Id) {
        iAuthorRepository.deleteByIdIn(Id);
    }

}
