package pl.orange.NextDoorBook.address.DTO;

import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.Address;

import java.util.function.Function;
@Service
public class AddressDTOMapper implements Function<Address,AddressDTO> {
    @Override
    public AddressDTO apply(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getCityName(),
                address.getStreet(),
                address.getNumberHouse(),
                address.getZipCode(),
                address.getDistrict()
        );
    }
    public Address apply(AddressDTO addressDTO) {
        return new Address(
                addressDTO.id(),
                addressDTO.cityName(),
                addressDTO.street(),
                addressDTO.numberHouse(),
                addressDTO.zipCode(),
                addressDTO.district()
        );
    }


}
