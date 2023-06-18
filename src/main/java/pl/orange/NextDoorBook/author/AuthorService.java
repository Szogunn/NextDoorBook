package pl.orange.NextDoorBook.author;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.address.exception.AddressNotFoundException;
import pl.orange.NextDoorBook.author.dto.AuthorAddDTO;
import pl.orange.NextDoorBook.author.dto.AuthorDTO;
import pl.orange.NextDoorBook.author.dto.AuthorDTOMapper;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorDTOMapper authorDTOMapper;
    private final BookDTOMapper bookDTOMapper;

    public AuthorAddDTO addAuthor(Long bookId, AuthorAddDTO authorAddDTO) {
        Author findAuthor = authorDTOMapper.authorAddDTOToAuthorMap(authorAddDTO);
        return authorDTOMapper.authorTOAuthorAddDTOMap(
                bookRepository.getBookByID(bookId)
                        .map((book) -> {
                            Optional<Author> optionalAuthor = authorRepository.getAuthor(findAuthor);
                            if (optionalAuthor.isPresent()) {
                                return optionalAuthor
                                        .map(author -> {
                                            book.addAuthor(author);
                                            bookRepository.saveBook(book);
                                            return author;
                                        })
                                        .orElseThrow(() ->
                                                new BookNotFoundException(""));
                            }
                            Author authorToSave = authorDTOMapper.authorAddDTOToAuthorMap(authorAddDTO);
                            book.addAuthor(authorToSave);
                            return authorRepository.addAuthor(authorToSave);
                        }).orElseThrow(() ->
                                new BookNotFoundException("")));

    }

    public BookDTO deleteAuthorFromBook(Long bookId, Long authorId) {
        BookDTO bookAfterDelete = bookDTOMapper.BookToBookDTOMap(
                bookRepository.getBookByID(bookId)
                        .map(book -> {
                            book.removeAuthor(authorId);
                            return bookRepository.saveBook(book);
                        })
                        .orElseThrow(() ->
                                new BookNotFoundException("Book with id " + bookId + "does not exits.")));

        for (Author unusedAuthor : authorRepository.checkIfAuthorsAreInUse()) {
            authorRepository.deleteAuthorByID(unusedAuthor.getId());
        }
        return bookAfterDelete;
    }

    public AuthorDTO getAuthorByID(Long authorId) {
        return authorRepository.getAuthorByID(authorId)
                .map(authorDTOMapper::authorTOAuthorDTOMap)
                .orElseThrow(() ->
                        new AddressNotFoundException("Author with id " + authorId + "does not exits."));
    }

    public AuthorDTO updateAuthor(Long authorToUpdateId, AuthorDTO authorDTO) {
        return authorRepository.getAuthorByID(authorToUpdateId)
                .map(author -> {
                    author.setFirstName(authorDTO.firstName());
                    author.setLastName(authorDTO.lastName());
                    author.setNationality(authorDTO.nationality());
                    return authorDTOMapper.authorTOAuthorDTOMap(authorRepository.updateAuthor(author));
                })
                .orElseThrow(() ->
                        new AddressNotFoundException("Author with id " + authorToUpdateId + "does not exits."));

    }

}
