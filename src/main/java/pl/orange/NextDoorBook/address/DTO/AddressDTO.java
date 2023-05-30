package pl.orange.NextDoorBook.address.DTO;

public record AddressDTO(Long id,
                         String cityName,
                         String street,
                         int numberHouse,
                         int zipCode,
                         String district) {

}
