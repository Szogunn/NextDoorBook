package pl.orange.NextDoorBook.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void addAddress(Address address) {
        addressRepository.addAddress(address);
    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteAddressById(id);
    }

    public void getAddressById(Long id) {
        addressRepository.getAddressById(id);
    }

    public void updateAddress(Long id, String cityName, String street, int numberHouse, int zipCode, String district) {
        addressRepository.updateAddress(id,cityName,street,numberHouse,zipCode,district);
    }

}
