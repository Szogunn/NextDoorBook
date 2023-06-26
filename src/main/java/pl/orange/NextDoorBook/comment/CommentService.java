package pl.orange.NextDoorBook.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.Book;
import pl.orange.NextDoorBook.book.BookRepository;
import pl.orange.NextDoorBook.book.exceptions.BookNotFoundException;
import pl.orange.NextDoorBook.comment.dto.CommentAddDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTOMapper;
import pl.orange.NextDoorBook.comment.dto.CommentUpdateDTO;
import pl.orange.NextDoorBook.comment.exceptions.CommentNotFoundException;
import pl.orange.NextDoorBook.comment.exceptions.RateIllegalArgumentException;
import pl.orange.NextDoorBook.user.User;
import pl.orange.NextDoorBook.user.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDTOMapper commentDTOMapper;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    public CommentAddDTO addComment(CommentAddDTO commentAddDTO, User user) {
        Comment commentToAdd = commentDTOMapper.commentMapToAddEntity(commentAddDTO);

        commentToAdd.setUser(userRepository.getUserById(user.getId()).get());

        if (commentToAdd.getRate() > 5) {
            throw new RateIllegalArgumentException("Rate should not be greater than 5");
        } else if (commentToAdd.getRate() < 1) {
            throw new RateIllegalArgumentException("Rate should not be less than 1");
        }
        Comment comment = commentRepository.addComment(commentToAdd);
        return commentDTOMapper.commentMapToAddDTO(comment);

    }

    public boolean isCommentBelongToUser(Long commentId, User user) {
        Comment comment = commentRepository.getCommentByID(commentId)
                .orElseThrow(() ->
                        new CommentNotFoundException("Book with id " + commentId + " doesn't exist."));
        if (comment.getUser().getId().equals(user.getId())) {
            return true; // comment belong to User
        }
        return false; // Comment do not belong to user
    }

    public void deleteCommentByID(Long id) {
        if (commentRepository.getCommentByID(id).isEmpty()) {
            throw new CommentNotFoundException("Comment with id " + id + " doesn't exist");
        }
        commentRepository.deleteCommentByID(id);
    }


    public CommentDTO getCommentByID(Long id) {
        return commentRepository
                .getCommentByID(id)
                .map(commentDTOMapper::commentToCommentDTOMap)
                .orElseThrow(() ->
                        new CommentNotFoundException("Comment with id " + id + " doesn't exist"));
    }

    public CommentUpdateDTO updateComment(CommentUpdateDTO updatedComment) {

        Comment comment = commentRepository.getCommentByID(updatedComment.id())
                .orElseThrow(() ->
                        new CommentNotFoundException("Comment with id " + updatedComment.id() + " doesn't exist"));

        if (updatedComment.rate() > 5) {
            throw new RateIllegalArgumentException("Rate should not be greater than 5");
        }
        if (updatedComment.rate() < 1) {
            throw new RateIllegalArgumentException("Rate should not be less than 1");
        }
        Comment result = commentRepository
                .updateComment(commentDTOMapper.updateCommentMapper(updatedComment, comment));

        return commentDTOMapper.commentToCommentUpdateDTOMap(result);

    }
}

