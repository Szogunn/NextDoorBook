package pl.orange.NextDoorBook.address.DTO;

import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.Address;

@Service
public class AddressDTOMapper {

    public AddressDTO mapAddressDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getCityName(),
                address.getStreet(),
                address.getNumberHouse(),
                address.getZipCode(),
                address.getDistrict()
        );
    }
    public Address mapAddressDTO(AddressDTO addressDTO) {
        return new Address(
                addressDTO.id(),
                addressDTO.cityName(),
                addressDTO.street(),
                addressDTO.numberHouse(),
                addressDTO.zipCode(),
                addressDTO.district()
        );
    }

    public AddressAddDTO mapAddressAddDTO(Address address) {
        return new AddressAddDTO(
                address.getCityName(),
                address.getStreet(),
                address.getNumberHouse(),
                address.getZipCode(),
                address.getDistrict()
        );
    }
    public Address mapAddressAddDTO(AddressAddDTO addressAddDTO) {
        return new Address(
                addressAddDTO.cityName(),
                addressAddDTO.street(),
                addressAddDTO.numberHouse(),
                addressAddDTO.zipCode(),
                addressAddDTO.district()
        );
    }


}
