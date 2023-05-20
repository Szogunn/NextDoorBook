package pl.orange.NextDoorBook.exchange;

import jakarta.persistence.*;
import lombok.Data;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "EXCHANGES")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startRent;
    private LocalDate endRent;
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "RENTER_ID")
    private User renter;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
