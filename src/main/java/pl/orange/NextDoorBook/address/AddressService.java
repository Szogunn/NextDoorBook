package pl.orange.NextDoorBook.address;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.DTO.AddressDTO;
import pl.orange.NextDoorBook.address.DTO.AddressDTOMapper;
import pl.orange.NextDoorBook.address.exception.AddressNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDTOMapper addressDTOMapper;

    public AddressDTO addAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new IllegalStateException("Cant save null data address");
        }
        return addressDTOMapper.mapAddressDTO(
                addressRepository.addAddress(
                        addressDTOMapper.mapAddressDTO(addressDTO)));
    }

    public AddressDTO deleteAddressById(Long id) {
        return addressRepository.getAddressById(id)
                .map(addressDTOMapper::mapAddressDTO)
                .orElseThrow(() ->
                        new AddressNotFoundException("Address with id " + id + " does not exist"));
    }

    public AddressDTO getAddressById(Long id) {
        return addressRepository.getAddressById(id)
                .map(addressDTOMapper::mapAddressDTO)
                .orElseThrow(() ->
                        new AddressNotFoundException("Address with id " + id + " does not exist"));
    }

    public AddressDTO updateAddress(AddressDTO addressDTO) {
        if (addressRepository.getAddressById(addressDTO.id()).isEmpty()) {
            throw new AddressNotFoundException("Address with id " + addressDTO.id() + " does not exist");
        }
        Address addressToUpdate = addressRepository.getAddressById(addressDTO.id()).get();
        addressToUpdate.setCityName(addressDTO.cityName());
        addressToUpdate.setStreet(addressDTO.street());
        addressToUpdate.setZipCode(addressDTO.zipCode());
        addressToUpdate.setNumberHouse(addressDTO.numberHouse());
        addressToUpdate.setDistrict(addressDTO.district());
        return addressDTOMapper.mapAddressDTO(addressRepository.save(addressToUpdate));
    }

}
