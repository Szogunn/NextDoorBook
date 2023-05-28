package pl.orange.NextDoorBook.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final IAuthorRepository iAuthorRepository;

    public void addAuthor(Author author) {
        iAuthorRepository.save(author);
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

    public void updateAuthor(Long id, String firstName, String lastName, String nationality) {
        iAuthorRepository.updateAuthor(id, firstName, lastName, nationality);
    }

    public Set<Author> checkIfAuthorsAreInUse(){
        return iAuthorRepository.checkIfAuthorsAreInUse();
    }

    public void deleteAuthorsByIDList(Set<Long> Id){
        iAuthorRepository.deleteByIdIn(Id);
    }

}
