package pl.orange.NextDoorBook.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.Address;
import pl.orange.NextDoorBook.address.AddressRepository;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;
import pl.orange.NextDoorBook.user.dto.UserAddDTO;
import pl.orange.NextDoorBook.user.dto.UserAuthDTO;
import pl.orange.NextDoorBook.user.dto.UserDTO;
import pl.orange.NextDoorBook.user.dto.UserDTOMapper;
import pl.orange.NextDoorBook.user.exceptions.UserNotFoundException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserDTOMapper userDTOMapper;
    private final AddressDTOMapper addressDTOMapper;

    public UserAuthDTO addUser(UserAuthDTO userToRegister) {
        if (userToRegister == null) {
            throw new IllegalStateException("Cant save null data user");
        }
        Optional<Address> addressToAdd = addressRepository.findAddressByFieldsWithoutId(userToRegister.address());
        if (addressToAdd.isEmpty()) {
            return userDTOMapper.userToUserAuthDTO(userRepository.save(userDTOMapper.userAuthDTOToUser(userToRegister)));
        }
        User userToAdd = userDTOMapper.userAuthDTOToUser(userToRegister);
        userToAdd.setAddress(addressToAdd.get());

        return userDTOMapper.userToUserAuthDTO(userRepository.save(userToAdd));
    }

    public UserDTO getUserById(Long id) {
        return userRepository.getUserById(id)
                .filter(user -> !user.isDeleted())
                .map(userDTOMapper::map)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public UserDTO deleteUserById(Long id) {
        return userRepository.getUserById(id)
                .filter(user -> !user.isDeleted())
                .map((user) -> {
                    deleteUnusedAddress(user.getAddress().getId());
                    userRepository.deleteUserById(id);
                    return userDTOMapper.map(user);
                })
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public UserDTO updateUser(UserAddDTO userAddDTO, Long userId) {
        Optional<User> findUser = userRepository.getUserById(userId);
        if (findUser.isEmpty() || findUser.get().isDeleted() ) {
            throw new UserNotFoundException("User with id " + userId + "does not exist");
        }
        User userToUpdate = findUser.get();
        userToUpdate.setUsername(userAddDTO.login());

        userToUpdate.setPassword(userToUpdate.getPassword());
        userToUpdate.setEmail(userAddDTO.email());

        Optional<Address> addressInDB = addressRepository.findAddressByFieldsWithoutId(userAddDTO.address());
        if (addressInDB.isEmpty() || !userToUpdate.getAddress().getId().equals(addressInDB.get().getId())) {
            deleteUnusedAddress(userToUpdate.getAddress().getId());
        }
        return addressInDB
                .map(address -> {
                    userToUpdate.setAddress(address);
                    return userDTOMapper.map(userRepository.save(userToUpdate));
                })
                .orElseGet(() ->
                {
                    Address addressToAdd = addressRepository.addAddress(addressDTOMapper.mapAddressAddDTO(userAddDTO.address()));
                    userToUpdate.setAddress(addressToAdd);
                    return userDTOMapper.map(userRepository.save(userToUpdate));
                });

    }

    private void deleteUnusedAddress(Long addressId) {
        if (userRepository.getUsersByAddressId(addressId).size() <= 1) {
            addressRepository.deleteAddressById(addressId);
        }
    }

}
