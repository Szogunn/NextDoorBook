package pl.orange.NextDoorBook.book.dto;

import pl.orange.NextDoorBook.comment.dto.CommentDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;

import java.util.Set;

public record BookInfoDTO(
        BookDTO book,
        boolean available,
        Double average,
        Integer exchangeCount,
        ExchangeDTO exchangeHistory,
        Set<CommentDTO> comments
) {
}
