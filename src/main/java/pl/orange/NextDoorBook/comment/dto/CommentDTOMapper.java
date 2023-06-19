package pl.orange.NextDoorBook.comment.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.comment.Comment;
import pl.orange.NextDoorBook.user.UserRepository;
import pl.orange.NextDoorBook.user.dto.UserDTOMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentDTOMapper {
    private final UserDTOMapper userDTOMapper;
    private final BookDTOMapper bookDTOMapper;
    private final BookRepository bookRepository;

    public CommentDTO commentToCommentDTOMap(Comment comment) {
        return new CommentDTO(
                comment.getMessage(),
                comment.isSpoilerAlert(),
                comment.getAddTime(),
                bookDTOMapper.BookToBookDTOMap(comment.getBook()),
                userDTOMapper.map(comment.getUser()),
                comment.getRate()
        );
    }

    public Comment commentDTOtoCommentMap(CommentDTO commentDTO) {
        return Comment.builder()
                .message(commentDTO.message())
                .spoilerAlert(commentDTO.spoilerAlert())
                .addTime(LocalDateTime.now())
                .book(bookRepository.getBookByID(commentDTO.book().id())
                        .orElseThrow(() ->
                                new BookNotFoundException("Book with id " + commentDTO.book().id() + " does not exist")))
                .user(bookRepository.getBookByID(commentDTO.book().id())
                        .orElseThrow(() ->
                                new BookNotFoundException("Book with id " + commentDTO.book().id() + " does not exist"))
                        .getOwner())
                .rate(commentDTO.rate())
                .build();
    }

    public CommentAddDTO commentMapToAddDTO(Comment comment) {
        return new CommentAddDTO(
                comment.getMessage(),
                comment.isSpoilerAlert(),
                bookRepository.getBookByID(comment.getBook().getId()).get().getId(),
                userDTOMapper.map(comment.getUser()),
                comment.getRate()
        );
    }


    public Comment commentMapToAddEntity(CommentAddDTO commentAddDTO) {
        return Comment.builder()
                .message(commentAddDTO.message())
                .spoilerAlert(commentAddDTO.spoilerAlert())
                .addTime(LocalDateTime.now())
                .book(bookRepository.getBookByID(commentAddDTO.bookId())
                        .orElseThrow(() ->
                                new BookNotFoundException("Book with id " + commentAddDTO.bookId() + " does not exist")))
                .rate(commentAddDTO.rate())
                .build();

    }

    public Comment updateCommentMapper(CommentUpdateDTO commentUpdateDTO, Comment comment) {

        if (commentUpdateDTO.message() != null) {
            comment.setMessage(commentUpdateDTO.message());
        }
        if (commentUpdateDTO.spoilerAlert().isPresent()) {
            comment.setSpoilerAlert(commentUpdateDTO.spoilerAlert().get());
        }
        if (commentUpdateDTO.rate() != 0) {
            comment.setRate(commentUpdateDTO.rate());
        }
        return comment;
    }

    public CommentUpdateDTO commentToCommentUpdateDTOMap(Comment comment) {

        return new CommentUpdateDTO(comment.getId(),
                comment.getMessage(),
                Optional.of(comment.isSpoilerAlert()),
                comment.getRate());
    }

}
