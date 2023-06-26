package pl.orange.NextDoorBook.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IBookRepository extends JpaRepository<Book, Long> {



    List<Book> findByBookGenre(BookGenre bookGenre);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE lower(a.lastName) = lower(:lastName) ")
    List<Book> findBooksByAuthorsLastName(String lastName);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE lower(a.nationality) = lower(:nationality) ")
    List<Book> findBooksByAuthorsNationality(String nationality);

    Optional<Book> findBookByIsbn(Long id);

    Optional<Book> findBookByTitleIgnoreCase(String title);

    List<Book> findBooksByLanguageIgnoreCase(String language);
    List<Book> findBooksByPublisherIgnoreCase(String publisher);

    @Query("SELECT b FROM Book b JOIN b.comments c WHERE b.id = c.book.id GROUP BY b.id HAVING AVG(c.rate) = :rateDouble")
    Set<Book> findBooksByCommentRateAverage(Double rateDouble);

    List<Book> findBooksByOwnerId(Long ownerId);
}
