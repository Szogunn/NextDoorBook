package pl.orange.NextDoorBook.comment.dto;

import java.util.Optional;

public record CommentUpdateDTO (
        Long id,
        String message,
        Optional<Boolean> spoilerAlert,
        int rate
){
}
