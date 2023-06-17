package pl.orange.NextDoorBook.security.payloads;

import java.util.List;
import java.util.Set;

public record SignupRequest(

        String username,
        String email,
        String password,
        Set<String> roles

) {
}
