package pl.orange.NextDoorBook.exchange.dto;

import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.exchange.Exchange;


@Service

public class ExchangeDTOMapper {

    public ExchangeDTO mapToDTO(Exchange exchange) {
        return new ExchangeDTO(
                exchange.getId(),
                exchange.getStartRent(),
                exchange.getEndRent(),
                exchange.getOwner(),
                exchange.getRenter(),
                exchange.getBook()
        );
    }

    public Exchange mapToEntity(ExchangeDTO exchangeDTO) {
        return new Exchange(
                exchangeDTO.id(),
                exchangeDTO.startRent(),
                exchangeDTO.endRent(),
                exchangeDTO.owner(),
                exchangeDTO.renter(),
                exchangeDTO.book()
        );
    }
}
