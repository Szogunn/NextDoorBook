package pl.orange.NextDoorBook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.user.dto.UserAddDTO;
import pl.orange.NextDoorBook.user.dto.UserDTO;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(userService.getUserById(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserAddDTO userToUpdate, @PathVariable Long userId) {
        return ResponseEntity
                .status(200)
                .body(userService.updateUser(userToUpdate,userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(userService.deleteUserById(id));
    }

}
