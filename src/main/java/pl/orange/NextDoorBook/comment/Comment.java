package pl.orange.NextDoorBook.comment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import pl.orange.NextDoorBook.book.Book;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(value = 1, message = "Rate should not be less than 1")
    @Max(value = 5, message = "Rate should not be greater than 5")
    private int rate;


}
