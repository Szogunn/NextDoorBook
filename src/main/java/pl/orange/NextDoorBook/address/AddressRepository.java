package pl.orange.NextDoorBook.address;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.orange.NextDoorBook.address.DTO.AddressAddDTO;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepository {

    private final IAddressRepository addressRepository;

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }
    public Address save(Address address){
        return addressRepository.save(address);
    }
    public Optional<Address> findAddressByFieldsWithoutId(AddressAddDTO addressAddDTO){
        return addressRepository.findAddressByCityNameAndStreetAndNumberHouseAndZipCodeAndDistrict(
                addressAddDTO.cityName(),
                addressAddDTO.street(),
                addressAddDTO.numberHouse(),
                addressAddDTO.zipCode(),
                addressAddDTO.district()
        );
    }

    public void updateAddress(Long id, String cityName, String street, int numberHouse, int zipCode, String district) {
        addressRepository.updateAddress(id, cityName, street, numberHouse, zipCode, district);
    }
}
