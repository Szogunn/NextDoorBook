package pl.orange.NextDoorBook.user.dto;

import lombok.Builder;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;
@Builder
public record UserDTO(
        Long id,
        String login,
        String email,
        AddressDTO addressDTO) {

}
