package pl.orange.NextDoorBook.comment;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "UPDATE Comment SET message = :message" +
            ",spoilerAlert =:spoilerAlert" +
            ",book=:book" +
            ",user=:user" +
            " WHERE id =:id")
    @Modifying
    @Transactional
    void updateComment(@Param("id") Long id,
                       @Param("message") String message,
                       @Param("spoilerAlert") boolean spoilerAlert,
                       @Param("book") Book book,
                       @Param("user") User user);

    Optional<Set<Comment>> findCommentsByBookId(Long id);
}
