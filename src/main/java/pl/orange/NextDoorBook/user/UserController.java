package pl.orange.NextDoorBook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepostiory userRepostiory;

    @PostMapping(path = "")
    public void addUser(@RequestBody User user) {
        userRepostiory.addUser(user);
    }

}
