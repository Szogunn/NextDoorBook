package pl.orange.NextDoorBook.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.orange.NextDoorBook.address.Address;
import pl.orange.NextDoorBook.address.DTO.AddressAddDTO;
import pl.orange.NextDoorBook.role.Role;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@SQLDelete(sql = "UPDATE USERS SET deleted = true,email = null, password = null ,ADDRESS_ID = null WHERE id=?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public User(String username, String email, Address address, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }


}
