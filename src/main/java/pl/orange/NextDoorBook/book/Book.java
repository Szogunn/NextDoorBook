package pl.orange.NextDoorBook.book;

import jakarta.persistence.*;
import lombok.*;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"title", "isbn", "numPages", "language", "publisher", "publishedYear", "bookGenre"})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private long isbn;
    private int numPages;
    private String language;
    private String publisher;
    private LocalDate publishedYear;


    @Enumerated(EnumType.STRING)
    BookGenre bookGenre;



    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "OWNER_ID")
    private User owner;


    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Long authorId) {
        Author author = this.authors
                .stream()
                .filter(a -> a.getId() == authorId)
                .findFirst()
                .orElse(null);
        if (author != null) {
            this.authors.remove(author);
            author.getBooks().remove(this);
        }
    }
}
