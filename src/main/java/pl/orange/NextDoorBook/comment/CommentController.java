package pl.orange.NextDoorBook.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<?> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteCommentByID(@PathVariable Long id){
        return commentService.deleteCommentByID(id);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentByID(@PathVariable Long id){
        return commentService.getCommentByID(id);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id,@RequestBody Comment comment){
        return commentService.updateComment(id,comment);
    }
}
