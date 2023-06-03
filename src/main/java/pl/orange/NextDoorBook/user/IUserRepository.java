package pl.orange.NextDoorBook.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.orange.NextDoorBook.address.Address;
import pl.orange.NextDoorBook.comment.Comment;

import java.util.List;
import java.util.Set;

public interface IUserRepository extends JpaRepository<User, Long> {


    @Query(value="UPDATE User SET id=:id,login=:login,password=:password,email=:email,address=:address WHERE id=:id")
    @Modifying
    @Transactional
    void updateUser(@Param("id")Long id,
                    @Param("login")String login,
                    @Param("password")char[] password,
                    @Param("email")String email,
                    @Param("address")Address address);

    List<User> findUserByAddressId(Long id);
}
