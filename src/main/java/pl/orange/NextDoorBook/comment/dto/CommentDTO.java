package pl.orange.NextDoorBook.comment.dto;

import pl.orange.NextDoorBook.book.dto.BookDTO;
import pl.orange.NextDoorBook.user.dto.UserDTO;

public record CommentDTO(
        Long id,
        String message,
        boolean spoilerAlert,
        BookDTO book,
        UserDTO user,
        int rate
) {
}
