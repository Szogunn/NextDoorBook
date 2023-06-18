package pl.orange.NextDoorBook.comment.dto;

import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.user.dto.UserDTO;

import java.time.LocalDateTime;

public record CommentDTO(
        String message,
        boolean spoilerAlert,
        LocalDateTime addTime,
        BookDTO book,
        UserDTO user,
        int rate
) {
}
