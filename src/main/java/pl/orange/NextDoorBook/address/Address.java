package pl.orange.NextDoorBook.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String street;
    private int numberHouse;
    private int zipCode;
    private String district;


    public Address(String cityName, String street, int numberHouse, int zipCode, String district) {
        this.cityName = cityName;
        this.street = street;
        this.numberHouse = numberHouse;
        this.zipCode = zipCode;
        this.district = district;
    }
}
