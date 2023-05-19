package pl.orange.NextDoorBook.address;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressRepository {

    private final IAddressRepository addressRepository;

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    public void getAddressById(Long id) {
        addressRepository.findById(id);
    }

    public void updateAddress(Long id, String cityName, String street, int numberHouse, int zipCode, String district) {
        addressRepository.updateAddress(id,cityName,street,numberHouse,zipCode,district);
    }
}
