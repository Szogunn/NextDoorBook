package pl.orange.NextDoorBook.comment.dto;


import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.user.dto.UserDTO;

public record CommentAddDTO(
        String message,
        boolean spoilerAlert,
        Long bookId,
        Long userId,
        int rate) {
}
