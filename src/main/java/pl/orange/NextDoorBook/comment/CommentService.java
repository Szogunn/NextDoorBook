package pl.orange.NextDoorBook.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final ICommentRepository iCommentRepository;

    public ResponseEntity<?> addComment(Comment comment) {
        if (comment == null) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        iCommentRepository.save(comment);
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> deleteCommentByID(Long id) {
        Optional<Comment> toDelete = iCommentRepository.findById(id);
        if (toDelete.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        iCommentRepository.deleteById(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<Comment> getCommentByID(Long id) {
        return iCommentRepository.findById(id)
                .map(comment -> ResponseEntity.status(200).body(comment))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    public ResponseEntity<?> updateComment(Long id, Comment comment) {
        if (iCommentRepository.findById(id).isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        iCommentRepository.updateComment(id, comment.getMessage(), comment.isSpoilerAlert(), comment.getBook(), comment.getUser(), comment.getRate());
        return ResponseEntity
                .status(200)
                .build();
    }
}
