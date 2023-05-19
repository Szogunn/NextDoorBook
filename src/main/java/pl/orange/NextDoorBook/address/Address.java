package pl.orange.NextDoorBook.address;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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


}
