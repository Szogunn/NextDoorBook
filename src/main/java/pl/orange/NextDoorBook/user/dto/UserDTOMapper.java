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
                .login(user.getUsername())
                .email(user.getEmail())
                .address(addressDTOMapper.mapAddressDTO(user.getAddress()))
                .build();
    }

    public User map(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.id())
                .username(userDTO.login())
                .email(userDTO.email())
                .address(addressDTOMapper.mapAddressDTO(userDTO.address()))
                .build();
    }

    public User map(UserAddDTO userAddDTO) {
        return User.builder()
                .password(userAddDTO.password())
                .username(userAddDTO.login())
                .email(userAddDTO.email())
                .address(addressDTOMapper.mapAddressAddDTO(userAddDTO.address()))
                .build();
    }

    public UserAuthDTO userToUserAuthDTO(User user) {
        return new UserAuthDTO(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles(),
                addressDTOMapper.mapAddressAddDTO(user.getAddress()));
    }
    public User userAuthDTOToUser(UserAuthDTO user) {
        return User.builder()
                .password(user.password())
                .username(user.login())
                .email(user.email())
                .roles(user.roles())
                .address(addressDTOMapper.mapAddressAddDTO(user.address()))
                .build();
    }


}
