package pl.orange.NextDoorBook.user;


import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.address.Address;


@Entity
@Data
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String login;
    private char[] password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

}
