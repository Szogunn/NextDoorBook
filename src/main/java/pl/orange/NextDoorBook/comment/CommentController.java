package pl.orange.NextDoorBook.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.comment.dto.CommentAddDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTO;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(path = "")
    public ResponseEntity<CommentAddDTO> addComment(@RequestBody CommentAddDTO commentAddDTO) {
        return ResponseEntity
                .status(200)
                .body(commentService.addComment(commentAddDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCommentByID(@PathVariable Long id) {
        commentService.deleteCommentByID(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> getCommentByID(@PathVariable Long id) {

        return ResponseEntity
                .status(200)
                .body(commentService.getCommentByID(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }
}
