package pl.orange.NextDoorBook.author;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

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


    @Query("SELECT a FROM Author a " +
            "WHERE a.firstName = :firstName " +
            "AND a.lastName = :lastName  " +
            "AND a.nationality =:nationality")
    Optional<Author> findByFirstNameAndLastNameAndNationality
            (@Param("firstName") String firstName
                    , @Param("lastName") String lastName
                    , @Param("nationality") String nationality);


    @Query(value = "SELECT * " +
            "FROM AUTHORS " +
            "WHERE ID NOT IN (SELECT AUTHOR_ID FROM BOOK_AUTHOR)", nativeQuery = true)
    @Transactional
    Set<Author> checkIfAuthorsAreInUse();

    void deleteByIdIn(Set<Long> Id);
}
