package pl.orange.NextDoorBook.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.comment.dto.CommentAddDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTO;
import pl.orange.NextDoorBook.user.User;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentAddDTO commentAddDTO
    , UsernamePasswordAuthenticationToken user) {

        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);

        return ResponseEntity
                .status(200)
                .body(commentService.addComment(commentAddDTO, userFromObjectMapper));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteCommentByID(@PathVariable Long id) {
        commentService.deleteCommentByID(id);
        return ResponseEntity
                .status(200)
                .build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDTO> getCommentByID(@PathVariable Long id) {

        return ResponseEntity
                .status(200)
                .body(commentService.getCommentByID(id));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }
}
