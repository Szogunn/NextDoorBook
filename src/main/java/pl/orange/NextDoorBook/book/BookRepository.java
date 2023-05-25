package pl.orange.NextDoorBook.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.UserRepostiory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final IBookRepository iBookRepository;
    private final UserRepostiory userRepostiory;
    private final AuthorRepository authorRepository;

    public Book addBook(Book book, Long id) {
        User userById = userRepostiory.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        book.setOwner(userById);

//        //TODO continue implementation for authors
//        List<Author> authors = new ArrayList<>();
//        //pobieram listę autorów z JSONA
//        for (Author author : new ArrayList<>(book.getAuthors())) {
//            //Interuję po niej i szukam czy jest w bazie danych, jeśli jest to dodaję do listy authorów
//            authors.add(authorRepository.getAuthor(author).orElseThrow());
//        }

        return iBookRepository.save(book);
    }

    public void deleteBookByID(Long id) {
        iBookRepository.deleteById(id);
    }

    public Optional<Book> getBookByID(Long id) {
        return iBookRepository.findById(id);
    }

    public Book updateBook(Book book) {
        return iBookRepository.save(book);


//        iBookRepository.updateBook(id, book.getTittle(), book.getIsbn(),
//                book.getNumPages(), book.getLanguage(), book.getPublisher(),
//                book.getPublishedYear(), book.getBookGenre(),
//                book.getAuthors(), book.getOwner());
    }

    public Optional<List<Book>> getBooksByGenre(BookGenre bookGenre) {
        return iBookRepository.findBooksByBookGenre(bookGenre);
    }


}
