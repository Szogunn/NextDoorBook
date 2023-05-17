package pl.orange.NextDoorBook.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.author.entity.Author;
import pl.orange.NextDoorBook.comment.entity.Comment;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_id")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "books")
    private Set<Author> articles = new HashSet<>();

}
