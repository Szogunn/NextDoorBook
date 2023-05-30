package pl.orange.NextDoorBook.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {



    List<Book> findByBookGenre(BookGenre bookGenre);


}
