package pl.orange.NextDoorBook.comment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "COMMENTS")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private boolean spoilerAlert;
    private LocalDateTime addTime;
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
