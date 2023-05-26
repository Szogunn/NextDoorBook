package pl.orange.NextDoorBook.user.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;
import pl.orange.NextDoorBook.user.User;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserDTOMapper implements Function<User,UserDTO> {

    private final AddressDTOMapper addressDTOMapper;
    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .addressDTO(addressDTOMapper.apply(user.getAddress()))
                .build();
    }

    public User apply(UserDTO userDTO){
        return User.builder()
                .id(userDTO.id())
                .login(userDTO.login())
                .email(userDTO.email())
                .address(addressDTOMapper.apply(userDTO.addressDTO()))
                .build();
    }
}
