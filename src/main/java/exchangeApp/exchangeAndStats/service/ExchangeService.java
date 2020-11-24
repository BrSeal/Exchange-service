package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;

import java.util.List;
import java.util.Map;

public interface ExchangeService {
    ExchangeResultDTO doExchange(Exchange exchange);

    List<Exchange> getAll();

    Exchange getById(int id);

    List<Exchange> findAllByUsername(String username);
}
