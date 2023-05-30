package pl.orange.NextDoorBook.user.dto;

import lombok.Builder;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;

@Builder
public record UserAddDTO(
        Long id,
        String login,
        char[] password,
        String email,
        AddressDTO address) {

}
