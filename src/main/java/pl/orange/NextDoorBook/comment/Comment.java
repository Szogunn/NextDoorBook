package pl.orange.NextDoorBook.comment;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.comment.rate.Rate;
import pl.orange.NextDoorBook.comment.rate.RateConverter;
import pl.orange.NextDoorBook.user.User;

import java.util.Set;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private boolean spoilerAlert;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = RateConverter.class)
    private Rate rate;



}
