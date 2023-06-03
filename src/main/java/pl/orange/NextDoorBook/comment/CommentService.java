package pl.orange.NextDoorBook.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.comment.dto.CommentAddDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTOMapper;
import pl.orange.NextDoorBook.comment.exceptions.CommentNotFoundException;
import pl.orange.NextDoorBook.comment.exceptions.RateIllegalArgumentException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDTOMapper commentDTOMapper;


    public CommentAddDTO addComment(CommentAddDTO commentAddDTO) {
        Comment commentToAdd = commentDTOMapper.commentMapToAddEntity(commentAddDTO);

        if (commentToAdd.getRate() > 5) {
            throw new RateIllegalArgumentException("Rate should not be greater than 5");
        } else if (commentToAdd.getRate() < 1) {
            throw new RateIllegalArgumentException("Rate should not be less than 1");
        } else {
            return commentDTOMapper.commentMapToAddDTO(commentRepository.addComment(commentToAdd));
        }

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
                .map(commentDTOMapper::commentMapToDTO)
                .orElseThrow(() ->
                        new CommentNotFoundException("Comment with id " + id + " doesn't exist"));
    }

    public ResponseEntity<?> updateComment(Long id, Comment comment) {
        if (comment.getRate() > 5) {
            throw new RateIllegalArgumentException("Rate should not be greater than 5");
        } else if (comment.getRate() < 1) {
            throw new RateIllegalArgumentException("Rate should not be less than 1");
        } else {
            commentRepository.updateComment(id, comment.getMessage(), comment.isSpoilerAlert(), comment.getBook(), comment.getUser(), comment.getRate());
            return ResponseEntity
                    .status(200)
                    .build();
        }
    }
}

