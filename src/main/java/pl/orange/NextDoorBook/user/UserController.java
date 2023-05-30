package pl.orange.NextDoorBook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.user.dto.UserDTO;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userToAdd) {
        return ResponseEntity
                .status(200)
                .body(userService.addUser(userToAdd));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(userService.getUserById(id));
    }


}
