package pl.orange.NextDoorBook.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.exception.AddressNotFoundException;
import pl.orange.NextDoorBook.author.Author;
import pl.orange.NextDoorBook.author.AuthorRepository;
import pl.orange.NextDoorBook.author.dto.AuthorAddDTO;
import pl.orange.NextDoorBook.author.dto.AuthorDTOMapper;
import pl.orange.NextDoorBook.book.dto.BookAddDTO;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookDTOMapper bookDTOMapper;
    private final AuthorDTOMapper authorDTOMapper;


    public BookAddDTO addBook(BookAddDTO bookAddDTO, Long userId) {
        Set<AuthorAddDTO> authorsToAdd = new HashSet<>(bookAddDTO.authors());
        bookAddDTO.authors().clear();
        Book bookToAdd = bookDTOMapper.BookAddDTOToBookMap(bookAddDTO);
        Book addedBook = bookRepository.addBook(bookToAdd, userId);

        for (AuthorAddDTO authorToAdd : authorsToAdd) {
            if (authorToAdd.id() != null) {
                authorRepository.getAuthorByID(authorToAdd.id())
                        .map(author -> {
                            addedBook.addAuthor(author);
                            return author;
                        })
                        .orElseThrow(() ->
                                new AddressNotFoundException("Author with id " + authorToAdd.id() + " does not exist"));
            } else {
                addedBook.addAuthor(authorDTOMapper.authorAddDTOToAuthorMap(authorToAdd));
            }
        }
        return bookDTOMapper
                .BookToBookAddDTOMap(addedBook);
    }

    public void deleteBook(Long id) {
        bookRepository
                .getBookByID(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + id + " does not exist"));

        bookRepository.deleteBookByID(id);
    }

    public List<BookAddDTO> getBooksByGenre(BookGenre bookGenre) {
        return bookRepository
                .getBooksByGenre(bookGenre)
                .stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public List<BookAddDTO> getBooksByAuthorsLastName(String lastName) {
        return bookRepository.getBooksByAuthorsLastName(lastName)
                .stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public List<BookAddDTO> getBooksByAuthorsNationality(String lastName) {
        return bookRepository.getBooksByAuthorsNationality(lastName)
                .stream()
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .collect(Collectors.toList());
    }

    public BookAddDTO getBookByISBN(Long isbn) {

        return bookRepository.getBookByISBN(isbn)
                .map(bookDTOMapper::BookToBookAddDTOMap)
                .orElseThrow(() ->
                        new BookNotFoundException("Book with isbn "+isbn+" doesn't exist."));

    }

    public List<BookDTO> getAllBooks() {

        return bookRepository.getAllBooks()
                .stream()
                .map(bookDTOMapper::BookToBookDTOMap)
                .collect(Collectors.toList());
    }


    public BookDTO updateBook(BookDTO book) {
        bookRepository
                .getBookByID(bookDTOMapper.BookDTOToBookMap(book).getId())
                .orElseThrow(() ->
                        new BookNotFoundException
                                ("Books with id " + book.id() + " does not exist"));

        Book result = bookRepository.updateBook(bookDTOMapper.BookDTOToBookMap(book));

        authorRepository.deleteAuthorsByIDList(authorRepository
                .checkIfAuthorsAreInUse()
                .stream()
                .mapToLong(Author::getId)
                .boxed()
                .collect(Collectors.toSet()));


        return bookDTOMapper.BookToBookDTOMap(result);
    }
}
