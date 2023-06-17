package pl.orange.NextDoorBook.user.dto;

import lombok.Builder;
import pl.orange.NextDoorBook.address.DTO.AddressAddDTO;

@Builder
public record UserAddDTO(
        String login,
        String password,
        String email,
        AddressAddDTO address) {

}
