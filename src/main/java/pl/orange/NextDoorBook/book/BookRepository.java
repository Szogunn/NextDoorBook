package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.User;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final IBookRepository iBookRepository;

    public void addBook(Book book) {
        iBookRepository.save(book);
    }

    public void deleteBookByID(Long id) {
        iBookRepository.deleteById(id);
    }

    public Optional<Book> getBookByID(Long id) {
        return iBookRepository.findById(id);
    }

    public void updateBook(Long id, String tittle, long isbn,
                           int pages, Set<Comment> comments,
                           Set<Author> authors, User owner) {
        iBookRepository.updateAddress(id, tittle, isbn, pages, comments, authors, owner);
    }


}
