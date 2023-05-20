package pl.orange.NextDoorBook.author;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.User;

import java.util.Set;

public interface IAuthorRepository extends JpaRepository<Author,Long> {

    @Query(value = "UPDATE Author SET firstName = :firstName" +
            ",lastName =:lastName" +
            ",nationality=:nationality" +
            " WHERE id =:id")
    @Modifying
    @Transactional
    void updateAuthor(@Param("id") Long id,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName,
                       @Param("nationality") String nationality);
}
