package pl.orange.NextDoorBook.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.Address;
import pl.orange.NextDoorBook.address.AddressRepository;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;
import pl.orange.NextDoorBook.user.dto.UserAddDTO;
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

    public UserDTO addUser(UserAddDTO userToRegister) {
        if (userToRegister == null) {
            throw new IllegalStateException("Cant save null data user");
        }
        Optional<Address> addressToAdd = addressRepository.findAddressByFieldsWithoutId(userToRegister.address());
        if (addressToAdd.isEmpty()) {
            return userDTOMapper.map(userRepository.save(userDTOMapper.map(userToRegister)));
        }
        User userToAdd = userDTOMapper.map(userToRegister);
        userToAdd.setAddress(addressToAdd.get());

        return userDTOMapper.map(userRepository.save(userToAdd));
    }

    public UserDTO getUserById(Long id) {
        return userRepository.getUserById(id)
                .map(userDTOMapper::map)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public UserDTO deleteUserById(Long id) {
        return userRepository.getUserById(id)
                .map((user) -> {
                    deleteUnusedAddress(user.getAddress().getId());
                    userRepository.deleteUserById(id);
                    return userDTOMapper.map(user);
                })
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public UserDTO updateUser(UserAddDTO userAddDTO, Long userId) {
        if (userRepository.getUserById(userId).isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + "does not exist");
        }
        User userToUpdate = userRepository.getUserById(userId).get();
        userToUpdate.setLogin(userAddDTO.login());
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
