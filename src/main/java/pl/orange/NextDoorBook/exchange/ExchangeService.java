package pl.orange.NextDoorBook.exchange;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.orange.NextDoorBook.exchange.dto.ExchangeAddDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTO;
import pl.orange.NextDoorBook.exchange.dto.ExchangeDTOMapper;
import pl.orange.NextDoorBook.exchange.exception.ExchangeNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final ExchangeDTOMapper exchangeDTOMapper;

    public ExchangeAddDTO addExchange(ExchangeAddDTO exchangeAddDTO) {
        Exchange exchangeToAdd = exchangeDTOMapper.mapToAddEntity(exchangeAddDTO);

        return exchangeDTOMapper.mapToAddDTO(exchangeRepository.addExchange(exchangeToAdd));

    }

    public void deleteExchangeById(Long id) {
        if (exchangeRepository.getExchangeById(id).isEmpty()) {
            throw new ExchangeNotFoundException("Exchange with id " + id + " doesn't exist");
        }
        exchangeRepository.deleteExchangeById(id);

    }

    public ExchangeDTO getExchangeById(Long id) {
        return exchangeRepository
                .getExchangeById(id)
                .map(exchangeDTOMapper::mapToDTO)
                .orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + id + " doesn't exist"));


    }

    public ExchangeDTO updateExchange(ExchangeDTO exchangeDTO) {

        exchangeRepository
                .getExchangeById(exchangeDTOMapper.mapToEntity(exchangeDTO).getId())
                .orElseThrow(() ->
                        new ExchangeNotFoundException("Exchange with id " + exchangeDTO.id() + " doesn't exist"));


        Exchange exchange = exchangeRepository.updateExchange(exchangeDTOMapper.mapToEntity(exchangeDTO));

        return exchangeDTOMapper.mapToDTO(exchange);

    }
}
