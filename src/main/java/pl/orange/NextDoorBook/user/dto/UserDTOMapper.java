package pl.orange.NextDoorBook.user.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;
import pl.orange.NextDoorBook.user.User;

@Service
@RequiredArgsConstructor
public class UserDTOMapper {

    private final AddressDTOMapper addressDTOMapper;

    public UserDTO map(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .address(addressDTOMapper.apply(user.getAddress()))
                .build();
    }

    public User map(UserDTO userDTO){
        return User.builder()
                .id(userDTO.id())
                .login(userDTO.login())
                .email(userDTO.email())
                .address(addressDTOMapper.apply(userDTO.address()))
                .build();
    }
}
