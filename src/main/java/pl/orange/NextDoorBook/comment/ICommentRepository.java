package pl.orange.NextDoorBook.comment;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface ICommentRepository extends JpaRepository<Comment, Long> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE Comment SET message = :message" +
            ",spoilerAlert =:spoilerAlert" +
            ",addTime =:addTime" +
            ",book=:book" +
            ",user=:user" +
            ",rate=:rate" +
            " WHERE id =:id")
    void updateComment(@Param("id") Long id,
                       @Param("message") String message,
                       @Param("spoilerAlert") boolean spoilerAlert,
                       @Param("addTime") LocalDateTime addTime,
                       @Param("book") Book book,
                       @Param("user") User user,
                       @Param("rate") int rate);

    Set<Comment> findCommentsByBookId(Long id);

    @Transactional
    @Query("SELECT AVG(c.rate) FROM Comment c WHERE c.book = :book")
    Double averageBookRate(@Param("book") Book book);
}
