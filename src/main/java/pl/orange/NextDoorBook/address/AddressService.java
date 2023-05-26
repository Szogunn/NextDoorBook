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

    public void addAddress(Address address) {
        if (address == null) {
            throw new IllegalStateException("Cant save null data address");
        }
        addressRepository.addAddress(address);
    }

    public void deleteAddressById(Long id) {
        addressRepository.getAddressById(id)
                .ifPresentOrElse(
                        (address) -> addressRepository.deleteAddressById(id),
                        () -> {
                            throw new AddressNotFoundException("Address with id " + id + " does not exist");
                        });
    }

    public AddressDTO getAddressById(Long id) {
        return addressRepository.getAddressById(id)
                .map(addressDTOMapper)
                .orElseThrow(() ->
                        new AddressNotFoundException("Address with id " + id + " does not exist"));
    }

    public Address updateAddress(Address address) {
        if (addressRepository.getAddressById(address.getId()).isEmpty()) {
            throw new AddressNotFoundException("Address with id " + address.getId() + " does not exist");
        }
        Address addressToUpdate = addressRepository.getAddressById(address.getId()).get();
        addressToUpdate.setCityName(address.getCityName());
        addressToUpdate.setStreet(address.getStreet());
        addressToUpdate.setZipCode(address.getZipCode());
        addressToUpdate.setNumberHouse(address.getNumberHouse());
        addressToUpdate.setDistrict(address.getDistrict());

        return addressRepository.save(addressToUpdate);
    }

}
