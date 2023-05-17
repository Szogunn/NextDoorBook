package pl.orange.NextDoorBook.comment.entity;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.book.entity.Book;

import java.util.Set;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;



}
