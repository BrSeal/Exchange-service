package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;

import java.util.List;
import java.util.Map;

public interface ExchangeService {

    Map<String, Double> requestRatesFromExternalAPI(String base);

    List<Exchange> getAll();

    Exchange getById(int id);

    ExchangeResultDTO doExchange(Exchange exchange);

    List<Exchange> findAllByUserId(int id);
}
