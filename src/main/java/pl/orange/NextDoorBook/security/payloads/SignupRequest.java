package pl.orange.NextDoorBook.security.payloads;

import pl.orange.NextDoorBook.address.DTO.AddressAddDTO;

import java.util.List;
import java.util.Set;

public record SignupRequest(

        String username,
        String email,
        String password,
        AddressAddDTO address,
        Set<String> roles

) {
}
