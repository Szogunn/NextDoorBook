package pl.orange.NextDoorBook.security.payloads;

import pl.orange.NextDoorBook.role.ERole;

import java.util.List;

public record UserInfoResponse(
        Long id,
        String username,
        String email,
        List <String> roles
) {
}
