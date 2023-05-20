package pl.orange.NextDoorBook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.address.Address;
import pl.orange.NextDoorBook.comment.Comment;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserRepostiory {
    private final IUserRepository iUserRepository;

    public void addUser(User user){
        iUserRepository.save(user);
    }
    public void deleteUserById(Long id){
        iUserRepository.deleteById(id);
    }
    public void getUserById(Long id){
        iUserRepository.findById(id);
    }
    public void updateUser(Long id, String login, char[] password, String email, Address address, Set<Comment> comments){
        iUserRepository.updateUser(id,login,password,email,address,comments);
    }
}
