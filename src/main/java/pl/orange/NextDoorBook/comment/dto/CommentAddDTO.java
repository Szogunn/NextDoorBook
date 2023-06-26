package pl.orange.NextDoorBook.comment.dto;


import pl.orange.NextDoorBook.user.dto.UserDTO;

public record CommentAddDTO(
        String message,
        boolean spoilerAlert,
        Long bookId,
        UserDTO user,
        int rate) {
}
