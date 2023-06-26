package pl.orange.NextDoorBook.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.orange.NextDoorBook.book.BookService;
import pl.orange.NextDoorBook.comment.dto.CommentAddDTO;
import pl.orange.NextDoorBook.comment.dto.CommentDTO;
import pl.orange.NextDoorBook.comment.dto.CommentUpdateDTO;
import pl.orange.NextDoorBook.security.payloads.MessageResponse;
import pl.orange.NextDoorBook.user.User;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentAddDTO> addComment(@RequestBody CommentAddDTO commentAddDTO
            , UsernamePasswordAuthenticationToken user) {

        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);

        return ResponseEntity
                .status(200)
                .body(commentService.addComment(commentAddDTO, userFromObjectMapper));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteCommentByID(@PathVariable Long id, UsernamePasswordAuthenticationToken user) {

        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);

        if (!commentService.isCommentBelongToUser(id, userFromObjectMapper)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Comment has another owner"));
        }

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

    @PatchMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateComment(@RequestBody CommentUpdateDTO comment
            , UsernamePasswordAuthenticationToken user) {

        User userFromObjectMapper = objectMapper.convertValue(user.getPrincipal(), User.class);

        if (!commentService.isCommentBelongToUser(comment.id(), userFromObjectMapper)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Comment has another owner"));
        }

        return ResponseEntity
                .status(200)
                .body(commentService.updateComment(comment));
    }
}
