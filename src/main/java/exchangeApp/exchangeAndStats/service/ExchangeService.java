package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;

import java.util.Map;

public interface ExchangeService {

    Map<String, Double> getActualRates();

    Map<String, Double> getActualRates(String base);

    ExchangeResultDTO doExchange(Exchange exchange);
}
