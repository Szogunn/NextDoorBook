package pl.orange.NextDoorBook.address.DTO;

public record UserAddressDTO (String cityName,
                             String street,
                             int numberHouse,
                             int zipCode,
                             String district) {

}