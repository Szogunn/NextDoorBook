package pl.orange.NextDoorBook.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.orange.NextDoorBook.book.Book;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String nationality;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    Set<Book> books = new HashSet<>();

}
