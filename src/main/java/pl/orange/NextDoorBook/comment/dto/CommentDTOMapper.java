package pl.orange.NextDoorBook.comment.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.UserRepository;
import pl.orange.NextDoorBook.user.dto.UserDTOMapper;
import pl.orange.NextDoorBook.user.exceptions.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentDTOMapper {
    private final UserDTOMapper userDTOMapper;
    private final BookDTOMapper bookDTOMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    public CommentDTO commentMapToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getMessage(),
                comment.isSpoilerAlert(),
                bookDTOMapper.BookToBookDTOMap(comment.getBook()),
                userDTOMapper.map(comment.getUser()),
                comment.getRate()
        );
    }

    public Comment commentMapToEntity(CommentDTO commentDTO) {
        return new Comment(
                commentDTO.id(),
                commentDTO.message(),
                commentDTO.spoilerAlert(),
                bookDTOMapper.BookDTOToBookMap(commentDTO.book()),
                userDTOMapper.map(commentDTO.user()),
                commentDTO.rate()

        );
    }

    public CommentAddDTO commentMapToAddDTO(Comment comment) {
        return new CommentAddDTO(
                comment.getMessage(),
                comment.isSpoilerAlert(),
                comment.getBook().getId(),
                comment.getUser().getId(),
                comment.getRate()
        );
    }

    public Comment commentMapToAddEntity(CommentAddDTO commentAddDTO) {
        return new Comment(
                null,
                commentAddDTO.message(),
                commentAddDTO.spoilerAlert(),
                bookRepository.getBookByID(commentAddDTO.bookId()).orElseThrow(()-> new BookNotFoundException("B")),
                userRepository.getUserById(commentAddDTO.userId()).orElseThrow(()-> new UserNotFoundException("A")),
                commentAddDTO.rate()
        );
    }

}
