package pl.orange.NextDoorBook.exchange;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.user.User;

import java.time.LocalDate;
import java.util.Optional;


public interface IExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query(value = "UPDATE Exchange SET startRent = :startRent , endRent =:endRent, owner =:owner, renter =:renter, book =:book WHERE id=:id")
    @Modifying
    @Transactional
    void updateExchange(@Param("id") Long id,
                        @Param("startRent") LocalDate startRent,
                        @Param("endRent") LocalDate endRent,
                        @Param("owner") User owner,
                        @Param("renter") User renter,
                        @Param("book") Book book);

    Optional<Exchange> findExchangeByBookIdAndConfirmExchangeTrueAndConfirmReturnFalse(Long BookId);
}
