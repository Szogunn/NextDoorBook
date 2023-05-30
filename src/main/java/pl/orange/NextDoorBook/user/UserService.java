package pl.orange.NextDoorBook.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.Address;
import pl.orange.NextDoorBook.address.AddressRepository;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;
import pl.orange.NextDoorBook.address.exception.AddressNotFoundException;
import pl.orange.NextDoorBook.user.dto.UserDTO;
import pl.orange.NextDoorBook.user.dto.UserDTOMapper;
import pl.orange.NextDoorBook.user.dto.UserAddDTO;
import pl.orange.NextDoorBook.user.exceptions.UserNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserDTOMapper userDTOMapper;
    private final AddressDTOMapper addressDTOMapper;

    public UserDTO addUser(UserAddDTO userToRegister) {
        if (userToRegister == null) {
            throw new IllegalStateException("Cant save null data user");
        }
        if (userToRegister.address().id() == null) {
            return userDTOMapper.map(userRepository.save(userDTOMapper.map(userToRegister)));
        }
        User userToAdd = userDTOMapper.map(userToRegister);
        userToAdd.setAddress(
                addressRepository.getAddressById(userToRegister.address().id())
                        .orElseThrow(() ->
                                new AddressNotFoundException("Address with id " + userToRegister.address().id() + "does not exits."))
        );

        return userDTOMapper.map(userRepository.save(userToAdd));
    }

    public UserDTO getUserById(Long id) {
        return userRepository.getUserById(id)
                .map(userDTOMapper::map)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public void deleteUserById(Long id) {
        userRepository.getUserById(id)
                .ifPresentOrElse(
                        (user) -> {
                            user.setPassword(null);
                            user.setEmail(null);
                            addressRepository.deleteAddressById(user.getAddress().getId());
                            user.setAddress(null);
                            userRepository.save(user);
                        },
                        () -> {
                            throw new UserNotFoundException("User with id " + id + " does not exist");
                        });
    }

    public UserDTO updateUser(UserDTO userDTO) {
        if (userRepository.getUserById(userDTO.id()).isEmpty()) {
            throw new UserNotFoundException("User with id " + userDTO.id() + "does not exist");
        }
        User userToUpdate = userRepository.getUserById(userDTO.id()).get();
        userToUpdate.setLogin(userDTO.login());
        userToUpdate.setEmail(userDTO.email());

        deleteUnusedAddress(userDTO, userToUpdate);

        Address addressToUpdate = addressDTOMapper.apply(userDTO.address());
        addressRepository.addAddress(addressToUpdate);
        userToUpdate.setAddress(addressToUpdate);

        return userDTOMapper.map(userRepository.save(userToUpdate));
    }

    private void deleteUnusedAddress(UserDTO userDTO, User userToUpdate) {
        if (!userToUpdate.getAddress().getId().equals(userDTO.address().id())) {
            if (userRepository.getUsersByAddressId(userToUpdate.getAddress().getId()).size() <= 1) {
                addressRepository.deleteAddressById(userToUpdate.getAddress().getId());
            }
        }
    }

}
