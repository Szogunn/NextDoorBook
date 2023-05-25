package pl.orange.NextDoorBook.address;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDTOMapper addressDTOMapper;

    public void addAddress(AddressDTO addressDTO) {
        addressRepository.addAddress(addressDTOMapper.apply(addressDTO));
    }

    public boolean deleteAddressById(Long id) {
        if (addressRepository.getAddressById(id).isEmpty()){
            return false;
        }
        addressRepository.deleteAddressById(id);
        return true;
    }

    public Optional<AddressDTO> getAddressById(Long id) {
        return addressRepository.getAddressById(id).map(addressDTOMapper);
    }

    public boolean updateAddress(Long id, AddressDTO addressDTO) {
        if (addressRepository.getAddressById(id).isEmpty()){
            return false;
        }
        addressRepository.updateAddress(
                id,
                addressDTO.cityName(),
                addressDTO.street(),
                addressDTO.numberHouse(),
                addressDTO.zipCode(), addressDTO.district());
        return true;
    }

}
