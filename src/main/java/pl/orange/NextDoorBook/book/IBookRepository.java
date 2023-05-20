package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.User;

import java.util.Set;

public interface IBookRepository extends JpaRepository<Book, Long> {


    @Query(value = "UPDATE Book SET tittle = :tittle" +
            ",isbn =:isbn" +
            ",numPages=:numPages" +
            ",bookGenre=:bookGenre" +
            ",authors=:authors" +
            ",owner=:owner  WHERE id =:id")
    @Modifying
    @Transactional
    void updateBook(@Param("id") Long id,
                       @Param("tittle") String tittle,
                       @Param("isbn") long isbn,
                       @Param("numPages") int numPages,
                       @Param("authors") Set<Author> authors,
                       @Param("owner") User owner);
}
