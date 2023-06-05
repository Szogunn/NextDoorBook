package pl.orange.NextDoorBook.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.orange.NextDoorBook.author.Author;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {



    List<Book> findByBookGenre(BookGenre bookGenre);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE lower(a.lastName) = lower(:lastName) ")
    List<Book> findBooksByAuthorsLastName(String lastName);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE lower(a.nationality) = lower(:nationality) ")
    List<Book> findBooksByAuthorsNationality(String nationality);

    Book findBookByIsbn(Long id);


}
