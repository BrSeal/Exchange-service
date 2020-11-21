package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeRequestDTO;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.security.entity.User;

import java.util.List;
import java.util.Map;

public interface ExchangeService {

    Map<String, Double> requestRatesFromExternalAPI(String type);

    List<Exchange> getAll();

    Exchange getById(int id);

    ExchangeResultDTO doExchange(ExchangeRequestDTO dto, User user);

    List<Exchange> findAllByUserId(int id);
}
