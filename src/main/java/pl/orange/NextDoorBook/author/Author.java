package pl.orange.NextDoorBook.author;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.book.Book;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String nationality;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
