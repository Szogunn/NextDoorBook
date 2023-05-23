package pl.orange.NextDoorBook.address.DTO;

public record AddressDTO (String cityName,
                          String street,
                          int numberHouse,
                          int zipCode,
                          String district) {

}
