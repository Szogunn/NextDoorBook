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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID")
    private User owner;

}
