package pl.orange.NextDoorBook.book;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tittle;
    private long isbn;
    private int numPages;
    private String language;
    private String publisher;
    private LocalDate publishedYear;


    @Enumerated(EnumType.STRING)
    BookGenre bookGenre;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

}
