package pl.orange.NextDoorBook.comment;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.comment.rate.Rate;
import pl.orange.NextDoorBook.comment.rate.RateConverter;
import pl.orange.NextDoorBook.user.User;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private boolean spoilerAlert;


    @Convert(converter = RateConverter.class)
    private Rate rate;
    @ManyToOne
    private Book book;


}
