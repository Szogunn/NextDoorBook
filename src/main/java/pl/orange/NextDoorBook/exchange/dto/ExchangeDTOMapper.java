package pl.orange.NextDoorBook.exchange.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.book.dto.BookDTOMapper;
import pl.orange.NextDoorBook.exchange.Exchange;
import pl.orange.NextDoorBook.user.dto.UserDTOMapper;


@Service
@RequiredArgsConstructor
public class ExchangeDTOMapper {
    private final UserDTOMapper userDTOMapper;
    private final BookDTOMapper bookDTOMapper;

    public ExchangeDTO mapToDTO(Exchange exchange) {
        return new ExchangeDTO(
                exchange.getId(),
                exchange.getStartRent(),
                exchange.getEndRent(),
                userDTOMapper.map(exchange.getOwner()),
                userDTOMapper.map(exchange.getRenter()),
                bookDTOMapper.BookToBookDTOMap(exchange.getBook()),
                exchange.isConfirmExchange(),
                exchange.isConfirmReturn()
        );
    }

    public Exchange mapToEntity(ExchangeDTO exchangeDTO) {
        return new Exchange(
                exchangeDTO.id(),
                exchangeDTO.startRent(),
                exchangeDTO.endRent(),
                userDTOMapper.map(exchangeDTO.owner()),
                userDTOMapper.map(exchangeDTO.renter()),
                bookDTOMapper.BookDTOToBookMap(exchangeDTO.book()),
                exchangeDTO.confirmExchange(),
                exchangeDTO.confirmReturn()
        );
    }
    public ExchangeAddDTO mapToAddDTO(Exchange exchange){
        return new ExchangeAddDTO(
                exchange.getStartRent(),
                exchange.getEndRent(),
                userDTOMapper.map(exchange.getOwner()),
                userDTOMapper.map(exchange.getRenter()),
                bookDTOMapper.BookToBookDTOMap(exchange.getBook())
        );
    }
    public Exchange mapToAddEntity(ExchangeAddDTO exchangeAddDTO){
        return new Exchange(
                null,
                exchangeAddDTO.startRent(),
                exchangeAddDTO.endRent(),
                userDTOMapper.map(exchangeAddDTO.owner()),
                userDTOMapper.map(exchangeAddDTO.renter()),
                bookDTOMapper.BookDTOToBookMap(exchangeAddDTO.book()),
                false,
                false
        );
    }
}
