package pl.orange.NextDoorBook.user.dto;

import lombok.Builder;
import pl.orange.NextDoorBook.address.DTO.AddressAddDTO;
import pl.orange.NextDoorBook.role.Role;

import java.util.Set;

public record UserAuthDTO(
        String login,
        String password,
        String email,
        Set<Role> roles,
        AddressAddDTO address
) {
}
